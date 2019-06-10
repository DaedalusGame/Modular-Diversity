package modulardiversity.tile;

import buildcraft.api.mj.ILaserTarget;
import buildcraft.api.mj.MjAPI;
import buildcraft.lib.misc.data.AverageLong;
import hellfirepvp.modularmachinery.common.crafting.MachineRecipe;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import hellfirepvp.modularmachinery.common.util.IEnergyHandler;
import modulardiversity.ModularDiversity;
import modulardiversity.components.MachineComponents;
import modulardiversity.components.requirements.RequirementLaser;
import modulardiversity.util.ICraftingResourceHolder;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Optional;

import javax.annotation.Nullable;

@Optional.Interface(iface = "buildcraft.api.mj.ILaserTarget",modid = "buildcraftlib")
public class TileLaserInput extends TileColorableMachineComponent implements MachineComponentTile, ILaserTarget, ITickable, ICraftingResourceHolder<RequirementLaser.ResourceToken> {
    private static final long MJ_FLOW_ROUND = MjAPI.MJ / 10;
    public long power;
    public long demandedPower;
    public ResourceLocation lastRecipe;

    @Optional.Method(modid = "buildcraftlib")
    @Override
    public long getRequiredLaserPower() {
        return demandedPower - power;
    }

    @Optional.Method(modid = "buildcraftlib")
    @Override
    public long receiveLaserPower(long laserPower) {
        long received = Math.min(laserPower, getRequiredLaserPower());
        power += received;
        return laserPower - received;
    }

    @Optional.Method(modid = "buildcraftlib")
    @Override
    public boolean isInvalidTarget() {
        return this.isInvalid();
    }

    @Override
    public void update() {
        if (world.isRemote) {
            return;
        }

        if (demandedPower <= power) {
            power = demandedPower;
        }
    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new MachineComponents.LaserHatch(MachineComponent.IOType.INPUT) {
            @Override
            public ICraftingResourceHolder<RequirementLaser.ResourceToken> getContainerProvider() {
                return TileLaserInput.this;
            }

            @Override
            public void reset() {
                TileLaserInput.this.demandedPower = 0;
            }
        };
    }

    @Override
    public boolean consume(RequirementLaser.ResourceToken token, boolean doConsume) {
        if(token.getRecipe().getRegistryName() != lastRecipe) {
            lastRecipe = token.getRecipe().getRegistryName();
            demandedPower = token.getRequiredMicroMJ();
        }

        long consumedMicroMJ = Math.min(token.getRequiredMicroMJ(),power);
        token.setRequiredMicroMJ(token.getRequiredMicroMJ() - consumedMicroMJ);
        if(doConsume)
            power -= consumedMicroMJ;
        return consumedMicroMJ > 0;
    }

    @Override
    public boolean generate(RequirementLaser.ResourceToken token, boolean doGenerate) {
        return false;
    }

    @Override
    public String getInputProblem(RequirementLaser.ResourceToken token) {
        return "craftcheck.laser.input";
    }

    @Override
    public String getOutputProblem(RequirementLaser.ResourceToken token) {
        return null;
    }

    @Override
    public void readCustomNBT(NBTTagCompound compound) {
        super.readCustomNBT(compound);
        this.power = compound.getLong("power");
        this.demandedPower = compound.getLong("demandedPower");
        if(compound.hasKey("lastRecipe"))
            this.lastRecipe = new ResourceLocation(compound.getString("lastRecipe"));
    }

    @Override
    public void writeCustomNBT(NBTTagCompound compound) {
        super.writeCustomNBT(compound);
        compound.setLong("power",power);
        compound.setLong("demanded_power",demandedPower);
        if(lastRecipe != null)
            compound.setString("lastRecipe",lastRecipe.toString());
    }

}
