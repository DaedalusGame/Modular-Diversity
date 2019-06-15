package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import modulardiversity.jei.JEIComponentEmberWorld;
import modulardiversity.jei.JEIComponentMineral;
import modulardiversity.jei.ingredients.EmberWorld;
import modulardiversity.jei.ingredients.Mineral;
import modulardiversity.util.IResourceToken;
import modulardiversity.util.Misc;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamroots.embers.util.EmberGenUtil;

import java.util.List;

public class RequirementEmberWorld extends RequirementEnvironmental<EmberWorld, RequirementEmberWorld.ResourceToken> {
    public float emberMin, emberMax;
    public float stabilityMin, stabilityMax;

    public RequirementEmberWorld(MachineComponent.IOType actionType, float emberMin, float emberMax, float stabilityMin, float stabilityMax) {
        super(ComponentType.Registry.getComponent("mantle"), actionType);
        this.emberMin = emberMin;
        this.emberMax = emberMax;
        this.stabilityMin = stabilityMin;
        this.stabilityMax = stabilityMax;
    }

    @Override
    protected String getInputProblem(ResourceToken token) {
        if(token.isEmberMatched() && !token.isStabilityMatched())
            return "craftcheck.ember_stability";
        else
            return "craftcheck.ember";
    }

    @Override
    protected String getOutputProblem(ResourceToken token) {
        return null;
    }

    @Override
    protected ResourceToken emitConsumptionToken(RecipeCraftingContext context) {
        return new ResourceToken(emberMin, emberMax, stabilityMin, stabilityMax);
    }

    @Override
    protected boolean consumeToken(MachineComponent component, RecipeCraftingContext context, ResourceToken token, boolean doConsume) {
        TileEntity tile = Misc.getTileEntity(component);
        if(tile != null)
        {
            World world = tile.getWorld();
            BlockPos pos = tile.getPos();
            float density = EmberGenUtil.getEmberDensity(world.getSeed(),pos.getX(),pos.getZ());
            float stability = EmberGenUtil.getEmberStability(world.getSeed(),pos.getX(),pos.getZ());

            if(density >= emberMin && density <= emberMax)
                token.setEmberMatched(true);
            if(stability >= stabilityMin && stability <= stabilityMax)
                token.setStabilityMatched(true);
        }
        return token.isEmpty();
    }

    @Override
    protected boolean generateToken(MachineComponent component, RecipeCraftingContext context, ResourceToken token, boolean doGenerate) {
        return true;
    }

    @Override
    public ComponentRequirement<EmberWorld> deepCopy() {
        return new RequirementEmberWorld(getActionType(),emberMin, emberMax,stabilityMin,stabilityMax);
    }

    @Override
    public ComponentRequirement<EmberWorld> deepCopyModified(List<RecipeModifier> modifiers) {
        return new RequirementEmberWorld(getActionType(),
                Misc.applyModifiers(modifiers,"ember_min",getActionType(), emberMin,false),
                Misc.applyModifiers(modifiers,"ember_max",getActionType(), emberMax,false),
                Misc.applyModifiers(modifiers,"ember_stability_min",getActionType(), stabilityMin,false),
                Misc.applyModifiers(modifiers,"ember_stability_max",getActionType(), stabilityMax,false)
        );
    }

    @Override
    public JEIComponent<EmberWorld> provideJEIComponent() {
        return new JEIComponentEmberWorld(this);
    }

    public static class ResourceToken implements IResourceToken
    {
        public float emberMin, emberMax;
        public float stabilityMin, stabilityMax;
        boolean emberMatched;
        boolean stabilityMatched;

        public ResourceToken(float emberMin, float emberMax, float stabilityMin, float stabilityMax) {
            this.emberMin = emberMin;
            this.emberMax = emberMax;
            this.stabilityMin = stabilityMin;
            this.stabilityMax = stabilityMax;
        }

        public boolean isEmberMatched() {
            return emberMatched;
        }

        public void setEmberMatched(boolean emberMatched) {
            this.emberMatched = emberMatched;
        }

        public boolean isStabilityMatched() {
            return stabilityMatched;
        }

        public void setStabilityMatched(boolean stabilityMatched) {
            this.stabilityMatched = stabilityMatched;
        }

        @Override
        public void applyModifiers(RecipeCraftingContext modifiers, MachineComponent.IOType ioType, float durationMultiplier) {
            emberMin =  Misc.applyModifiers(modifiers,"ember_min",ioType, emberMin,false);
            emberMax =  Misc.applyModifiers(modifiers,"ember_max",ioType, emberMax,false);
            stabilityMin =  Misc.applyModifiers(modifiers,"ember_stability_min",ioType, stabilityMin,false);
            stabilityMax =  Misc.applyModifiers(modifiers,"ember_stability_max",ioType, stabilityMax,false);
        }

        @Override
        public boolean isEmpty() {
            return emberMatched && stabilityMatched;
        }
    }
}
