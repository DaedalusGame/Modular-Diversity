package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import modulardiversity.jei.JEIComponentAnchor;
import modulardiversity.jei.ingredients.Anchor;
import modulardiversity.util.IResourceToken;
import modulardiversity.util.MachineList;
import modulardiversity.util.Misc;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class RequirementAnchor extends RequirementEnvironmental<Anchor, RequirementAnchor.ResourceToken> {
    public String name;
    public int time;

    public RequirementAnchor(MachineComponent.IOType actionType, String name, int time) {
        super(ComponentType.Registry.getComponent("anchor"), actionType);
        this.name = name;
        this.time = time;
    }

    @Override
    protected String getInputProblem(ResourceToken token) {
        return null;
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
    protected boolean consumeToken(MachineComponent component, RecipeCraftingContext context, ResourceToken token, boolean doConsume) {
        TileEntity tile = Misc.getTileEntity(component);
        if(tile != null) {
            World world = tile.getWorld();
            BlockPos pos = tile.getPos();

            if(doConsume)
                MachineList.wakeMachine(world,pos,name,time);
        }
        return true;
    }

    @Override
    protected boolean generateToken(MachineComponent component, RecipeCraftingContext context, ResourceToken token, boolean doGenerate) {
        TileEntity tile = Misc.getTileEntity(component);
        if(tile != null) {
            World world = tile.getWorld();
            BlockPos pos = tile.getPos();

            if(doGenerate)
                MachineList.wakeMachine(world,pos,name,time);
        }
        return true;
    }

    @Override
    public ComponentRequirement<Anchor> deepCopy() {
        return new RequirementAnchor(getActionType(), name, time);
    }

    @Override
    public ComponentRequirement<Anchor> deepCopyModified(List<RecipeModifier> modifiers) {
        return new RequirementAnchor(getActionType(), name, time);
    }

    @Override
    public JEIComponent<Anchor> provideJEIComponent() {
        return new JEIComponentAnchor(this);
    }

    public static class ResourceToken implements IResourceToken
    {
        @Override
        public void applyModifiers(RecipeCraftingContext modifiers, MachineComponent.IOType ioType, float durationMultiplier) {

        }

        @Override
        public boolean isEmpty() {
            return true;
        }
    }
}
