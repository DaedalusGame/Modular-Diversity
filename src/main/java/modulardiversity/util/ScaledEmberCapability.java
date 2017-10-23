package modulardiversity.util;

import hellfirepvp.modularmachinery.common.util.IEnergyHandler;
import modulardiversity.ModularDiversity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import teamroots.embers.power.IEmberCapability;

public class ScaledEmberCapability implements IEmberCapability, IEnergyHandler {
    int energy = 0;
    int capacity = 0;

    @Override
    public double getEmber() {
        return energy / ModularDiversity.EmberToFE;
    }

    @Override
    public double getEmberCapacity() {
        return capacity / ModularDiversity.EmberToFE;
    }

    @Override
    public void setEmber(double v) {
        energy = (int)(v * ModularDiversity.EmberToFE);
    }

    @Override
    public void setEmberCapacity(double v) {
        capacity = (int)(v * ModularDiversity.EmberToFE);
    }

    @Override
    public double addAmount(double v, boolean b) {
        int emberToAdd = (int)(v * ModularDiversity.EmberToFE);
        int added = Math.min(emberToAdd, capacity - energy);
        if(b)
            energy += added;

        return (double)added / ModularDiversity.EmberToFE;
    }

    @Override
    public double removeAmount(double v, boolean b) {
        int emberToRemove = (int)(v * ModularDiversity.EmberToFE);
        int removed = Math.min(emberToRemove, energy);
        if(b)
            energy -= removed;

        return (double)removed / ModularDiversity.EmberToFE;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setDouble("embers:ember", this.getEmber());
        nbtTagCompound.setDouble("embers:emberCapacity", this.getEmberCapacity());
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        if(nbtTagCompound.hasKey("embers:ember")) {
            setEmber(nbtTagCompound.getDouble("embers:ember"));
        }

        if(nbtTagCompound.hasKey("embers:emberCapacity")) {
            setEmberCapacity(nbtTagCompound.getDouble("embers:emberCapacity"));
        }
    }

    @Override
    public int getCurrentEnergy() {
        return energy;
    }

    @Override
    public void setCurrentEnergy(int i) {
        energy = MathHelper.clamp(i,0,capacity);
    }

    @Override
    public int getMaxEnergy() {
        return capacity;
    }
}
