package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.ModularDiversity;
import modulardiversity.tile.base.TileEntityPneumaticBase;

public class TilePneumaticInputConsume extends TileEntityPneumaticBase {
    public TilePneumaticInputConsume(int tier, int volume) {
        super(MachineComponent.IOType.INPUT, tier, volume);
    }

    @Override
    public int getCurrentEnergy() {
        return (int)(airHandler.getPressure() * ModularDiversity.PressureToFE);
    }

    @Override
    public void setCurrentEnergy(int i) {
        float addedPressure = (i - getCurrentEnergy()) / (float)ModularDiversity.PressureToFE;
        airHandler.addAir((int)(addedPressure / volume));
    }

    @Override
    public int getMaxEnergy() {
        return (int)(airHandler.getMaxPressure() * ModularDiversity.PressureToFE);
    }
}
