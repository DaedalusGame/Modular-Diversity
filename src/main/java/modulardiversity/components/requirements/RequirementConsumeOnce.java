package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentOutputRestrictor;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.util.ResultChance;
import modulardiversity.util.ICraftingResourceHolder;
import modulardiversity.util.IResourceToken;

import java.util.List;

public abstract class RequirementConsumeOnce<T, V extends IResourceToken> extends ComponentRequirement<T> {
    V checkToken;
    V outputToken;

    public RequirementConsumeOnce(ComponentType componentType, MachineComponent.IOType actionType) {
        super(componentType, actionType);
    }

    @Override
    public boolean startCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance resultChance) {
        if(!isCorrectHatch(component)) return false;
        ICraftingResourceHolder<V> handler = (ICraftingResourceHolder<V>) context.getProvidedCraftingComponent(component);
        switch (getActionType()) {
            case INPUT:
                handler.consume(outputToken,true);
                if(outputToken.isEmpty())
                    return true;
        }
        return false;
    }

    @Override
    public boolean finishCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance resultChance) {
        if(!isCorrectHatch(component)) return false;
        ICraftingResourceHolder<V> handler = (ICraftingResourceHolder<V>) context.getProvidedCraftingComponent(component);
        switch (getActionType()) {
            case OUTPUT:
                handler.generate(outputToken,true);
                if(outputToken.isEmpty())
                    return true;
        }
        return false;
    }

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
        if(!isCorrectHatch(component)) return CraftCheck.skipComponent();
        ICraftingResourceHolder<V> handler = (ICraftingResourceHolder<V>) context.getProvidedCraftingComponent(component);
        switch (getActionType()) {
            case INPUT:
                boolean didConsume = handler.consume(checkToken,false);
                if(!didConsume) {
                    return CraftCheck.failure(handler.getInputProblem(checkToken));
                } else if(checkToken.isEmpty()) {
                    return CraftCheck.success();
                } else {
                    return CraftCheck.failure(getMissingInput());
                }
            case OUTPUT:
                boolean didGenerate = handler.generate(checkToken,false);
                if(!didGenerate) {
                    return CraftCheck.failure(handler.getOutputProblem(checkToken));
                } else if(checkToken.isEmpty()) {
                    return CraftCheck.success();
                } else {
                    return CraftCheck.failure(getMissingOutput());
                }
        }
        return CraftCheck.failure(getMiscProblem());
    }

    @Override
    public void startRequirementCheck(ResultChance chance, RecipeCraftingContext context) {
        checkToken = emitConsumptionToken(context);
        checkToken.applyModifiers(context,getActionType(), 1.0f);
        outputToken = emitConsumptionToken(context);
        outputToken.applyModifiers(context,getActionType(), 1.0f);
    }

    @Override
    public void endRequirementCheck() {
        checkToken = emitConsumptionToken(null);
        outputToken = emitConsumptionToken(null);
    }

    protected abstract boolean isCorrectHatch(MachineComponent component);

    protected abstract V emitConsumptionToken(RecipeCraftingContext context);
}
