package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import modulardiversity.components.MachineComponents;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nullable;

import static modulardiversity.block.BlockWeatherDetector.WEATHER_TYPE;

public class TileEntityWeatherDetector extends TileColorableMachineComponent implements MachineComponentTile, ITickable {
    private MachineComponent.IOType ioType = MachineComponent.IOType.INPUT;

    public TileEntityWeatherDetector() {
        System.out.println("Created Tile Entity Weather Detector");
    }

    @Override
    public void readCustomNBT(NBTTagCompound compound) {
        super.readCustomNBT(compound);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound compound) {
        super.writeCustomNBT(compound);
        compound.setBoolean("input", true);
    }

    @Override
    @Nullable
    public MachineComponent provideComponent() {
        return new MachineComponents.BiomeDetector(this.ioType) {
            @Override
            public Integer getContainerProvider() {
                return (world.isRaining()) ? (world.isThundering()) ? 2 : 1 : 0;
            }
        };
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate)
    {
        return oldState.getBlock() != newSate.getBlock();
    }

    @Override
    public void update() {
        int weather = (world.isRaining()) ? (world.isThundering()) ? 2 : 1 : 0;
        world.setBlockState(pos, world.getBlockState(pos).getBlock().getDefaultState().withProperty(WEATHER_TYPE, weather));
    }
}
