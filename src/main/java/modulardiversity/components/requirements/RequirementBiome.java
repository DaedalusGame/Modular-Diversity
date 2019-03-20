package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import modulardiversity.jei.JEIComponentBiome;
import modulardiversity.jei.ingredients.BiomeIngredient;
import modulardiversity.util.IResourceToken;
import modulardiversity.util.Misc;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class RequirementBiome extends RequirementEnvironmental<BiomeIngredient,RequirementBiome.ResourceToken> {

    private HashSet<Integer> biomes;


    public RequirementBiome(MachineComponent.IOType actionType, int biome) {
        super(ComponentType.Registry.getComponent("biome"), actionType);
        biomes = new HashSet<>();
        this.biomes.add(biome);
    }

    public RequirementBiome(MachineComponent.IOType actionType, Collection<Integer> biomes) {
        super(ComponentType.Registry.getComponent("biome"), actionType);
        this.biomes = new HashSet<>(biomes);
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
    public ComponentRequirement<BiomeIngredient> deepCopy() {
        return new RequirementBiome(this.getActionType(), this.biomes);
    }

    @Override
    public ComponentRequirement<BiomeIngredient> deepCopyModified(List<RecipeModifier> modifiers) {
        return new RequirementBiome(this.getActionType(), this.biomes);
    }

    /*@Override
    public void startRequirementCheck(ResultChance resultChance, RecipeCraftingContext recipeCraftingContext) {
    }

    @Override
    public void endRequirementCheck() {
    }*/

    @Override
    protected ResourceToken emitConsumptionToken(RecipeCraftingContext context) {
        return new ResourceToken();
    }

    @Override
    protected boolean consumeToken(MachineComponent component, RecipeCraftingContext context, ResourceToken token, boolean doConsume) {
        TileEntity tile = Misc.getTileEntity(component);
        if (tile != null && isValidBiome(tile.getWorld(), tile.getPos()))
            token.setRequirementMet();
        return true;
    }

    private boolean isValidBiome(World world, BlockPos pos) {
        return biomes.contains(Biome.getIdForBiome(world.getBiome(pos)));
    }

    @Override
    protected boolean generateToken(MachineComponent component, RecipeCraftingContext context, ResourceToken token, boolean doGenerate) {
        return false; //Definitely not valid
    }

    @Override
    public JEIComponent<BiomeIngredient> provideJEIComponent() {
        return new JEIComponentBiome(this);
    }

    public Collection<Integer> getBiomes() {
        return this.biomes.stream().sorted().collect(Collectors.toList());
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
