package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.ModularDiversity;
import modulardiversity.block.prop.EmberHatchSize;
import modulardiversity.tile.base.TileEntityEmber;
import modulardiversity.tile.base.TileEntityMana;
import net.minecraft.util.math.MathHelper;
import vazkii.botania.api.mana.IManaReceiver;

public class TileManaInputHatch extends TileEntityMana implements IManaReceiver {
    public TileManaInputHatch()
    {
        super(MachineComponent.IOType.INPUT);
    }

    @Override
    public boolean isFull() {
        return getCurrentMana() >= getManaCapacity();
    }

    @Override
    public boolean canRecieveManaFromBursts() {
        return true;
    }
}
