package modulardiversity.block;

import hellfirepvp.modularmachinery.common.CommonProxy;
import hellfirepvp.modularmachinery.common.block.BlockMachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import mekanism.api.lasers.ILaserReceptor;
import modulardiversity.jei.ingredients.DaylightIngredient;
import modulardiversity.tile.TileEntityMekLaserAcceptor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;

import javax.annotation.Nullable;

public class BlockMekLaserAcceptor extends BlockMachineComponent {
    public BlockMekLaserAcceptor() {
        super(Material.IRON);
        setHardness(2F);
        setResistance(10F);
        setSoundType(SoundType.METAL);
        setHarvestLevel("pickaxe", 1);
        setCreativeTab(CommonProxy.creativeTabModularMachinery);
    }

    //Debug Code
    //When the player right clicks sends a message to chat with the energy
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te != null && te instanceof TileEntityMekLaserAcceptor) {
                double energy = ((TileEntityMekLaserAcceptor)te).getEnergy() / 1000;
                String e = (energy < 1000) ? Double.toString(energy) + "kJ" : (energy < 1000000) ? Double.toString(energy/1000) + "MJ" : Double.toString(energy/1000000) + "GJ";
                playerIn.sendStatusMessage(new TextComponentString("Current Energy: " + e), false);
            }
        }
        return true;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityMekLaserAcceptor();
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityMekLaserAcceptor();
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
