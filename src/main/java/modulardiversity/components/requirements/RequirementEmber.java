package modulardiversity.components.requirements;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.lib.RegistriesMM;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import modulardiversity.components.MachineComponents;
import modulardiversity.jei.JEIComponentEmber;
import modulardiversity.jei.ingredients.Embers;
import modulardiversity.util.IResourceToken;
import modulardiversity.util.Misc;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class RequirementEmber extends RequirementConsumeOnce<Embers,RequirementEmber.ResourceToken,RequirementEmber.Type> {
    static ResourceLocation resourceLocation;

    public static class Type extends RequirementType<Embers,RequirementEmber> {

        @Override
        public RequirementEmber createRequirement(IOType ioType, JsonObject jsonObject) {
            if(jsonObject.has("ember") && jsonObject.get("ember").isJsonPrimitive() && jsonObject.get("ember").getAsJsonPrimitive().isNumber()) {
                float emberRequired = jsonObject.getAsJsonPrimitive("ember").getAsFloat();
                return new RequirementEmber(ioType, emberRequired);
            } else {
                throw new JsonParseException("The ComponentType \'"+getRegistryName()+"\' expects a \'ember\'-entry that defines the required ember!");
            }
        }
    }

    public double requiredEmber;

    public RequirementEmber(IOType actionType, double requiredEmber) {
        super((Type) RegistriesMM.REQUIREMENT_TYPE_REGISTRY.getValue(resourceLocation), actionType);
        this.requiredEmber = requiredEmber;
    }

    @Override
    public ComponentRequirement deepCopy() {
        return new RequirementEmber(getActionType(),requiredEmber);
    }

    @Override
    public ComponentRequirement<Embers,Type> deepCopyModified(List<RecipeModifier> modifiers) {
        return new RequirementEmber(getActionType(),Misc.applyModifiers(modifiers,getRequirementType(),getActionType(),requiredEmber,false));
    }

    @Override
    public JEIComponent<Embers> provideJEIComponent() {
        return new JEIComponentEmber(this);
    }

    @Override
    protected boolean isCorrectHatch(MachineComponent component) {
        return component.getComponentType().getRegistryName().equals("ember") &&
                component instanceof MachineComponents.EmberHatch &&
                component.getIOType() == getActionType();
    }

    @Override
    protected ResourceToken emitConsumptionToken(RecipeCraftingContext context) {
        return new ResourceToken(requiredEmber);
    }

    public static class ResourceToken implements IResourceToken
    {
        private double ember;

        public ResourceToken(double ember) {
            this.ember = ember;
        }

        public double getEmber() {
            return ember;
        }

        public void setEmber(double ember) {
            this.ember = ember;
        }

        @Override
        public void applyModifiers(RecipeCraftingContext modifiers, RequirementType type, IOType ioType, float durationMultiplier) {
            ember = Misc.applyModifiers(modifiers,type,ioType,ember,false);
        }

        @Override
        public boolean isEmpty() {
            return ember <= 0;
        }
    }
}
