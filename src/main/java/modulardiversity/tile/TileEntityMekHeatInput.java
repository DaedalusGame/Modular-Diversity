package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.MachineComponents;
import modulardiversity.components.requirements.RequirementMekHeat;
import modulardiversity.tile.base.TileEntityMekHeat;
import modulardiversity.util.ICraftingResourceHolder;

import javax.annotation.Nullable;

public class TileEntityMekHeatInput extends TileEntityMekHeat {
    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new MachineComponents.MekHeatHatch(MachineComponent.IOType.INPUT) {
            @Override
            public ICraftingResourceHolder<RequirementMekHeat.ResourceToken> getContainerProvider() {
                return TileEntityMekHeatInput.this;
            }
        };
    }

    @Override
    public boolean consume(RequirementMekHeat.ResourceToken token, boolean doConsume) {
        if(temperature < token.getRequiredTemperatureMin() || temperature > token.getRequiredTemperatureMax())
            return false;
        token.setTemperatureMet();
        if(doConsume)
            transferHeatTo(token.getTemperature());
        return true;
    }

    @Override
    public boolean generate(RequirementMekHeat.ResourceToken token, boolean doGenerate) {
        return false;
    }
}
