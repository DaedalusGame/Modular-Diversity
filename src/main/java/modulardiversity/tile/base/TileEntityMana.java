package modulardiversity.tile.base;

import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import modulardiversity.components.requirements.RequirementMana;
import modulardiversity.util.ICraftingResourceHolder;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import vazkii.botania.api.mana.IManaReceiver;

import static modulardiversity.ModularDiversity.MANA_CAPACITY;

public abstract class TileEntityMana extends TileColorableMachineComponent implements MachineComponentTile, IManaReceiver, ICraftingResourceHolder<RequirementMana.ResourceToken> {
    private int mana;
    private int capacity;

    public TileEntityMana()
    {
        capacity = MANA_CAPACITY;
    }

    @Override
    public void readCustomNBT(NBTTagCompound compound) {
        super.readCustomNBT(compound);
        this.mana = compound.getInteger("mana");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound compound) {
        super.writeCustomNBT(compound);
        compound.setInteger("mana",mana);
    }

    @Override
    public boolean consume(RequirementMana.ResourceToken token, boolean doConsume) {
        int manaConsumed = Math.min(getCurrentMana(),token.getMana());
        token.setMana(token.getMana() - manaConsumed);
        if(doConsume)
            setCurrentMana(getCurrentMana() - manaConsumed);
        return manaConsumed > 0;
    }

    @Override
    public boolean generate(RequirementMana.ResourceToken token, boolean doGenerate) {
        int manaAdded = Math.min(getManaCapacity() - getCurrentMana(),token.getMana());
        token.setMana(token.getMana() - manaAdded);
        if(doGenerate)
            setCurrentMana(getCurrentMana() + manaAdded);
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

    @Override
    public String getInputProblem(RequirementMana.ResourceToken token) {
        return "craftcheck.mana.input";
    }

    @Override
    public String getOutputProblem(RequirementMana.ResourceToken token) {
        return "craftcheck.mana.output";
    }
}
