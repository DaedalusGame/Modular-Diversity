package modulardiversity.block;

import hellfirepvp.modularmachinery.common.block.BlockController;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockControllerAlt extends BlockController {
    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }
}
