package modulardiversity.util;

import mekanism.api.IHeatTransfer;
import mekanism.api.transmitters.IGridTransmitter;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import java.awt.*;

public class HeatUtils {
    @CapabilityInject(IGridTransmitter.class)
    public static Capability<IGridTransmitter> GRID_TRANSMITTER_CAPABILITY = null;

    public static double[] simulate(IHeatTransfer source)
    {
        double heatTransferred[] = new double[] {0, 0};

        for(EnumFacing side : EnumFacing.VALUES)
        {
            IHeatTransfer sink = source.getAdjacent(side);

            if(sink != null)
            {
                double invConduction = sink.getInverseConductionCoefficient() + source.getInverseConductionCoefficient();
                double heatToTransfer = source.getTemp() / invConduction;
                source.transferHeatTo(-heatToTransfer);
                sink.transferHeatTo(heatToTransfer);

                if(!(sink instanceof ICapabilityProvider && !(GRID_TRANSMITTER_CAPABILITY == null) && ((ICapabilityProvider) sink).hasCapability(GRID_TRANSMITTER_CAPABILITY, side.getOpposite())))
                {
                    heatTransferred[0] += heatToTransfer;
                }

                continue;
            }

            //Transfer to air otherwise
            double invConduction = IHeatTransfer.AIR_INVERSE_COEFFICIENT + source.getInsulationCoefficient(side) + source.getInverseConductionCoefficient();
            double heatToTransfer = source.getTemp() / invConduction;
            source.transferHeatTo(-heatToTransfer);
            heatTransferred[1] += heatToTransfer;
        }

        return heatTransferred;
    }

    public static Color getHeatColor(double temperature) {
        temperature = temperature / 100.0;
        int red, green, blue;

        if(temperature <= 66) {
            red = 255;
        } else {
            red = MathHelper.clamp((int)(329.698727446 * Math.pow(temperature - 60,-0.1332047592)),0,255);
        }

        if(temperature <= 66) {
            green = MathHelper.clamp((int)(99.4708025861 * Math.log(temperature) - 161.1195681661),0,255);
        } else {
            green = MathHelper.clamp((int)(288.1221695283 * Math.pow((temperature - 60),-0.0755148492)),0,255);
        }

        if(temperature >= 66) {
            blue = 255;
        } else {
            if(temperature <= 19) {
                blue = 0;
            } else {
                blue = MathHelper.clamp((int)(138.5177312231 * Math.log(temperature - 10) - 305.0447927307),0,255);
            }
        }

        return new Color(red,green,blue);
    }
}
