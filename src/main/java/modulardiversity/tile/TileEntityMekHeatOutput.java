package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import mekanism.api.IHeatTransfer;
import modulardiversity.components.MachineComponents;
import modulardiversity.components.requirements.RequirementHotAir;
import modulardiversity.components.requirements.RequirementMekHeat;
import modulardiversity.tile.base.TileEntityMekHeat;
import modulardiversity.util.HeatUtils;
import modulardiversity.util.ICraftingResourceHolder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import javax.annotation.Nullable;

public class TileEntityMekHeatOutput extends TileEntityMekHeat {
    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new MachineComponents.MekHeatHatch(MachineComponent.IOType.OUTPUT) {
            @Override
            public ICraftingResourceHolder<RequirementMekHeat.ResourceToken> getContainerProvider() {
                return TileEntityMekHeatOutput.this;
            }
        };
    }

    @Override
    public boolean consume(RequirementMekHeat.ResourceToken token, boolean doConsume) {
        return false;
    }

    @Override
    public boolean generate(RequirementMekHeat.ResourceToken token, boolean doGenerate) {
        if(temperature < token.getRequiredTemperatureMin() || temperature > token.getRequiredTemperatureMax())
            return false;
        token.setTemperatureMet();
        if(doGenerate)
            transferHeatTo(token.getTemperature());
        return true;
    }
}
