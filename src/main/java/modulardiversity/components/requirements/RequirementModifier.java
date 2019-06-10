package modulardiversity.components.requirements;

import flaxbeard.immersivepetroleum.api.crafting.PumpjackHandler;
import flaxbeard.immersivepetroleum.api.crafting.PumpjackHandler.OilWorldInfo;
import flaxbeard.immersivepetroleum.api.crafting.PumpjackHandler.ReservoirType;
import flaxbeard.immersivepetroleum.common.IPSaveData;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import modulardiversity.jei.JEIComponentModifier;
import modulardiversity.jei.ingredients.Modifier;
import modulardiversity.jei.ingredients.Reservoir;
import modulardiversity.util.IResourceToken;
import modulardiversity.util.Misc;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

import java.util.List;

public class RequirementModifier extends RequirementEnvironmental<Modifier, RequirementModifier.ResourceToken> {
    public String name;
    public float min, max;

    public RequirementModifier(MachineComponent.IOType actionType, String name, float min, float max) {
        super(ComponentType.Registry.getComponent("modifier"), actionType);
        this.name = name;
        this.min = min;
        this.max = max;
    }

    @Override
    protected String getInputProblem(ResourceToken token) {
        return "craftcheck.failure.modifier";
    }

    @Override
    protected String getOutputProblem(ResourceToken token) {
        return null;
    }

    @Override
    protected ResourceToken emitConsumptionToken(RecipeCraftingContext context) {
        return new ResourceToken(name, min,max);
    }

    @Override
    protected boolean consumeToken(MachineComponent component, RecipeCraftingContext context, ResourceToken token, boolean doConsume) {
        return token.isEmpty();
    }

    @Override
    protected boolean generateToken(MachineComponent component, RecipeCraftingContext context, ResourceToken token, boolean doGenerate) {
        return token.isEmpty();
    }

    @Override
    public ComponentRequirement<Modifier> deepCopy() {
        return new RequirementModifier(getActionType(),name,min,max);
    }

    @Override
    public ComponentRequirement<Modifier> deepCopyModified(List<RecipeModifier> modifiers) {
        return new RequirementModifier(getActionType(), name, min, max);
    }

    @Override
    public JEIComponent<Modifier> provideJEIComponent() {
        return new JEIComponentModifier(this);
    }

    public static class ResourceToken implements IResourceToken
    {
        private String name;
        private float min, max;
        private boolean matched;

        public ResourceToken(String name, float min, float max) {
            this.name = name;
            this.min = min;
            this.max = max;
        }

        @Override
        public void applyModifiers(RecipeCraftingContext modifiers, MachineComponent.IOType ioType, float durationMultiplier) {
            float v = Misc.applyModifiers(modifiers, name, ioType, 1.0f, false);
            if(v >= min && v <= max)
                matched = true;
        }

        @Override
        public boolean isEmpty() {
            return matched;
        }
    }
}
