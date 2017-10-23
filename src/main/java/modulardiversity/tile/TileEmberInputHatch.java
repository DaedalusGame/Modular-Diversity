package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.block.prop.EmberHatchSize;
import modulardiversity.tile.base.TileEntityEmber;

public class TileEmberInputHatch extends TileEntityEmber {
    public TileEmberInputHatch() { super(); }

    public TileEmberInputHatch(EmberHatchSize size)
    {
        super(size,MachineComponent.IOType.INPUT);
    }
}
