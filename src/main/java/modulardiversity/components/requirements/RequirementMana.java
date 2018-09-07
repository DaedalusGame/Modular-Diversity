package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.jei.JEIComponentMana;
import modulardiversity.jei.ingredients.Mana;
import modulardiversity.tile.base.TileEntityMana;
import modulardiversity.util.IResourceToken;

public class RequirementMana extends RequirementConsumeOnce<Mana,RequirementMana.ResourceToken> {
    public int requiredMana;

    public RequirementMana(MachineComponent.IOType actionType, int requiredMana) {
        super(ComponentType.Registry.getComponent("mana"), actionType);
        this.requiredMana = requiredMana;
    }

    @Override
    protected boolean isCorrectHatch(MachineComponent component) {
        return component.getComponentType().getRegistryName().equals("mana") &&
                component instanceof TileEntityMana.Component &&
                component.getIOType() == getActionType();
    }

    @Override
    protected ResourceToken emitConsumptionToken() {
        return new ResourceToken(requiredMana);
    }

    @Override
    public ComponentRequirement deepCopy() {
        return new RequirementMana(getActionType(),requiredMana);
    }

    @Override
    public JEIComponent<Mana> provideJEIComponent() {
        return new JEIComponentMana(this);
    }

    public static class ResourceToken implements IResourceToken {
        private int mana;

        public ResourceToken(int mana) {
            this.mana = mana;
        }

        public int getMana() {
            return mana;
        }

        public void setMana(int mana) {
            this.mana = mana;
        }

        @Override
        public float getModifier() {
            return (float)mana;
        }

        @Override
        public void setModifier(float modifier) {
            mana = (int) modifier;
        }

        @Override
        public boolean isEmpty() {
            return mana <= 0;
        }
    }
}
