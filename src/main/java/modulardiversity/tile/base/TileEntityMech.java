package modulardiversity.tile.base;

import betterwithmods.api.BWMAPI;
import betterwithmods.api.capabilities.CapabilityMechanicalPower;
import betterwithmods.api.tile.IMechanicalPower;
import betterwithmods.util.MechanicalUtil;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import hellfirepvp.modularmachinery.common.util.IEnergyHandler;
import net.minecraft.block.Block;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public abstract class TileEntityMech extends TileColorableMachineComponent implements MachineComponentTile, IEnergyHandler, IMechanicalPower {
    private MachineComponent.IOType ioType;
    private int maxLevel;

    public TileEntityMech(MachineComponent.IOType ioType, int maxLevel) {
        this.ioType = ioType;
        this.maxLevel = maxLevel;
    }

    @Override
    public int getMaximumInput(EnumFacing enumFacing) {
        return maxLevel;
    }

    @Override
    public int getMinimumInput(EnumFacing enumFacing) {
        return 0;
    }

    @Override
    public Block getBlock() {
        return getBlockType();
    }

    @Override
    public World getBlockWorld() {
        return getWorld();
    }

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

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new MachineComponent.EnergyHatch(ioType) {
            public IEnergyHandler getEnergyBuffer() {
                return TileEntityMech.this;
            }
        };
    }

    @Override
    public int getMaxEnergy() {
        return 50;
    }
}
