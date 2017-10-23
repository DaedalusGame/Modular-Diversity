package modulardiversity.util;

import hellfirepvp.modularmachinery.common.tiles.base.TileEntitySynchronized;
import hellfirepvp.modularmachinery.common.util.IOInventory;
import net.minecraft.util.EnumFacing;

public class ExcavatorInventoryRandom extends IOInventory {

    public ExcavatorInventoryRandom(TileEntitySynchronized owner, int[] inSlots, int[] outSlots) {
        super(owner, inSlots, new int[0], new EnumFacing[0]);
    }
}
