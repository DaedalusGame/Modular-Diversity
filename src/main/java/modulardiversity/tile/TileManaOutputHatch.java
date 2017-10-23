package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.tile.base.TileEntityMana;
import net.minecraft.item.EnumDyeColor;
import vazkii.botania.api.mana.IManaPool;

public class TileManaOutputHatch extends TileEntityMana implements IManaPool {
    public TileManaOutputHatch()
    {
        super(MachineComponent.IOType.OUTPUT);
    }

    @Override
    public boolean isOutputtingPower() {
        return true;
    }

    @Override
    public EnumDyeColor getColor() {
        return EnumDyeColor.WHITE;
    }

    @Override
    public void setColor(EnumDyeColor enumDyeColor) {
        //NOOP
    }

    @Override
    public boolean isFull() {
        return true;
    }

    @Override
    public boolean canRecieveManaFromBursts() {
        return false;
    }
}
