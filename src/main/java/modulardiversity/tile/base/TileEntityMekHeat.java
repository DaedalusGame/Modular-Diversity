package modulardiversity.tile.base;

import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import mekanism.api.IHeatTransfer;
import modulardiversity.components.requirements.RequirementMekHeat;
import modulardiversity.util.HeatUtils;
import modulardiversity.util.ICraftingResourceHolder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

import javax.annotation.Nullable;

public abstract class TileEntityMekHeat extends TileColorableMachineComponent implements MachineComponentTile, IHeatTransfer, ITickable, ICraftingResourceHolder<RequirementMekHeat.ResourceToken> {
    public double temperature;
    public double heatToAbsorb = 0;

    @CapabilityInject(IHeatTransfer.class)
    public static Capability<IHeatTransfer> HEAT_TRANSFER_CAPABILITY = null;

    @Override
    public double getTemp()
    {
        return temperature;
    }

    @Override
    public double getInverseConductionCoefficient()
    {
        return 5;
    }

    @Override
    public double getInsulationCoefficient(EnumFacing side)
    {
        return 1000;
    }

    @Override
    public void transferHeatTo(double heat) {
        heatToAbsorb += heat;
    }

    @Override
    public double[] simulateHeat()
    {
        return HeatUtils.simulate(this);
    }

    @Override
    public double applyTemperatureChange() {
        temperature += heatToAbsorb;
        heatToAbsorb = 0;

        return temperature;
    }

    @Override
    public boolean canConnectHeat(EnumFacing side)
    {
        return true;
    }

    @Override
    public IHeatTransfer getAdjacent(EnumFacing side) {
        TileEntity adj = world.getTileEntity(pos.offset(side));

        if(adj != null && adj.hasCapability(HEAT_TRANSFER_CAPABILITY, side.getOpposite())) {
            return adj.getCapability(HEAT_TRANSFER_CAPABILITY, side.getOpposite());
        }

        return null;
    }

    @Override
    public void update() {
        if(!world.isRemote) {
            simulateHeat();
            applyTemperatureChange();
        }
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == HEAT_TRANSFER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability == HEAT_TRANSFER_CAPABILITY) {
            return (T) this;
        }

        return super.getCapability(capability, facing);
    }

    @Override
    public String getInputProblem(RequirementMekHeat.ResourceToken token) {
        return "craftcheck.mekheat.input";
    }

    @Override
    public String getOutputProblem(RequirementMekHeat.ResourceToken token) {
        return "craftcheck.mekheat.output";
    }
}
