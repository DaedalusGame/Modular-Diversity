package modulardiversity.util;

import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent.IOType;

public interface IResourceToken {
    void applyModifiers(RecipeCraftingContext modifiers, IOType ioType, float durationMultiplier);

    default void applyModifiers(RecipeCraftingContext modifiers, IOType ioType) {
        applyModifiers(modifiers,ioType,1.0f);
    }

    boolean isEmpty();
}
