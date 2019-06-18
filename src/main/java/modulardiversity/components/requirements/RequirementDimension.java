package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import modulardiversity.jei.JEIComponentDimension;
import modulardiversity.jei.ingredients.DimensionIngredient;
import modulardiversity.util.IResourceToken;
import modulardiversity.util.Misc;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class RequirementDimension extends RequirementEnvironmental<DimensionIngredient, RequirementDimension.ResourceToken> {
    private HashSet<Integer> dimensions;

    public RequirementDimension(MachineComponent.IOType actionType, int biome) {
        super(ComponentType.Registry.getComponent("dimension"), actionType);
        dimensions = new HashSet<>();
        this.dimensions.add(biome);
    }

    public RequirementDimension(MachineComponent.IOType actionType, Collection<Integer> dimensions) {
        super(ComponentType.Registry.getComponent("dimension"), actionType);
        this.dimensions = new HashSet<>(dimensions);
    }

    public RequirementDimension(MachineComponent.IOType actionType, int[] dimensions) {
        super(ComponentType.Registry.getComponent("dimension"), actionType);
        this.dimensions = new HashSet<>();
        for (int dimension : dimensions) {
            this.dimensions.add(dimension);
        }
    }

    /*@Override
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
    }*/

    @Override
    public ComponentRequirement<DimensionIngredient> deepCopy() {
        return new RequirementDimension(this.getActionType(), this.dimensions);
    }

    @Override
    public ComponentRequirement<DimensionIngredient> deepCopyModified(List<RecipeModifier> modifiers) {
        return new RequirementDimension(this.getActionType(), this.dimensions);
    }

    /*@Override
    public void startRequirementCheck(ResultChance resultChance, RecipeCraftingContext recipeCraftingContext) {
    }

    @Override
    public void endRequirementCheck() {
    }*/

    @Override
    protected String getInputProblem(ResourceToken token) {
        return "craftcheck.dimension";
    }

    @Override
    protected String getOutputProblem(ResourceToken token) {
        return null;
    }

    @Override
    protected ResourceToken emitConsumptionToken(RecipeCraftingContext context) {
        return new ResourceToken();
    }

    @Override
    protected boolean consumeToken(MachineComponent component, RecipeCraftingContext context, ResourceToken token, boolean doConsume) {
        TileEntity tile = Misc.getTileEntity(component);
        if (tile != null && isValidDimension(tile.getWorld(), tile.getPos())) {
            token.setRequirementMet();
            return true;
        }
        return false;
    }

    private boolean isValidDimension(World world, BlockPos pos) {
        return dimensions.contains(world.provider.getDimension());
    }

    @Override
    protected boolean generateToken(MachineComponent component, RecipeCraftingContext context, ResourceToken token, boolean doGenerate) {
        return false; //Definitely not valid
    }

    @Override
    public JEIComponent<DimensionIngredient> provideJEIComponent() {
        return new JEIComponentDimension(this);
    }

    public Collection<Integer> getDimensions() {
        return this.dimensions.stream().sorted().collect(Collectors.toList());
    }

    public static class ResourceToken implements IResourceToken {
        private boolean requirementMet;

        public ResourceToken() {
        }

        public void setRequirementMet() {
            requirementMet = true;
        }

        @Override
        public void applyModifiers(RecipeCraftingContext modifiers, MachineComponent.IOType ioType, float durationMultiplier) {

        }

        @Override
        public boolean isEmpty() {
            return requirementMet;
        }
    }
}
