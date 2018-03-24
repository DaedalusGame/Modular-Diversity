package modulardiversity.block;

import modulardiversity.tile.TilePneumaticInputConsume;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockPneumaticConsumer extends BlockPneumatic {
    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TilePneumaticInputConsume(state.getValue(TIER),1000);
    }
}
