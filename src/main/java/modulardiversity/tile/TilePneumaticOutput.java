package modulardiversity.tile;

import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.ModularDiversity;
import modulardiversity.components.MachineComponents;
import modulardiversity.components.requirements.RequirementAir;
import modulardiversity.tile.base.TileEntityPneumaticBase;
import modulardiversity.util.ICraftingResourceHolder;

import javax.annotation.Nullable;

public class TilePneumaticOutput extends TileEntityPneumaticBase {
    public TilePneumaticOutput(int tier, int volume) {
        super(MachineComponent.IOType.OUTPUT, tier, volume);
    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new MachineComponents.AirHatch(MachineComponent.IOType.OUTPUT) {
            @Override
            public ICraftingResourceHolder<RequirementAir.ResourceToken> getContainerProvider() {
                return null;
            }
        };
    }
}
