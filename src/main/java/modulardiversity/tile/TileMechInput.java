package modulardiversity.tile;

import betterwithmods.api.BWMAPI;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.MachineComponents;
import modulardiversity.components.requirements.RequirementMechanical;
import modulardiversity.tile.base.TileEntityMech;
import modulardiversity.util.ICraftingResourceHolder;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.Optional;

import javax.annotation.Nullable;

@Optional.Interface(iface = "betterwithmods.api.tile.IMechanicalPower",modid = "betterwithmods")
public class TileMechInput extends TileEntityMech {
    public TileMechInput() {
        super();
    }

    public TileMechInput(int maxLevel) {
        super(maxLevel);
    }

    @Optional.Method(modid = "betterwithmods")
    @Override
    public int getMechanicalOutput(EnumFacing enumFacing) {
        return -1;
    }

    @Optional.Method(modid = "betterwithmods")
    @Override
    public int getMechanicalInput(EnumFacing enumFacing) {
        return BWMAPI.IMPLEMENTATION.getPowerOutput(world, pos.offset(enumFacing), enumFacing.getOpposite());
    }

    public int getCurrentEnergy() {
        return calculateInput();
    }

    @Override
    public boolean consume(RequirementMechanical.ResourceToken token, boolean doConsume) {
        if(getCurrentEnergy() >= token.getRequiredLevel())
            token.setRequiredlevelMet();
        return true;
    }

    @Override
    public boolean generate(RequirementMechanical.ResourceToken token, boolean doGenerate) {
        return false;
    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new MachineComponents.MechanicalHatch(MachineComponent.IOType.INPUT) {
            @Override
            public ICraftingResourceHolder<RequirementMechanical.ResourceToken> getContainerProvider() {
                return TileMechInput.this;
            }
        };
    }
}
