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
import teamroots.embers.api.EmbersAPI;
import teamroots.embers.api.capabilities.EmbersCapabilities;
import teamroots.embers.power.DefaultEmberCapability;
import teamroots.embers.power.EmberCapabilityProvider;

import javax.annotation.Nullable;

public abstract class TileEntityEmber extends TileColorableMachineComponent implements MachineComponentTile, ICraftingResourceHolder<RequirementEmber.ResourceToken> {
    public DefaultEmberCapability capability = new DefaultEmberCapability();
    private EmberHatchSize size;

    public TileEntityEmber() {
    }

    public TileEntityEmber(EmberHatchSize size) {
        this.size = size;
        this.capability.setEmberCapacity(size.getSize());
    }

    @Override
    public void readCustomNBT(NBTTagCompound compound) {
        super.readCustomNBT(compound);
        this.size = EmberHatchSize.values()[MathHelper.clamp(compound.getInteger("size"),0,EmberHatchSize.values().length-1)];
        capability.readFromNBT(compound.getCompoundTag("ember"));
        capability.setEmberCapacity(size.getSize());
    }

    @Override
    public void writeCustomNBT(NBTTagCompound compound) {
        super.writeCustomNBT(compound);
        compound.setInteger("size", this.size.ordinal());
        NBTTagCompound emberTag = new NBTTagCompound();
        capability.writeToNBT(emberTag);
        compound.setTag("ember",emberTag);
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

    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == EmbersCapabilities.EMBER_CAPABILITY || super.hasCapability(capability, facing);
    }

    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == EmbersCapabilities.EMBER_CAPABILITY ? (T)this.capability : super.getCapability(capability, facing);
    }


}
