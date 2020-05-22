package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.*;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.util.ResultChance;
import modulardiversity.util.ICraftingResourceHolder;
import modulardiversity.util.IResourceToken;

import javax.annotation.Nonnull;
import java.util.List;

public abstract class RequirementConsumeOnce<T, V extends IResourceToken, TType extends RequirementType<T,? extends RequirementConsumeOnce<T,V,TType>>> extends ComponentRequirement<T,TType> {
    V checkToken;
    V outputToken;

    public RequirementConsumeOnce(TType componentType, IOType actionType) {
        super(componentType, actionType);
    }

    @Override
    public boolean isValidComponent(ProcessingComponent<?> component, RecipeCraftingContext ctx) {
        Object handler = component.getComponent().getContainerProvider();
        return handler instanceof ICraftingResourceHolder;
    }

    @Override
    public boolean startCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, ResultChance chance) {
        ICraftingResourceHolder<V> handler = (ICraftingResourceHolder<V>) component.getComponent().getContainerProvider();
        switch (getActionType()) {
            case INPUT:
                handler.consume(outputToken,true);
                if(outputToken.isEmpty())
                    return true;
        }
        return false;
    }

    @Nonnull
    @Override
    public CraftCheck finishCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, ResultChance chance) {
        ICraftingResourceHolder<V> handler = (ICraftingResourceHolder<V>) component.getComponent().getContainerProvider();
        switch (getActionType()) {
            case OUTPUT:
                handler.generate(outputToken,true);
                if(outputToken.isEmpty())
                    return CraftCheck.success();
        }
        return CraftCheck.failure("");
    }

    @Nonnull
    @Override
    public String getMissingComponentErrorMessage(IOType ioType) {
        return "craftcheck."+getRequirementType().getRegistryName()+".input";
    }

    protected String getMissingInput() {
        return "craftcheck."+getRequirementType().getRegistryName()+".input";
    }

    protected String getMissingOutput() {
        return "craftcheck."+getRequirementType().getRegistryName()+".output";
    }

    protected String getMiscProblem() {
        return "craftcheck.failure.misc";
    }

    @Nonnull
    @Override
    public CraftCheck canStartCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, List<ComponentOutputRestrictor> restrictions) {
        ICraftingResourceHolder<V> handler = (ICraftingResourceHolder<V>) component.getComponent().getContainerProvider();
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
        checkToken.applyModifiers(context, getRequirementType(), getActionType(), 1.0f);
        outputToken = emitConsumptionToken(context);
        outputToken.applyModifiers(context, getRequirementType(), getActionType(), 1.0f);
    }

    @Override
    public void endRequirementCheck() {
        checkToken = emitConsumptionToken(null);
        outputToken = emitConsumptionToken(null);
    }

    protected abstract boolean isCorrectHatch(MachineComponent component);

    protected abstract V emitConsumptionToken(RecipeCraftingContext context);
}
