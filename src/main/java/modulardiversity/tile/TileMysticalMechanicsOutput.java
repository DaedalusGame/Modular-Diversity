package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.MachineComponents;
import modulardiversity.components.requirements.RequirementMysticalMechanics;
import modulardiversity.tile.base.TileEntityMysticalMechanics;
import modulardiversity.util.ConsumerMechCapability;
import modulardiversity.util.ICraftingResourceHolder;
import mysticalmechanics.api.DefaultMechCapability;
import mysticalmechanics.api.IMechCapability;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

import javax.annotation.Nullable;

public class TileMysticalMechanicsOutput extends TileEntityMysticalMechanics implements ITickable {
    public int keepPowerTicks;

    @Override
    protected IMechCapability initCapability() {
        return new DefaultMechCapability();
    }

    @Override
    public boolean consume(RequirementMysticalMechanics.ResourceToken token, boolean doConsume) {
        return false;
    }

    @Override
    public boolean generate(RequirementMysticalMechanics.ResourceToken token, boolean doGenerate) {
        if(doGenerate) {
            if(capability.getPower(null) != token.getOutputLevel())
            capability.setPower(token.getOutputLevel(),null);
            keepPowerTicks = 20;
        }

        token.setRequiredlevelMet();

        return true;
    }

    @Override
    public void update() {
        keepPowerTicks = Math.max(0,keepPowerTicks-1);
        if(keepPowerTicks <= 0 && capability.getPower(null) > 0) {
            capability.setPower(0,null);
        }
    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new MachineComponents.MysticalMechanicsHatch(MachineComponent.IOType.OUTPUT) {
            @Override
            public ICraftingResourceHolder<RequirementMysticalMechanics.ResourceToken> getContainerProvider() {
                return TileMysticalMechanicsOutput.this;
            }
        };
    }

    @Override
    public void readCustomNBT(NBTTagCompound compound) {
        super.readCustomNBT(compound);
        DefaultMechCapability capability = (DefaultMechCapability) this.capability;
        capability.power = compound.getDouble("mech_power");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound compound) {
        super.writeCustomNBT(compound);
        DefaultMechCapability capability = (DefaultMechCapability) this.capability;
        compound.setDouble("mech_power", capability.power);
    }
}
