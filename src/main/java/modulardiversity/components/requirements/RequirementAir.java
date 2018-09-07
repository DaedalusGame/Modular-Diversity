package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentOutputRestrictor;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.util.ResultChance;
import modulardiversity.jei.JEIComponentAir;
import modulardiversity.jei.ingredients.Air;
import modulardiversity.tile.base.TileEntityEmber;
import modulardiversity.util.IResourceToken;

import javax.annotation.Nonnull;
import java.util.List;

public class RequirementAir extends RequirementConsumePerTick<Air, RequirementAir.ResourceToken> {
    public final int volumeConsumed;
    public final float pressureRequired;

    public RequirementAir(MachineComponent.IOType actionType, int volumeConsumed, float pressureRequired) {
        super(ComponentType.Registry.getComponent("pneumatic_air"), actionType);
        this.volumeConsumed = volumeConsumed;
        this.pressureRequired = pressureRequired;
    }

    @Override
    protected ResourceToken emitConsumptionToken() {
        return new ResourceToken(volumeConsumed,pressureRequired);
    }

    @Override
    protected boolean isCorrectHatch(MachineComponent component) {
        return component.getComponentType().getRegistryName().equals("pneumatic_air") &&
                component instanceof TileEntityEmber.Component &&
                component.getIOType() == getActionType();
    }

    @Override
    public ComponentRequirement deepCopy() {
        return new RequirementAir(getActionType(),volumeConsumed,pressureRequired);
    }

    @Override
    public JEIComponent<Air> provideJEIComponent() {
        return new JEIComponentAir(this);
    }

    public static class ResourceToken implements IResourceToken
    {
        private int volumeConsumed;
        private float pressureRequired;

        public ResourceToken(int volumeConsumed, float pressureRequired) {
            this.volumeConsumed = volumeConsumed;
            this.pressureRequired = pressureRequired;
        }

        public int getVolumeConsumed() {
            return volumeConsumed;
        }

        public void setVolumeConsumed(int volumeConsumed) {
            this.volumeConsumed = volumeConsumed;
        }

        public float getPressureRequired() {
            return pressureRequired;
        }

        @Override
        public float getModifier() {
            return (float)volumeConsumed;
        }

        @Override
        public void setModifier(float modifier) {
            volumeConsumed = (int) modifier;
        }

        @Override
        public boolean isEmpty() {
            return volumeConsumed <= 0;
        }
    }
}
