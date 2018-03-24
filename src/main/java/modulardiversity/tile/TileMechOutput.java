package modulardiversity.tile;

import betterwithmods.api.BWMAPI;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.tile.base.TileEntityMech;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileMechOutput extends TileEntityMech implements ITickable {
    public TileMechOutput(int maxLevel) {
        super(MachineComponent.IOType.OUTPUT, maxLevel);
    }

    public int currentPowerLevel;
    public int keepPowerTicks;

    @Override
    public int getMechanicalOutput(EnumFacing facing) {
        return BWMAPI.IMPLEMENTATION.isAxle(world, pos.offset(facing), facing.getOpposite()) ? currentPowerLevel : -1;
    }

    @Override
    public int getMechanicalInput(EnumFacing enumFacing) {
        return 0;
    }

    @Override
    public int getCurrentEnergy() {
        return 0;
    }

    @Override
    public void setCurrentEnergy(int i) {
        currentPowerLevel = i;
        keepPowerTicks = 20;
    }

    @Override
    public int getMaxEnergy() {
        return 50;
    }

    @Override
    public void update() {
        keepPowerTicks = Math.max(0,keepPowerTicks-1);
        if(keepPowerTicks <= 0 && currentPowerLevel > 0)
        {
            currentPowerLevel = 0;
        }
    }
}
