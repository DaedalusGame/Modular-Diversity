package modulardiversity.tile.base;

import betterwithmods.api.BWMAPI;
import betterwithmods.api.capabilities.CapabilityMechanicalPower;
import betterwithmods.api.tile.IMechanicalPower;
import betterwithmods.util.MechanicalUtil;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import hellfirepvp.modularmachinery.common.util.IEnergyHandler;
import modulardiversity.components.MachineComponents;
import modulardiversity.components.requirements.RequirementMana;
import modulardiversity.components.requirements.RequirementMechanical;
import modulardiversity.util.ICraftingResourceHolder;
import net.minecraft.block.Block;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.Optional;

import javax.annotation.Nullable;

import static modulardiversity.components.MachineComponents.*;

@Optional.Interface(iface = "betterwithmods.api.tile.IMechanicalPower",modid = "betterwithmods")
public abstract class TileEntityMech extends TileColorableMachineComponent implements MachineComponentTile, IMechanicalPower, ICraftingResourceHolder<RequirementMechanical.ResourceToken> {
    private int maxLevel;

    public TileEntityMech() {}

    public TileEntityMech(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    @Optional.Method(modid = "betterwithmods")
    @Override
    public int getMaximumInput(EnumFacing enumFacing) {
        return maxLevel;
    }

    @Optional.Method(modid = "betterwithmods")
    @Override
    public int getMinimumInput(EnumFacing enumFacing) {
        return 0;
    }

    @Optional.Method(modid = "betterwithmods")
    @Override
    public Block getBlock() {
        return getBlockType();
    }

    @Optional.Method(modid = "betterwithmods")
    @Override
    public World getBlockWorld() {
        return getWorld();
    }

    @Optional.Method(modid = "betterwithmods")
    @Override
    public BlockPos getBlockPos() {
        return getPos();
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityMechanicalPower.MECHANICAL_POWER || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityMechanicalPower.MECHANICAL_POWER ? CapabilityMechanicalPower.MECHANICAL_POWER.cast(this) : super.getCapability(capability, facing);
    }
}
