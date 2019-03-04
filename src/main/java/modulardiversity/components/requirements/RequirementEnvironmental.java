package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentOutputRestrictor;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.util.ResultChance;
import modulardiversity.util.IResourceToken;

import javax.annotation.Nonnull;
import java.util.List;

public abstract class RequirementEnvironmental<T, V extends IResourceToken> extends ComponentRequirement.PerTick<T> {
    V checkToken;
    V outputToken;
    V perTickToken;

    boolean perTick;

    public RequirementEnvironmental(ComponentType componentType, MachineComponent.IOType actionType) {
        super(componentType, actionType);
    }

    @Override
    public boolean startCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance resultChance) {
        if (perTick)
            return true;
        switch (getActionType()) {
            case INPUT:
                consumeToken(component, context, outputToken, true);
                return outputToken.isEmpty();
        }
        return false;
    }

    @Override
    public boolean finishCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance resultChance) {
        if (perTick)
            return true;
        switch (getActionType()) {
            case OUTPUT:
                generateToken(component, context, outputToken, true);
                return outputToken.isEmpty();
        }
        return false;
    }

    @Override
    public CraftCheck canStartCrafting(MachineComponent component, RecipeCraftingContext context, List<ComponentOutputRestrictor> list) {
        switch (getActionType()) {
            case INPUT:
                boolean didConsume = consumeToken(component, context, checkToken, false);
                if (!didConsume) {
                    return CraftCheck.FAILURE_MISSING_INPUT;
                } else if (checkToken.isEmpty()) {
                    return CraftCheck.SUCCESS;
                } else {
                    return CraftCheck.PARTIAL_SUCCESS;
                }
            case OUTPUT:
                generateToken(component, context, checkToken, false);
                if (checkToken.isEmpty()) {
                    return CraftCheck.SUCCESS;
                } else {
                    return CraftCheck.PARTIAL_SUCCESS;
                }
        }
        return CraftCheck.FAILURE_MISSING_INPUT;
    }

    @Override
    public void startRequirementCheck(ResultChance resultChance, RecipeCraftingContext context) {
        checkToken = emitConsumptionToken(context);
        checkToken.setModifier(context.applyModifiers(this, getActionType(), checkToken.getModifier(), false));
        outputToken = emitConsumptionToken(context);
        outputToken.setModifier(context.applyModifiers(this, getActionType(), outputToken.getModifier(), false));
    }

    @Override
    public void endRequirementCheck() {
        checkToken = emitConsumptionToken(null);
        outputToken = emitConsumptionToken(null);
    }

    @Override
    public void startIOTick(RecipeCraftingContext context, float durationMultiplier) {
        this.perTickToken.setModifier(context.applyModifiers(this, getActionType(), this.perTickToken.getModifier(), false) * durationMultiplier);
    }

    @Override
    public void resetIOTick(RecipeCraftingContext context) {
        this.perTickToken = emitConsumptionToken(context);
    }

    @Nonnull
    @Override
    public CraftCheck doIOTick(MachineComponent component, RecipeCraftingContext context) {
        if (!perTick)
            return CraftCheck.SUCCESS;
        switch (getActionType()) {
            case INPUT:
                boolean didConsume = consumeToken(component, context, perTickToken, true);
                if (!didConsume) {
                    return CraftCheck.FAILURE_MISSING_INPUT;
                } else if (perTickToken.isEmpty()) {
                    return CraftCheck.SUCCESS;
                } else {
                    return CraftCheck.PARTIAL_SUCCESS;
                }
            case OUTPUT:
                generateToken(component, context, perTickToken, true);
                if (perTickToken.isEmpty()) {
                    return CraftCheck.SUCCESS;
                } else {
                    return CraftCheck.PARTIAL_SUCCESS;
                }
        }
        //This is neither input nor output? when do we actually end up in this case down here?
        return CraftCheck.INVALID_SKIP;
    }

    protected abstract V emitConsumptionToken(RecipeCraftingContext context);

    protected abstract boolean consumeToken(MachineComponent component, RecipeCraftingContext context, V token, boolean doConsume);

    protected abstract boolean generateToken(MachineComponent component, RecipeCraftingContext context, V token, boolean doGenerate);
}
