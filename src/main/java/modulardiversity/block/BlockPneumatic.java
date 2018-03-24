package modulardiversity.block;

import hellfirepvp.modularmachinery.common.CommonProxy;
import hellfirepvp.modularmachinery.common.block.BlockCustomName;
import hellfirepvp.modularmachinery.common.block.BlockMachineComponent;
import hellfirepvp.modularmachinery.common.block.BlockVariants;
import modulardiversity.block.prop.EmberHatchSize;
import modulardiversity.tile.TileJackHatch;
import modulardiversity.tile.TilePneumaticInput;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;

public abstract class BlockPneumatic extends BlockMachineComponent implements BlockVariants, BlockCustomName {
    public static final PropertyInteger TIER = PropertyInteger.create("tier", 1, 2);

    public BlockPneumatic() {
        super(Material.IRON);
        setHardness(2F);
        setResistance(10F);
        setSoundType(SoundType.METAL);
        setHarvestLevel("pickaxe", 1);
        setCreativeTab(CommonProxy.creativeTabModularMachinery);
        setDefaultState(getDefaultState().withProperty(TIER,1));
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        items.add(new ItemStack(this, 1, 0));
        items.add(new ItemStack(this, 1, 1));
        items.add(new ItemStack(this, 1, 2));
        items.add(new ItemStack(this, 1, 3));
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(TIER, meta + 1);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(TIER) - 1;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, TIER);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }

    @Override
    public String getIdentifierForMeta(int i) {
        return getStateFromMeta(i).getValue(TIER).toString();
    }

    @Override
    public Iterable<IBlockState> getValidStates() {
        List<IBlockState> ret = new LinkedList<>();
        ret.add(getDefaultState().withProperty(TIER, 1));
        ret.add(getDefaultState().withProperty(TIER, 2));
        return ret;
    }

    @Override
    public String getBlockStateName(IBlockState state) {
        return "tier="+state.getValue(TIER).toString();
    }
}
