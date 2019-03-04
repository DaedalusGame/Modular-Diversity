package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentOutputRestrictor;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.util.ResultChance;
import modulardiversity.components.MachineComponents.BiomeDetector;
import modulardiversity.components.MachineComponents.DaylightDetector;
import modulardiversity.components.ComponentDaylight;
import modulardiversity.jei.JEIComponentDaylight;
import modulardiversity.jei.ingredients.DaylightIngredient;
import modulardiversity.util.IResourceToken;
import modulardiversity.util.Misc;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;

public class RequirementDaylight extends RequirementEnvironmental<DaylightIngredient,RequirementDaylight.ResourceToken> {

    private long timeMin, timeMax;
    private long timeModulo;

    private boolean isTimeValid(long time)
    {
        time = time % timeModulo;
        if(time > Math.min(timeMin,timeMax) && time <= Math.max(timeMin,timeMax))
            return timeMin < timeMax;
        return timeMin >= timeMax;
    }

    public RequirementDaylight(ComponentType componentType, MachineComponent.IOType actionType, long min, long max, long modulo) {
        super(componentType, MachineComponent.IOType.INPUT);
        timeMin = min;
        timeMax = max;
        timeModulo = modulo;
    }

    @Override
    protected boolean consumeToken(MachineComponent component, RecipeCraftingContext context, ResourceToken token, boolean doConsume) {
        TileEntity tile = Misc.getTileEntity(component);
        if(tile != null && isTimeValid(tile.getWorld().getTotalWorldTime()))
            token.setRequirementMet();
        return true;
    }

    @Override
    protected boolean generateToken(MachineComponent component, RecipeCraftingContext context, ResourceToken token, boolean doGenerate) {
        return false; //Don't feel comfortable doing this
    }

    @Override
    protected ResourceToken emitConsumptionToken(RecipeCraftingContext context) {
        return new ResourceToken();
    }

    @Override
    public ComponentRequirement deepCopy() {
        return new RequirementDaylight(new ComponentDaylight(), this.getActionType(), timeMin, timeMax, timeModulo);
    }

    @Override
    public JEIComponent<DaylightIngredient> provideJEIComponent() {
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
        public float getModifier() {
            return 0;
        }

        @Override
        public void setModifier(float modifier) {
            //NOOP;
        }

        @Override
        public boolean isEmpty() {
            return requirementMet;
        }
    }
}
