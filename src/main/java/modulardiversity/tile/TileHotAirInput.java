package modulardiversity.tile;

import javax.annotation.Nullable;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import lykrast.prodigytech.common.capability.CapabilityHotAir;
import lykrast.prodigytech.common.capability.IHotAir;
import modulardiversity.components.MachineComponents;
import modulardiversity.components.requirements.RequirementHotAir;
import modulardiversity.components.requirements.RequirementHotAir.ResourceToken;
import modulardiversity.tile.base.TileEntityHotAir;
import modulardiversity.util.ICraftingResourceHolder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileHotAirInput extends TileEntityHotAir {
    public TileHotAirInput() {
        super();
    }
    
    public int getCurrentAirTemp() {
    	TileEntity tile = world.getTileEntity(pos.down());
		if (tile != null){
			IHotAir capability = tile.getCapability(CapabilityHotAir.HOT_AIR, EnumFacing.UP);
			if (capability != null) {
				int temp = capability.getOutAirTemperature();
				setAirTemp(temp);
				return temp;
			}

		}
		
		setAirTemp(30);
		return 30;
    }
    
    @Override
	public boolean consume(ResourceToken token, boolean doConsume) {
		if(getCurrentAirTemp() >= token.getRequiredTemp())
			token.setRequiredTempMet();
		return true;
	}
    
    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new MachineComponents.HotAirHatch(MachineComponent.IOType.INPUT) {
            @Override
            public ICraftingResourceHolder<RequirementHotAir.ResourceToken> getContainerProvider() {
                return TileHotAirInput.this;
            }
        };
    }

	@Override
	public int getOutAirTemperature() {
		return 0;
	}

}
