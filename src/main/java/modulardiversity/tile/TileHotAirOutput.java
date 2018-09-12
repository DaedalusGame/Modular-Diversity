package modulardiversity.tile;

import javax.annotation.Nullable;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import lykrast.prodigytech.common.capability.CapabilityHotAir;
import lykrast.prodigytech.common.capability.HotAirChangeable;
import modulardiversity.components.MachineComponents;
import modulardiversity.components.requirements.RequirementHotAir;
import modulardiversity.components.requirements.RequirementHotAir.ResourceToken;
import modulardiversity.tile.base.TileEntityHotAir;
import modulardiversity.util.ICraftingResourceHolder;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;

public class TileHotAirOutput extends TileEntityHotAir implements ITickable {
	private HotAirChangeable hotAir;
	private int coolDownClock;
	
    public TileHotAirOutput() {
        super();
        hotAir = new HotAir();
        resetCoolDownClock();
    }
    
    @Override
	public boolean consume(ResourceToken token, boolean doConsume) {
		return false;
	}
    
    @Override
    public boolean generate(RequirementHotAir.ResourceToken token, boolean doGenerate) {
    	token.setRequiredTempMet();
    	if(doGenerate)
    		setAirTemp(token.getRequiredTemp());
    	return true;
    }
    
    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new MachineComponents.HotAirHatch(MachineComponent.IOType.OUTPUT) {
            @Override
            public ICraftingResourceHolder<RequirementHotAir.ResourceToken> getContainerProvider() {
                return TileHotAirOutput.this;
            }
        };
    }

	@Override
	public int getOutAirTemperature() {
		return getAirTemp();
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if(capability==CapabilityHotAir.HOT_AIR && facing == EnumFacing.UP)
			return true;
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if(capability==CapabilityHotAir.HOT_AIR && facing == EnumFacing.UP)
			return (T)hotAir;
		return super.getCapability(capability, facing);
	}
	
	@Override
	public void update() {
		lowerTemp();
	}
	
	private void lowerTemp() {
		if(getAirTemp() <= 30)
			return;
		
		if(coolDownClock > 1)
			coolDownClock--;
		else
		{
			int newTemp = getAirTemp() - 1;
			setAirTemp(newTemp);
			hotAir.setTemperature(newTemp);
			resetCoolDownClock();
		}
	}
	
	private void resetCoolDownClock() {
		coolDownClock = 20;
	}
	
	private class HotAir extends HotAirChangeable {
		public HotAir() {
			super();
		}
	}

}
