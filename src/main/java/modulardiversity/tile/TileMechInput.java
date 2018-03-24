package modulardiversity.tile;

import betterwithmods.api.BWMAPI;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.tile.base.TileEntityMech;
import net.minecraft.util.EnumFacing;

public class TileMechInput extends TileEntityMech {
    public TileMechInput(int maxLevel) {
        super(MachineComponent.IOType.INPUT, maxLevel);
    }

    @Override
    public int getMechanicalOutput(EnumFacing enumFacing) {
        return -1;
    }

    @Override
    public int getMechanicalInput(EnumFacing enumFacing) {
        return BWMAPI.IMPLEMENTATION.getPowerOutput(world, pos.offset(enumFacing), enumFacing.getOpposite());
    }

    @Override
    public int getCurrentEnergy() {
        return calculateInput();
    }

    @Override
    public void setCurrentEnergy(int i) {
        //NOOP
    }

    @Override
    public int getMaxEnergy() {
        return 50;
    }
}
