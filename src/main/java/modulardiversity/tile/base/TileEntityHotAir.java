package modulardiversity.tile.base;

import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import lykrast.prodigytech.common.capability.IHotAir;
import modulardiversity.components.MachineComponents;
import modulardiversity.components.requirements.RequirementHotAir;
import modulardiversity.components.requirements.RequirementHotAir.ResourceToken;
import modulardiversity.tile.TileHotAirInput;
import modulardiversity.util.ICraftingResourceHolder;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class TileEntityHotAir extends TileColorableMachineComponent implements MachineComponentTile, IHotAir, ICraftingResourceHolder<RequirementHotAir.ResourceToken> {
	private int airTemp;
	
	public TileEntityHotAir(){
	}
	
	@Override
    public void readCustomNBT(NBTTagCompound compound) {
        super.readCustomNBT(compound);
        this.airTemp = compound.getInteger("airtemp");
    }
	
	@Override
    public void writeCustomNBT(NBTTagCompound compound) {
        super.writeCustomNBT(compound);
        compound.setInteger("airtemp", airTemp);
    }

	@Override
	public String getInputProblem(ResourceToken token) {
		return "craftcheck.hotair.input";
	}

	@Override
	public String getOutputProblem(ResourceToken token) {
		return "craftcheck.hotair.output";
	}

	public void setAirTemp(int temp) {
		this.airTemp = temp;
	}
	
	public int getAirTemp() {
		return airTemp;
	}

}
