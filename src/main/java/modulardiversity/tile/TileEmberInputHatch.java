package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.block.prop.EmberHatchSize;
import modulardiversity.components.requirements.RequirementEmber;
import modulardiversity.tile.base.TileEntityEmber;
import modulardiversity.util.ICraftingResourceHolder;
import modulardiversity.util.IResourceToken;

public class TileEmberInputHatch extends TileEntityEmber implements ICraftingResourceHolder<RequirementEmber.ResourceToken> {
    public TileEmberInputHatch() { super(); }

    public TileEmberInputHatch(EmberHatchSize size)
    {
        super(size,MachineComponent.IOType.INPUT);
    }

    @Override
    public boolean consume(RequirementEmber.ResourceToken token) {
        double emberConsumed = capability.removeAmount(token.getEmber(),true);
        token.setEmber(token.getEmber() - emberConsumed);
        return emberConsumed > 0;
    }

    @Override
    public boolean generate(RequirementEmber.ResourceToken token) {
        double emberAdded = capability.addAmount(token.getEmber(),true);
        token.setEmber(token.getEmber() - emberAdded);
        return emberAdded > 0;
    }
}
