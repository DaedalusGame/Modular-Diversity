package modulardiversity.block;

import hellfirepvp.modularmachinery.common.CommonProxy;
import hellfirepvp.modularmachinery.common.block.BlockMachineComponent;
import modulardiversity.tile.TileMysticalMechanicsInput;
import modulardiversity.tile.TileMysticalMechanicsOutput;
import mysticalmechanics.tileentity.TileEntityCreativeMechSource;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockMysticalMechanicsOutput extends BlockMachineComponent {
    public BlockMysticalMechanicsOutput() {
        super(Material.IRON);
        setHardness(2F);
        setResistance(10F);
        setSoundType(SoundType.METAL);
        setHarvestLevel("pickaxe", 1);
        setCreativeTab(CommonProxy.creativeTabModularMachinery);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
        TileMysticalMechanicsOutput p = (TileMysticalMechanicsOutput)world.getTileEntity(pos);
        p.updateNearby();
    }

    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        TileMysticalMechanicsOutput p = (TileMysticalMechanicsOutput)world.getTileEntity(pos);
        p.breakBlock(world, pos, state, player);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileMysticalMechanicsOutput();
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileMysticalMechanicsOutput();
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
