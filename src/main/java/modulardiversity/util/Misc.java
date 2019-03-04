package modulardiversity.util;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.util.IOInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class Misc {
    public static TileEntity getTileEntity(MachineComponent component)
    {
        Object provider = component.getContainerProvider();
        if(provider instanceof FluidTank)
            ReflectionHelper.getPrivateValue(FluidTank.class,(FluidTank)provider,"tile");
        if(provider instanceof IOInventory)
            return ((IOInventory) provider).getOwner();
        if(provider instanceof TileEntity)
            return (TileEntity) provider;
        return null;
    }
}
