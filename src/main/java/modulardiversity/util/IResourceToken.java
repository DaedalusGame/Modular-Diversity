package modulardiversity.util;

import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;

public interface IResourceToken {
    void applyModifiers(RecipeCraftingContext modifiers, RequirementType target, IOType ioType, float durationMultiplier);

    default void applyModifiers(RecipeCraftingContext modifiers, RequirementType target, IOType ioType) {
        applyModifiers(modifiers, target, ioType,1.0f);
    }

    boolean isEmpty();
}
