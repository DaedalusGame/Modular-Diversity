package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.ModularDiversity;
import modulardiversity.tile.base.TileEntityPneumaticBase;

import javax.annotation.Nullable;

public class TilePneumaticOutput extends TileEntityPneumaticBase {
    public TilePneumaticOutput(int tier, int volume) {
        super(MachineComponent.IOType.OUTPUT, tier, volume);
    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new Component(MachineComponent.IOType.OUTPUT);
    }
}
