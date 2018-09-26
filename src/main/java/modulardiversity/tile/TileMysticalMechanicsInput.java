package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.MachineComponents;
import modulardiversity.components.requirements.RequirementMysticalMechanics;
import modulardiversity.tile.base.TileEntityMysticalMechanics;
import modulardiversity.util.ConsumerMechCapability;
import modulardiversity.util.ICraftingResourceHolder;
import mysticalmechanics.api.IMechCapability;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;

public class TileMysticalMechanicsInput extends TileEntityMysticalMechanics {
    @Override
    protected IMechCapability initCapability() {
        return new ConsumerMechCapability();
    }

    @Override
    public boolean consume(RequirementMysticalMechanics.ResourceToken token, boolean doConsume) {
        double power = capability.getPower(null);
        if(token.getRequiredLevelMin() > power || token.getRequiredLevelMax() < power)
            return false;
        token.setRequiredlevelMet();
        return true;
    }

    @Override
    public boolean generate(RequirementMysticalMechanics.ResourceToken token, boolean doGenerate) {
        return false;
    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new MachineComponents.MysticalMechanicsHatch(MachineComponent.IOType.INPUT) {
            @Override
            public ICraftingResourceHolder<RequirementMysticalMechanics.ResourceToken> getContainerProvider() {
                return TileMysticalMechanicsInput.this;
            }
        };
    }

    @Override
    public void readCustomNBT(NBTTagCompound compound) {
        super.readCustomNBT(compound);
        ConsumerMechCapability capability = (ConsumerMechCapability) this.capability;
        for (int i = 0; i < 6; i++) {
            capability.power[i] = compound.getDouble("mech_power" + i);
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound compound) {
        super.writeCustomNBT(compound);
        ConsumerMechCapability capability = (ConsumerMechCapability) this.capability;
        for (int i = 0; i < 6; i++) {
            compound.setDouble("mech_power" + i, capability.power[i]);
        }
    }
}
