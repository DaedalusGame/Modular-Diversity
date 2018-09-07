package modulardiversity.components.requirements;

import com.google.common.collect.Lists;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentOutputRestrictor;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.util.ResultChance;
import modulardiversity.util.IResourceToken;
import modulardiversity.util.ICraftingResourceHolder;

import javax.annotation.Nonnull;
import java.util.List;

public abstract class RequirementConsumePerTick<T, V extends IResourceToken> extends ComponentRequirement.PerTick<T> {
    V checkToken;
    V perTickToken;

    public RequirementConsumePerTick(ComponentType componentType, MachineComponent.IOType actionType) {
        super(componentType, actionType);
    }

    @Override
    public boolean startCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance resultChance) {
        return canStartCrafting(component, context, Lists.newArrayList()) == CraftCheck.SUCCESS;
    }

    @Override
    public boolean finishCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance resultChance) {
        return true;
    }

    @Override
    public CraftCheck canStartCrafting(MachineComponent component, RecipeCraftingContext context, List<ComponentOutputRestrictor> list) {
        if(!isCorrectHatch(component)) return CraftCheck.INVALID_SKIP;
        ICraftingResourceHolder<V> handler = (ICraftingResourceHolder<V>) context.getProvidedCraftingComponent(component);
        switch (getActionType()) {
            case INPUT:
                boolean didConsume = handler.consume(checkToken,false);
                if(!didConsume) {
                    return CraftCheck.FAILURE_MISSING_INPUT;
                } else if(checkToken.isEmpty()) {
                    return CraftCheck.SUCCESS;
                } else {
                    return CraftCheck.PARTIAL_SUCCESS;
                }
            case OUTPUT:
                handler.generate(checkToken,false);
                if(checkToken.isEmpty()) {
                    return CraftCheck.SUCCESS;
                } else {
                    return CraftCheck.PARTIAL_SUCCESS;
                }
        }
        return CraftCheck.FAILURE_MISSING_INPUT;
    }

    @Override
    public void startRequirementCheck(ResultChance chance, RecipeCraftingContext context) {
        checkToken = emitConsumptionToken(context);
        checkToken.setModifier(context.applyModifiers(this,getActionType(),checkToken.getModifier(),false));
    }

    @Override
    public void endRequirementCheck() {
        checkToken = emitConsumptionToken(null);
    }

    @Override
    public void resetIOTick(RecipeCraftingContext context) {
        this.perTickToken = emitConsumptionToken(context);
    }

    @Override
    public void startIOTick(RecipeCraftingContext context, float durationMultiplier) {
        this.perTickToken.setModifier(context.applyModifiers(this, getActionType(), this.perTickToken.getModifier() , false) * durationMultiplier);
    }

    protected abstract V emitConsumptionToken(RecipeCraftingContext context);

    protected abstract boolean isCorrectHatch(MachineComponent component);

    @Nonnull
    @Override
    public CraftCheck doIOTick(MachineComponent component, RecipeCraftingContext context) {
        if(!isCorrectHatch(component)) return CraftCheck.INVALID_SKIP;
        ICraftingResourceHolder<V> handler = (ICraftingResourceHolder<V>) context.getProvidedCraftingComponent(component);
        switch (getActionType()) {
            case INPUT:
                boolean didConsume = handler.consume(perTickToken,true);
                if(!didConsume) {
                    return CraftCheck.FAILURE_MISSING_INPUT;
                } else if(perTickToken.isEmpty()) {
                    return CraftCheck.SUCCESS;
                } else {
                    return CraftCheck.PARTIAL_SUCCESS;
                }
            case OUTPUT:
                handler.generate(perTickToken,true);
                if(perTickToken.isEmpty()) {
                    return CraftCheck.SUCCESS;
                } else {
                    return CraftCheck.PARTIAL_SUCCESS;
                }
        }
        //This is neither input nor output? when do we actually end up in this case down here?
        return CraftCheck.INVALID_SKIP;
    }
}
