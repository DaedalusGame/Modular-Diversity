package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.ModularDiversity;
import modulardiversity.tile.base.TileEntityPneumatic;

public class TilePneumaticInput extends TileEntityPneumatic {
    public TilePneumaticInput(int tier, int volume) {
        super(MachineComponent.IOType.INPUT, tier, volume);
    }

    @Override
    public int getCurrentEnergy() {
        return (int)(airHandler.getPressure() * ModularDiversity.PressureToFE);
    }

    @Override
    public void setCurrentEnergy(int i) {
        //NOOP
    }

    @Override
    public int getMaxEnergy() {
        return (int)(airHandler.getMaxPressure() * ModularDiversity.PressureToFE);
    }
}
