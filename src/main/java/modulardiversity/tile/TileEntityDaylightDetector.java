package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import modulardiversity.components.MachineComponents;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;

public class TileEntityDaylightDetector extends TileColorableMachineComponent implements MachineComponentTile {
    private MachineComponent.IOType ioType = MachineComponent.IOType.INPUT;

    public TileEntityDaylightDetector() {
    }

    @Override
    public void readCustomNBT(NBTTagCompound compound) {
        super.readCustomNBT(compound);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound compound) {
        super.writeCustomNBT(compound);
        compound.setBoolean("input", true);
    }

    @Override
    @Nullable
    public MachineComponent provideComponent() {
        return new MachineComponents.DaylightDetector(this.ioType) {
            @Override
            public Long getContainerProvider() {
                return TileEntityDaylightDetector.this.world.getWorldTime()%24000;
            }
        };
    }
}
