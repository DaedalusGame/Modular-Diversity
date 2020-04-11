package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.MachineComponents;
import modulardiversity.components.requirements.RequirementMysticalMechanics;
import modulardiversity.tile.base.TileEntityMysticalMechanics;
import modulardiversity.util.ICraftingResourceHolder;
import mysticalmechanics.api.DefaultMechCapability;
import mysticalmechanics.api.IMechCapability;
import mysticalmechanics.api.MysticalMechanicsAPI;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TileMysticalMechanicsOutput extends TileEntityMysticalMechanics implements ITickable {
    public int keepPowerTicks;

    @Override
    protected IMechCapability initCapability() {
        return new DefaultMechCapability() {
            @Override
            public void setPower(double value, EnumFacing from) {
                if(from == null) {
                    super.setPower(value, from);
                }
            }

            @Override
            public void onPowerChange() {
                super.onPowerChange();
                updateNearby();
                markDirty();
            }

            @Override
            public boolean isInput(EnumFacing from) {
                return false;
            }

            @Override
            public boolean isOutput(EnumFacing from) {
                return true;
            }
        };
    }

    @Override
    public boolean consume(RequirementMysticalMechanics.ResourceToken token, boolean doConsume) {
        return false;
    }

    @Override
    public boolean generate(RequirementMysticalMechanics.ResourceToken token, boolean doGenerate) {
        if(doGenerate && !broken) {
            capability.setPower(token.getLevelOutput(),null);
            keepPowerTicks = token.getTime();
        }

        token.setRequiredlevelMet();

        return true;
    }

    public void breakBlock(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        broken = true;
        this.capability.setPower(0.0D, null);
        updateNearby();
    }

    public void updateNearby() {
        for (EnumFacing f : EnumFacing.values()) {
            TileEntity t = world.getTileEntity(getPos().offset(f));
            if (t != null) {
                if (t.hasCapability(MysticalMechanicsAPI.MECH_CAPABILITY, f.getOpposite())) {
                    t.getCapability(MysticalMechanicsAPI.MECH_CAPABILITY, f.getOpposite()).setPower(capability.getPower(f), f.getOpposite());
                    t.markDirty();
                }
            }
        }
    }

    @Override
    public void update() {
        if(!world.isRemote) {
            keepPowerTicks = Math.max(0, keepPowerTicks - 1);
            if (keepPowerTicks <= 0 && capability.getPower(null) > 0) {
                capability.setPower(0, null);
            }
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
