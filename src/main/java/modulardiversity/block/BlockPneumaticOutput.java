package modulardiversity.block;

import hellfirepvp.modularmachinery.common.CommonProxy;
import hellfirepvp.modularmachinery.common.block.BlockMachineComponent;
import modulardiversity.tile.TileJackHatch;
import modulardiversity.tile.TilePneumaticInput;
import modulardiversity.tile.TilePneumaticOutput;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockPneumaticOutput extends BlockPneumatic {
    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TilePneumaticOutput(state.getValue(TIER),1000);
    }
}
