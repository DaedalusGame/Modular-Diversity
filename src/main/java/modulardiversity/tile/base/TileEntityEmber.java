package modulardiversity.tile.base;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import modulardiversity.block.prop.EmberHatchSize;
import modulardiversity.components.requirements.RequirementEmber;
import modulardiversity.util.ICraftingResourceHolder;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import teamroots.embers.power.DefaultEmberCapability;
import teamroots.embers.power.EmberCapabilityProvider;

import javax.annotation.Nullable;

public abstract class TileEntityEmber extends TileColorableMachineComponent implements MachineComponentTile {
    public DefaultEmberCapability capability = new DefaultEmberCapability();
    private EmberHatchSize size;
    private MachineComponent.IOType ioType;

    public TileEntityEmber() {
    }

    public TileEntityEmber(EmberHatchSize size, MachineComponent.IOType ioType) {
        this.ioType = ioType;
        this.size = size;
        this.capability.setEmberCapacity(size.getSize());
    }

    @Override
    public void readCustomNBT(NBTTagCompound compound) {
        super.readCustomNBT(compound);
        this.ioType = compound.getBoolean("input") ? MachineComponent.IOType.INPUT : MachineComponent.IOType.OUTPUT;
        this.size = EmberHatchSize.values()[MathHelper.clamp(compound.getInteger("size"),0,EmberHatchSize.values().length-1)];
        capability.readFromNBT(compound.getCompoundTag("ember"));
        capability.setEmberCapacity(size.getSize());
    }

    @Override
    public void writeCustomNBT(NBTTagCompound compound) {
        super.writeCustomNBT(compound);
        compound.setBoolean("input", this.ioType == MachineComponent.IOType.INPUT);
        compound.setInteger("size", this.size.ordinal());
        NBTTagCompound emberTag = new NBTTagCompound();
        capability.writeToNBT(emberTag);
        compound.setTag("ember",emberTag);
    }

    @Override
    @Nullable
    public MachineComponent provideComponent() {
        return null;
    }

    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == EmberCapabilityProvider.emberCapability || super.hasCapability(capability, facing);
    }

    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == EmberCapabilityProvider.emberCapability? (T)this.capability : super.getCapability(capability, facing);
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
