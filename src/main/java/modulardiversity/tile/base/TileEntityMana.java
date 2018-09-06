package modulardiversity.tile.base;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import modulardiversity.components.requirements.RequirementEmber;
import modulardiversity.components.requirements.RequirementMana;
import modulardiversity.util.ICraftingResourceHolder;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import vazkii.botania.api.mana.IManaBlock;
import vazkii.botania.api.mana.IManaReceiver;

import javax.annotation.Nullable;

public abstract class TileEntityMana extends TileColorableMachineComponent implements MachineComponentTile, IManaReceiver, ICraftingResourceHolder<RequirementMana.ResourceToken> {
    private int mana;
    private int capacity;
    private MachineComponent.IOType ioType;

    public TileEntityMana()
    {
        capacity = 10000;
    }

    public TileEntityMana(MachineComponent.IOType ioType) {
        this();
        this.ioType = ioType;
    }

    @Override
    public void readCustomNBT(NBTTagCompound compound) {
        super.readCustomNBT(compound);
        this.ioType = compound.getBoolean("input") ? MachineComponent.IOType.INPUT : MachineComponent.IOType.OUTPUT;
        this.mana = compound.getInteger("mana");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound compound) {
        super.writeCustomNBT(compound);
        compound.setBoolean("input", this.ioType == MachineComponent.IOType.INPUT);
        compound.setInteger("mana",mana);
    }

    @Override
    public boolean consume(RequirementMana.ResourceToken token) {
        int manaConsumed = Math.min(mana,token.getMana());
        token.setMana(token.getMana() - manaConsumed);
        return manaConsumed > 0;
    }

    @Override
    public boolean generate(RequirementMana.ResourceToken token) {
        int manaAdded = Math.min(capacity - mana,token.getMana());
        token.setMana(token.getMana() - manaAdded);
        return manaAdded > 0;
    }

    public int getManaCapacity() {
        return capacity;
    }

    public void setCurrentMana(int mana) {
        this.mana = mana;
    }

    @Override
    public void recieveMana(int i) {
        setCurrentMana(MathHelper.clamp(getCurrentMana() + i,0,getManaCapacity()));
    }

    @Override
    public int getCurrentMana() {
        return mana;
    }

    public static class Component extends MachineComponent<ICraftingResourceHolder<RequirementEmber.ResourceToken>> {
        public Component(IOType ioType) {
            super(ioType);
        }

        @Override
        public ComponentType getComponentType() {
            return ComponentType.Registry.getComponent("ember");
        }

        @Override
        public ICraftingResourceHolder<RequirementEmber.ResourceToken> getContainerProvider() {
            return null;
        }
    }
}
