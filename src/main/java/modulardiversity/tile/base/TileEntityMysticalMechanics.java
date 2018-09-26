package modulardiversity.tile.base;

import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import modulardiversity.components.requirements.RequirementMysticalMechanics;
import modulardiversity.util.ICraftingResourceHolder;
import mysticalmechanics.api.IMechCapability;
import mysticalmechanics.api.MysticalMechanicsAPI;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public abstract class TileEntityMysticalMechanics extends TileColorableMachineComponent implements MachineComponentTile, ICraftingResourceHolder<RequirementMysticalMechanics.ResourceToken> {
    protected IMechCapability capability;

    public TileEntityMysticalMechanics() {
        this.capability = initCapability();
    }

    protected abstract IMechCapability initCapability();

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == MysticalMechanicsAPI.MECH_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability == MysticalMechanicsAPI.MECH_CAPABILITY)
            return (T) capability;
        return super.getCapability(capability, facing);
    }
}
