package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentOutputRestrictor;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.util.ResultChance;
import modulardiversity.components.MachineComponents.BiomeDetector;
import modulardiversity.components.ComponentBiome;
import modulardiversity.jei.JEIComponentBiome;
import modulardiversity.jei.ingredients.BiomeIngredient;

import java.util.ArrayList;
import java.util.List;

public class RequirementBiome extends ComponentRequirement<BiomeIngredient> {

    private ArrayList<Integer> biomes;


    public RequirementBiome(ComponentType componentType, MachineComponent.IOType actionType, int biome) {
        super(componentType, MachineComponent.IOType.INPUT);
        biomes = new ArrayList<>();
        this.biomes.add(biome);
    }

    public RequirementBiome(ComponentBiome componentType, MachineComponent.IOType actionType, ArrayList<Integer> biomesArrayList) {
        super(componentType, MachineComponent.IOType.INPUT);
        this.biomes = biomesArrayList;
    }

    @Override
    public boolean startCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance chance) {
        if (component.getComponentType().equals(this.getRequiredComponentType()) && component instanceof BiomeDetector && component.getIOType() == this.getActionType()) {
            boolean canStart = false;
            for (Integer i:biomes) {
                if (((BiomeDetector) component).getContainerProvider() == i) canStart = true;
            }
            return canStart;
        }
        return false;
    }

    @Override
    public boolean finishCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance chance) {
        if (component.getComponentType().equals(this.getRequiredComponentType()) && component instanceof BiomeDetector && component.getIOType() == this.getActionType()) {
            boolean canStart = false;
            for (Integer i:biomes) {
                if (((BiomeDetector) component).getContainerProvider() == i) canStart = true;
            }
            return canStart;
        }
        return false;
    }

    @Override
    public CraftCheck canStartCrafting(MachineComponent component, RecipeCraftingContext context, List<ComponentOutputRestrictor> list) {
        if (component.getComponentType().equals(this.getRequiredComponentType()) && component instanceof BiomeDetector && component.getIOType() == this.getActionType()) {
            boolean canStart = false;
            for (Integer i:biomes) {
                if (((BiomeDetector) component).getContainerProvider() == i) canStart = true;
            }
            return canStart ? CraftCheck.SUCCESS : CraftCheck.FAILURE_MISSING_INPUT;
        }
        return CraftCheck.INVALID_SKIP;
    }

    @Override
    public ComponentRequirement deepCopy() {
        RequirementBiome requirementBiome = new RequirementBiome(new ComponentBiome(), this.getActionType(), this.biomes);
        return requirementBiome;
    }

    @Override
    public void startRequirementCheck(ResultChance resultChance, RecipeCraftingContext recipeCraftingContext) {
    }

    @Override
    public void endRequirementCheck() {
    }

    @Override
    public JEIComponent<BiomeIngredient> provideJEIComponent() {
        return new JEIComponentBiome(this);
    }

    public ArrayList<Integer> getBiomes() {
        return this.biomes;
    }
}
