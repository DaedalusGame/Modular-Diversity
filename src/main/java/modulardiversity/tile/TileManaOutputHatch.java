package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.MachineComponents;
import modulardiversity.components.requirements.RequirementMana;
import modulardiversity.tile.base.TileEntityMana;
import modulardiversity.util.ICraftingResourceHolder;
import net.minecraft.item.EnumDyeColor;
import vazkii.botania.api.mana.IManaPool;

import javax.annotation.Nullable;

public class TileManaOutputHatch extends TileEntityMana implements IManaPool {
    public TileManaOutputHatch()
    {
        super();
    }

    @Override
    public boolean isOutputtingPower() {
        return true;
    }

    @Override
    public EnumDyeColor getColor() {
        return EnumDyeColor.WHITE;
    }

    @Override
    public void setColor(EnumDyeColor enumDyeColor) {
        //NOOP
    }

    @Override
    public boolean isFull() {
        return true;
    }

    @Override
    public boolean canRecieveManaFromBursts() {
        return false;
    }

    @Override
    @Nullable
    public MachineComponent provideComponent() {
        return new MachineComponents.ManaHatch(MachineComponent.IOType.OUTPUT) {
            @Override
            public ICraftingResourceHolder<RequirementMana.ResourceToken> getContainerProvider() {
                return TileManaOutputHatch.this;
            }
        };
    }
}
