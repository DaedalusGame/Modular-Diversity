package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.block.prop.EmberHatchSize;
import modulardiversity.tile.base.TileEntityEmber;

import javax.annotation.Nullable;

public class TileEmberOutputHatch extends TileEntityEmber {
    public TileEmberOutputHatch() { super(); }

    public TileEmberOutputHatch(EmberHatchSize size)
    {
        super(size,MachineComponent.IOType.OUTPUT);
    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new Component(MachineComponent.IOType.OUTPUT);
    }
}
