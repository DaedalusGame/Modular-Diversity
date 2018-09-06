package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.block.prop.EmberHatchSize;
import modulardiversity.components.requirements.RequirementEmber;
import modulardiversity.tile.base.TileEntityEmber;
import modulardiversity.util.ICraftingResourceHolder;
import modulardiversity.util.IResourceToken;

import javax.annotation.Nullable;

public class TileEmberInputHatch extends TileEntityEmber {
    public TileEmberInputHatch() { super(); }

    public TileEmberInputHatch(EmberHatchSize size)
    {
        super(size,MachineComponent.IOType.INPUT);
    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new Component(MachineComponent.IOType.INPUT);
    }
}
