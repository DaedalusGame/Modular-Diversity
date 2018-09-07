package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.ModularDiversity;
import modulardiversity.block.prop.EmberHatchSize;
import modulardiversity.components.MachineComponents;
import modulardiversity.components.requirements.RequirementMana;
import modulardiversity.tile.base.TileEntityEmber;
import modulardiversity.tile.base.TileEntityMana;
import modulardiversity.util.ICraftingResourceHolder;
import net.minecraft.util.math.MathHelper;
import vazkii.botania.api.mana.IManaReceiver;

import javax.annotation.Nullable;

public class TileManaInputHatch extends TileEntityMana {
    public TileManaInputHatch()
    {
        super();
    }

    @Override
    public boolean isFull() {
        return getCurrentMana() >= getManaCapacity();
    }

    @Override
    public boolean canRecieveManaFromBursts() {
        return true;
    }

    @Override
    @Nullable
    public MachineComponent provideComponent() {
        return new MachineComponents.ManaHatch(MachineComponent.IOType.INPUT) {
            @Override
            public ICraftingResourceHolder<RequirementMana.ResourceToken> getContainerProvider() {
                return TileManaInputHatch.this;
            }
        };
    }
}
