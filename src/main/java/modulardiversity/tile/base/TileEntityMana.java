package modulardiversity.tile.base;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import hellfirepvp.modularmachinery.common.util.IEnergyHandler;
import modulardiversity.ModularDiversity;
import modulardiversity.block.prop.EmberHatchSize;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import teamroots.embers.power.DefaultEmberCapability;
import teamroots.embers.power.EmberCapabilityProvider;
import teamroots.embers.power.IEmberCapability;
import vazkii.botania.api.mana.IManaBlock;
import vazkii.botania.api.mana.IManaReceiver;

import javax.annotation.Nullable;

public abstract class TileEntityMana extends TileColorableMachineComponent implements MachineComponentTile, IEnergyHandler, IManaReceiver {
    private int mana;
    private int capacity;
    private MachineComponent.IOType ioType;

    public TileEntityMana()
    {
        capacity = 10000 * ModularDiversity.ManaToFE;
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
    @Nullable
    public MachineComponent provideComponent() {
        return null;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getManaCapacity() {
        return capacity / ModularDiversity.ManaToFE;
    }

    public void setMana(int mana) {
        this.mana = mana * ModularDiversity.ManaToFE;
    }

    @Override
    public int getCurrentEnergy() {
        return mana;
    }

    @Override
    public void setCurrentEnergy(int i) {
        mana = MathHelper.clamp(i,0,capacity);
    }

    @Override
    public int getMaxEnergy() {
        return capacity;
    }

    @Override
    public int getCurrentMana() {
        return mana / ModularDiversity.ManaToFE;
    }

    @Override
    public void recieveMana(int i) {
        setMana(MathHelper.clamp(getCurrentMana() + i,0,getManaCapacity()));
    }
}
