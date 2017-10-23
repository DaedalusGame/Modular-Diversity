package modulardiversity.tile;

import betterwithmods.api.tile.IMechanicalPower;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import hellfirepvp.modularmachinery.common.util.IEnergyHandler;
import net.minecraft.block.Block;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TileMechInput extends TileColorableMachineComponent implements MachineComponentTile, IEnergyHandler, IMechanicalPower {
    @Override
    public int getCurrentEnergy() {
        return calculateInput();
    }

    @Override
    public void setCurrentEnergy(int i) {
        //NOOP
    }

    @Override
    public int getMaxEnergy() {
        return 2;
    }

    @Override
    public int getMechanicalOutput(EnumFacing enumFacing) {
        return 0;
    }

    @Override
    public int getMechanicalInput(EnumFacing enumFacing) {
        return 0;
    }

    @Override
    public int getMaximumInput(EnumFacing enumFacing) {
        return 0;
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

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return null;
    }
}
