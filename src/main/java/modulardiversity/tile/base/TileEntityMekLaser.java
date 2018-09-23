package modulardiversity.tile.base;

import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import mekanism.api.lasers.ILaserReceptor;
import mekanism.common.capabilities.Capabilities;
import modulardiversity.block.BlockMekLaserAcceptor;
import modulardiversity.components.requirements.RequirementMekLaser;
import modulardiversity.util.ICraftingResourceHolder;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public abstract class TileEntityMekLaser extends TileColorableMachineComponent implements MachineComponentTile, ILaserReceptor, ICraftingResourceHolder<RequirementMekLaser.ResourceToken> {
    private double energy;
    private double capacity;


    //TODO add a config for MekLaserAcceptorCapacity
    public TileEntityMekLaser() {
//        System.out.println("Created TileEntityMekLaser");
        this.capacity = 5.0E9D;
        this.energy = 0;
    }

    @Override
    public void readCustomNBT(NBTTagCompound compound) {
        super.readCustomNBT(compound);
        this.energy = compound.getDouble("mlenergy");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound compound) {
        super.writeCustomNBT(compound);
        compound.setDouble("mlenergy",energy);
    }

    @Override
    public void receiveLaserEnergy(double v, EnumFacing enumFacing) {
        this.setEnergy(v + this.energy);
    }

    @Override
    public boolean canLasersDig() {
        return false;
    }

    @Override
    public boolean consume(RequirementMekLaser.ResourceToken token, boolean doConsume) {
        double energyConsumed = Math.min(getEnergy(),token.getEnergy());
        token.setEnergy(token.getEnergy() - energyConsumed);
        if (doConsume)
            setEnergy(getEnergy() - energyConsumed);
        return energyConsumed > 0;
    }

    //TODO Implement laser generation
    @Override
    public boolean generate(RequirementMekLaser.ResourceToken token, boolean doGenerate) {
        return false;
    }

    public void setEnergy(double energy) {
        this.energy = Math.max(0.0D, Math.min(energy, capacity));
    }

    public double getEnergy() {
        return energy;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing side)
    {
        return capability == Capabilities.LASER_RECEPTOR_CAPABILITY || super.hasCapability(capability, side);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing side)
    {
        if(capability == Capabilities.LASER_RECEPTOR_CAPABILITY)
        {
            return (T)this;
        }

        return super.getCapability(capability, side);
    }
}
