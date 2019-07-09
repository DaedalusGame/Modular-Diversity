package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import modulardiversity.components.MachineComponents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nullable;

public class TileEntityBiomeDetector extends TileColorableMachineComponent {
    private MachineComponent.IOType ioType = MachineComponent.IOType.INPUT;

    public TileEntityBiomeDetector() {
//        System.out.println("Created Tile Entity Biome Detector");
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
}
