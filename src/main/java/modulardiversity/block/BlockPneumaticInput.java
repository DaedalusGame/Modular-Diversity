package modulardiversity.block;

import modulardiversity.tile.TilePneumaticInput;
import modulardiversity.tile.TilePneumaticOutput;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockPneumaticInput extends BlockPneumatic {
    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TilePneumaticInput(state.getValue(TIER),1000);
    }
}
