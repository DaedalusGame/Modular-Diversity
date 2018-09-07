package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.ModularDiversity;
import modulardiversity.tile.base.TileEntityPneumaticBase;

import javax.annotation.Nullable;

public class TilePneumaticInput extends TileEntityPneumaticBase {
    public TilePneumaticInput(int tier, int volume) {
        super(MachineComponent.IOType.INPUT, tier, volume);
    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new Component(MachineComponent.IOType.INPUT);
    }
}
