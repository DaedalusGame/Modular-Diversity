package modulardiversity.util;

import flaxbeard.immersivepetroleum.api.crafting.PumpjackHandler;
import hellfirepvp.modularmachinery.common.util.HybridTank;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

import javax.annotation.Nullable;

public class ReservoirTank extends HybridTank {
    public ReservoirTank() {
        super(Integer.MAX_VALUE);
    }

    public int getChunkX() {
        return tile.getPos().getX() >> 4;
    }

    public int getChunkZ() {
        return tile.getPos().getZ() >> 4;
    }

    @Nullable
    public World getWorld() {
        if(tile != null)
            return tile.getWorld();

        return null;
    }

    @Nullable
    @Override
    public FluidStack getFluid() {
        int amt = getFluidAmount();
        if(amt <= 0)
            return null;
        return new FluidStack(PumpjackHandler.getFluid(getWorld(),getChunkX(),getChunkZ()),amt);
    }

    @Override
    public int getFluidAmount() {
        World world = getWorld();
        if(world != null)
            return PumpjackHandler.getFluidAmount(world,getChunkX(),getChunkZ());
        return 0;
    }

    @Override
    public boolean canFill() {
        return false;
    }

    @Override
    public void setCapacity(int capacity) {
        //NOOP
    }

    @Override
    public int fillInternal(FluidStack resource, boolean doFill) {
        return 0;
    }

    @Nullable
    @Override
    public FluidStack drainInternal(int maxDrain, boolean doDrain)
    {
        World world = getWorld();
        if(world == null)
            return null;

        Fluid type = PumpjackHandler.getFluid(world,getChunkX(),getChunkZ());
        int amount = PumpjackHandler.getFluidAmount(world,getChunkX(),getChunkZ());

        if (type == null || amount <= 0 || maxDrain <= 0)
        {
            return null;
        }

        int drained = maxDrain;
        if (amount < drained)
        {
            drained = amount;
        }

        FluidStack stack = new FluidStack(type, drained);
        if (doDrain)
        {
            PumpjackHandler.depleteFluid(world,getChunkX(),getChunkZ(),drained);

            onContentsChanged();

            if (tile != null)
            {
                FluidEvent.fireEvent(new FluidEvent.FluidDrainingEvent(fluid, world, tile.getPos(), this, drained));
            }
        }
        return stack;
    }
}
