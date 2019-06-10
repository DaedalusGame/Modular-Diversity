package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType.Registry;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import modulardiversity.jei.JEIComponentDaylight;
import modulardiversity.jei.ingredients.Daylight;
import modulardiversity.util.IResourceToken;
import modulardiversity.util.Misc;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

public class RequirementDaylight extends RequirementEnvironmental<Daylight,RequirementDaylight.ResourceToken> {

    public long timeMin, timeMax;
    public long timeModulo;
    public boolean timeLocal;

    private boolean isTimeValid(long time)
    {
        time = time % timeModulo;
        if(time > Math.min(timeMin,timeMax) && time <= Math.max(timeMin,timeMax))
            return timeMin < timeMax;
        else
            return timeMin >= timeMax;
    }

    public RequirementDaylight(MachineComponent.IOType actionType, long min, long max, long modulo, boolean local) {
        super(Registry.getComponent("daylight"), actionType);
        timeMin = min;
        timeMax = max;
        timeModulo = modulo;
        timeLocal = local;
    }

    @Override
    protected boolean consumeToken(MachineComponent component, RecipeCraftingContext context, ResourceToken token, boolean doConsume) {
        TileEntity tile = Misc.getTileEntity(component);
        if(tile != null){
            World world = tile.getWorld();
            long time = world.getTotalWorldTime();
            if(timeLocal) {
                time -= time % 24000;
                time += world.getWorldTime();
            }
            if(isTimeValid(time)) {
                token.setRequirementMet();
                return true;
            }
        }

        return false;
    }

    @Override
    protected boolean generateToken(MachineComponent component, RecipeCraftingContext context, ResourceToken token, boolean doGenerate) {
        return false; //Don't feel comfortable doing this
    }

    @Override
    protected String getInputProblem(ResourceToken token) {
        return "craftcheck.time";
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
    public ComponentRequirement deepCopy() {
        return new RequirementDaylight(this.getActionType(), timeMin, timeMax, timeModulo, timeLocal);
    }

    @Override
    public ComponentRequirement<Daylight> deepCopyModified(List<RecipeModifier> modifiers) {
        return new RequirementDaylight(this.getActionType(), timeMin, timeMax, timeModulo, timeLocal);
    }

    @Override
    public JEIComponent<Daylight> provideJEIComponent() {
        return new JEIComponentDaylight(this);
    }

    /*@Override
    public boolean startCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance chance) {
        if (component.getComponentType().equals(this.getRequiredComponentType()) && component instanceof DaylightDetector && component.getIOType() == this.getActionType()) {
            long trueDaylight = ((DaylightDetector) component).getContainerProvider();
            int tempDaylightEnd = daylight.get(1);
            if (daylight.get(0) > tempDaylightEnd) {
                tempDaylightEnd += 24000;
                if (trueDaylight < daylight.get(0)) trueDaylight += 24000;
            }
            return this.daylight.get(0) < trueDaylight && trueDaylight < tempDaylightEnd;
        }
        return false;
    }

    @Override
    public boolean finishCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance chance) {
        if (component.getComponentType().equals(this.getRequiredComponentType()) && component instanceof BiomeDetector && component.getIOType() == this.getActionType()) {
            return true;
        }
        return false;
    }

    @Override
    public CraftCheck canStartCrafting(MachineComponent component, RecipeCraftingContext context, List<ComponentOutputRestrictor> list) {
        if (component.getComponentType().equals(this.getRequiredComponentType()) && component instanceof DaylightDetector && component.getIOType() == this.getActionType()) {
            long trueDaylight = ((DaylightDetector) component).getContainerProvider();
            int tempDaylightEnd = daylight.get(1);
            if (daylight.get(0) > tempDaylightEnd) {
                tempDaylightEnd += 24000;
                if (trueDaylight < daylight.get(0)) trueDaylight += 24000;
            }
            return this.daylight.get(0) < trueDaylight && trueDaylight < tempDaylightEnd ? CraftCheck.SUCCESS : CraftCheck.PARTIAL_SUCCESS;
        }
        return CraftCheck.INVALID_SKIP;
    }*/



    /*@Override
    public void startRequirementCheck(ResultChance resultChance, RecipeCraftingContext recipeCraftingContext) {
    }

    @Override
    public void endRequirementCheck() {
    }*/

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
