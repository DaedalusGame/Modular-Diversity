package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentOutputRestrictor;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.util.ResultChance;
import modulardiversity.components.EnvironmentalComponent;
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

    public RequirementEnvironmental<T,V> setPerTick(boolean perTick)
    {
        this.perTick = perTick;
        return this;
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

    protected abstract String getInputProblem(V token);

    protected abstract String getOutputProblem(V token);

    protected String getMissingInput() {
        return "craftcheck."+getRequiredComponentType().getRegistryName()+".input";
    }

    protected String getMissingOutput() {
        return "craftcheck."+getRequiredComponentType().getRegistryName()+".output";
    }

    protected String getMiscProblem() {
        return "craftcheck.failure.misc";
    }

    @Override
    public CraftCheck canStartCrafting(MachineComponent component, RecipeCraftingContext context, List<ComponentOutputRestrictor> list) {
        switch (getActionType()) {
            case INPUT:
                boolean didConsume = consumeToken(component, context, checkToken, false);
                if (!didConsume) {
                    return CraftCheck.failure(getInputProblem(checkToken));
                } else if (checkToken.isEmpty()) {
                    return CraftCheck.success();
                } else {
                    return CraftCheck.failure(getMissingInput());
                }
            case OUTPUT:
                boolean didGenerate = generateToken(component, context, checkToken, false);
                if (!didGenerate) {
                    return CraftCheck.failure(getOutputProblem(checkToken));
                } else if (checkToken.isEmpty()) {
                    return CraftCheck.success();
                } else {
                    return CraftCheck.failure(getMissingOutput());
                }
        }
        return CraftCheck.failure(getMiscProblem());
    }

    @Override
    public void startRequirementCheck(ResultChance resultChance, RecipeCraftingContext context) {
        checkToken = emitConsumptionToken(context);
        checkToken.applyModifiers(context,getActionType(), 1.0f);
        outputToken = emitConsumptionToken(context);
        outputToken.applyModifiers(context,getActionType(), 1.0f);
        EnvironmentalComponent.attach(getRequiredComponentType(),context);
    }

    @Override
    public void endRequirementCheck() {
        checkToken = emitConsumptionToken(null);
        outputToken = emitConsumptionToken(null);
    }

    @Override
    public void startIOTick(RecipeCraftingContext context, float durationMultiplier) {
        perTickToken.applyModifiers(context,getActionType(), durationMultiplier);
        EnvironmentalComponent.attach(getRequiredComponentType(),context);
    }

    @Override
    public CraftCheck resetIOTick(RecipeCraftingContext context) {
        boolean enough = !perTick || (perTickToken != null && perTickToken.isEmpty());
        this.perTickToken = emitConsumptionToken(context);
        if(enough)
            return CraftCheck.success();
        else if(getActionType() == MachineComponent.IOType.INPUT)
            return CraftCheck.failure(getMissingInput());
        else
            return CraftCheck.failure(getMissingOutput());
    }

    @Nonnull
    @Override
    public CraftCheck doIOTick(MachineComponent component, RecipeCraftingContext context) {
        if (!perTick)
            return CraftCheck.success();
        switch (getActionType()) {
            case INPUT:
                boolean didConsume = consumeToken(component, context, perTickToken, true);
                if (!didConsume) {
                    return CraftCheck.failure(getInputProblem(perTickToken));
                } else if (perTickToken.isEmpty()) {
                    return CraftCheck.success();
                } else {
                    return CraftCheck.failure(getMissingInput());
                }
            case OUTPUT:
                boolean didGenerate = generateToken(component, context, perTickToken, true);
                if (!didGenerate) {
                    return CraftCheck.failure(getOutputProblem(perTickToken));
                } else if (perTickToken.isEmpty()) {
                    return CraftCheck.success();
                } else {
                    return CraftCheck.failure(getMissingOutput());
                }
        }
        //This is neither input nor output? when do we actually end up in this case down here?
        return CraftCheck.failure(getMiscProblem());
    }

    protected abstract V emitConsumptionToken(RecipeCraftingContext context);

    protected abstract boolean consumeToken(MachineComponent component, RecipeCraftingContext context, V token, boolean doConsume);

    protected abstract boolean generateToken(MachineComponent component, RecipeCraftingContext context, V token, boolean doGenerate);
}
