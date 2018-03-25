package modulardiversity.tile;

import buildcraft.api.mj.ILaserTarget;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import hellfirepvp.modularmachinery.common.util.IEnergyHandler;
import modulardiversity.ModularDiversity;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.common.Optional;

import javax.annotation.Nullable;

@Optional.Interface(iface = "buildcraft.api.mj.ILaserTarget",modid = "buildcraftlib")
public class TileLaserInput extends TileColorableMachineComponent implements MachineComponentTile, ILaserTarget, IEnergyHandler, ITickable {
    int activeTicks;
    int laserTicks;
    int lastLaserPower;
    int demandedPower;

    @Optional.Method(modid = "buildcraftlib")
    @Override
    public long getRequiredLaserPower() {
        if(laserTicks <= 0)
            return 0;
        else
            return (long)demandedPower * ModularDiversity.MJToFE;
    }

    @Optional.Method(modid = "buildcraftlib")
    @Override
    public long receiveLaserPower(long laserPower) {
        long demandedPower = (long)this.demandedPower * ModularDiversity.MJToFE;
        int consumedPower = (int)Math.min(demandedPower,Math.min(Integer.MAX_VALUE,laserPower / ModularDiversity.MJToFE));
        lastLaserPower = consumedPower;
        if(consumedPower > 0)
            laserTicks = 20;
        return - (long)consumedPower * ModularDiversity.MJToFE;
    }

    @Optional.Method(modid = "buildcraftlib")
    @Override
    public boolean isInvalidTarget() {
        return this.isInvalid();
    }

    @Override
    public int getCurrentEnergy() {
        int returnValue;

        if(laserTicks <= 0) {
            returnValue = 0;
            demandedPower = Integer.MAX_VALUE;
            laserTicks = 20;
        }
        else
            returnValue = lastLaserPower;
        activeTicks = 100;

        return returnValue;
    }

    @Override
    public void setCurrentEnergy(int i) {
        int currentEnergy = getCurrentEnergy();
        demandedPower = currentEnergy - i;
    }

    @Override
    public int getMaxEnergy() {
        return 0;
    }

    @Override
    public void update() {
        activeTicks = Math.max(0,activeTicks-1);
        laserTicks = Math.max(0,laserTicks-1);

        if(laserTicks <= 0) {
            lastLaserPower = 0;
            activeTicks = 0;
        }

        if(activeTicks <= 0)
            demandedPower = 0;
    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new MachineComponent.EnergyHatch(MachineComponent.IOType.INPUT)
        {
            @Override
            public IEnergyHandler getEnergyBuffer() {
                return TileLaserInput.this;
            }
        };
    }
}
