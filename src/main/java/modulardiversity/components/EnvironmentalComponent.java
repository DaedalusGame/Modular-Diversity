package modulardiversity.components;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.TileMachineController;

public class EnvironmentalComponent extends MachineComponent<TileMachineController> {
    TileMachineController controller;
    ComponentType componentType;

    public EnvironmentalComponent(TileMachineController controller, ComponentType componentType) {
        super(IOType.INPUT); //Don't care if input or output, it's not a pre-fail condition
        this.controller = controller;
        this.componentType = componentType;
    }

    @Override
    public ComponentType getComponentType() {
        return componentType;
    }

    @Override
    public TileMachineController getContainerProvider() {
        return controller;
    }

    public static void attach(ComponentType componentType, RecipeCraftingContext context)
    {
        if(!context.getComponentsFor(componentType,null).iterator().hasNext())
            context.addComponent(new EnvironmentalComponent(context.getMachineController(),componentType),null);
    }
}
