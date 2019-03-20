package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentOutputRestrictor;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;
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
        return true;
    }

    @Override
    public boolean finishCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance resultChance) {
        return true;
    }

    @Override
    public CraftCheck canStartCrafting(MachineComponent component, RecipeCraftingContext context, List<ComponentOutputRestrictor> list) {
        if(!isCorrectHatch(component)) return CraftCheck.skipComponent();
        ICraftingResourceHolder<V> handler = (ICraftingResourceHolder<V>) context.getProvidedCraftingComponent(component);
        switch (getActionType()) {
            case INPUT:
                boolean didConsume = handler.consume(checkToken,false);
                if(!didConsume) {
                    return CraftCheck.failure("craftcheck.failure.generic.input");
                } else if(checkToken.isEmpty()) {
                    return CraftCheck.success();
                } else {
                    return CraftCheck.partialSuccess();
                }
            case OUTPUT:
                handler.generate(checkToken,false);
                if(checkToken.isEmpty()) {
                    return CraftCheck.success();
                } else {
                    return CraftCheck.partialSuccess();
                }
        }
        return CraftCheck.failure("craftcheck.failure.generic.input");
    }

    @Override
    public void startRequirementCheck(ResultChance chance, RecipeCraftingContext context) {
        checkToken = emitConsumptionToken(context);
        checkToken.applyModifiers(context,getActionType(), 1.0f);
    }

    @Override
    public void endRequirementCheck() {
        checkToken = emitConsumptionToken(null);
    }

    @Override
    public CraftCheck resetIOTick(RecipeCraftingContext context) {
        boolean enough = perTickToken.isEmpty();
        this.perTickToken = emitConsumptionToken(context);
        return enough ? CraftCheck.success() : CraftCheck.failure("craftcheck.failure.generic.input");
    }

    @Override
    public void startIOTick(RecipeCraftingContext context, float durationMultiplier) {
        perTickToken.applyModifiers(context,getActionType(), durationMultiplier);
    }

    protected abstract V emitConsumptionToken(RecipeCraftingContext context);

    protected abstract boolean isCorrectHatch(MachineComponent component);

    @Nonnull
    @Override
    public CraftCheck doIOTick(MachineComponent component, RecipeCraftingContext context) {
        if(!isCorrectHatch(component)) return CraftCheck.skipComponent();
        ICraftingResourceHolder<V> handler = (ICraftingResourceHolder<V>) context.getProvidedCraftingComponent(component);
        switch (getActionType()) {
            case INPUT:
                boolean didConsume = handler.consume(perTickToken,true);
                if(!didConsume) {
                    return CraftCheck.failure("craftcheck.failure.generic.input");
                } else if(perTickToken.isEmpty()) {
                    return CraftCheck.success();
                } else {
                    return CraftCheck.partialSuccess();
                }
            case OUTPUT:
                handler.generate(perTickToken,true);
                if(perTickToken.isEmpty()) {
                    return CraftCheck.success();
                } else {
                    return CraftCheck.partialSuccess();
                }
        }
        //This is neither input nor output? when do we actually end up in this case down here?
        return CraftCheck.failure("craftcheck.failure.generic.input");
    }
}
