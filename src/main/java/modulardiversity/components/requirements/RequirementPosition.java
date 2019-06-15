package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import modulardiversity.jei.JEIComponentEmberWorld;
import modulardiversity.jei.JEIComponentPosition;
import modulardiversity.jei.ingredients.EmberWorld;
import modulardiversity.jei.ingredients.Position;
import modulardiversity.util.IResourceToken;
import modulardiversity.util.Misc;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamroots.embers.util.EmberGenUtil;

import java.util.List;

public class RequirementPosition extends RequirementEnvironmental<Position, RequirementPosition.ResourceToken> {
    public float xMin, xMax;
    public float yMin, yMax;
    public float zMin, zMax;
    public float distanceMin, distanceMax;

    public RequirementPosition(MachineComponent.IOType actionType, float xMin, float xMax, float yMin, float yMax, float zMin, float zMax, float distanceMin, float distanceMax) {
        super(ComponentType.Registry.getComponent("position"), actionType);
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.zMin = zMin;
        this.zMax = zMax;
        this.distanceMin = distanceMin;
        this.distanceMax = distanceMax;
    }

    @Override
    protected String getInputProblem(ResourceToken token) {
        return "craftcheck.position";
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
        boolean matched = false;
        TileEntity tile = Misc.getTileEntity(component);
        if(tile != null)
        {
            World world = tile.getWorld();
            BlockPos pos = tile.getPos();

            float dx = getDistance(pos.getX(),xMin,xMax);
            float dy = getDistance(pos.getY(),yMin,yMax);
            float dz = getDistance(pos.getZ(),zMin,zMax);

            double dist = Math.sqrt(dx*dx+dy*dy+dz*dz);

            matched = dist >= distanceMin && dist <= distanceMax;
            token.setMatched(matched);
        }
        return matched;
    }

    private float getDistance(float coord, float min, float max)
    {
        if(coord < min)
            return min - coord;
        else if(coord > max)
            return coord - max;
        else
            return 0;
    }

    @Override
    protected boolean generateToken(MachineComponent component, RecipeCraftingContext context, ResourceToken token, boolean doGenerate) {
        return true;
    }

    @Override
    public ComponentRequirement<Position> deepCopy() {
        return new RequirementPosition(getActionType(),xMin, xMax,yMin,yMax,zMin,zMax,distanceMin,distanceMax);
    }

    @Override
    public ComponentRequirement<Position> deepCopyModified(List<RecipeModifier> modifiers) {
        return new RequirementPosition(getActionType(), xMin, xMax,yMin,yMax,zMin,zMax,distanceMin,distanceMax
        );
    }

    @Override
    public JEIComponent<Position> provideJEIComponent() {
        return new JEIComponentPosition(this);
    }

    public static class ResourceToken implements IResourceToken
    {
        boolean matched;

        public void setMatched(boolean matched) {
            this.matched = matched;
        }

        @Override
        public void applyModifiers(RecipeCraftingContext modifiers, MachineComponent.IOType ioType, float durationMultiplier) {
        }

        @Override
        public boolean isEmpty() {
            return matched;
        }
    }
}
