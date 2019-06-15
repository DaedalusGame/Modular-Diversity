package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import modulardiversity.jei.JEIComponentAura;
import modulardiversity.jei.JEIComponentModifier;
import modulardiversity.jei.ingredients.Aura;
import modulardiversity.jei.ingredients.Modifier;
import modulardiversity.util.IResourceToken;
import modulardiversity.util.Misc;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import thaumcraft.api.aura.AuraHelper;

import java.util.List;

public class RequirementAura extends RequirementEnvironmental<Aura, RequirementAura.ResourceToken> {
    public String name;
    public float visMin, visMax;
    public float fluxMin, fluxMax;
    public float vis;
    public float flux;

    public RequirementAura(MachineComponent.IOType actionType, float visMin, float visMax, float fluxMin, float fluxMax, float vis, float flux) {
        super(ComponentType.Registry.getComponent("vis"), actionType);
        this.visMin = visMin;
        this.visMax = visMax;
        this.fluxMin = fluxMin;
        this.fluxMax = fluxMax;
        this.vis = vis;
        this.flux = flux;
    }

    @Override
    protected String getInputProblem(ResourceToken token) {
        if(token.visMatched && !token.fluxMatched)
            return "craftcheck.failure.flux";
        else
            return "craftcheck.failure.vis";
    }

    @Override
    protected String getOutputProblem(ResourceToken token) {
        return null;
    }

    @Override
    protected ResourceToken emitConsumptionToken(RecipeCraftingContext context) {
        return new ResourceToken(visMin, visMax, fluxMin, fluxMax, vis, flux);
    }

    @Override
    protected boolean consumeToken(MachineComponent component, RecipeCraftingContext context, ResourceToken token, boolean doConsume) {
        TileEntity tile = Misc.getTileEntity(component);
        if(tile != null) {
            World world = tile.getWorld();
            BlockPos pos = tile.getPos();
            float vis = AuraHelper.getVis(world, pos);
            float flux = AuraHelper.getFlux(world, pos);
            if (vis <= visMax && vis >= visMin)
                token.setVisMatched(true);
            if (flux <= fluxMax && flux >= fluxMin)
                token.setFluxMatched(true);
            writeAura(world, pos);
        }
        return token.isEmpty();
    }

    @Override
    protected boolean generateToken(MachineComponent component, RecipeCraftingContext context, ResourceToken token, boolean doGenerate) {
        TileEntity tile = Misc.getTileEntity(component);
        if(tile != null) {
            World world = tile.getWorld();
            BlockPos pos = tile.getPos();
            writeAura(world, pos);
        }
        return true;
    }

    private void writeAura(World world, BlockPos pos) {
        if(vis > 0)
            AuraHelper.addVis(world, pos, vis);
        else if(vis < 0)
            AuraHelper.drainVis(world, pos, -vis,false);

        if(flux > 0)
            AuraHelper.polluteAura(world, pos, flux,true);
        else if(flux < 0)
            AuraHelper.drainFlux(world, pos, -flux,false);
    }

    @Override
    public ComponentRequirement<Aura> deepCopy() {
        return new RequirementAura(getActionType(), visMin, visMax, fluxMin, fluxMax, vis, flux);
    }

    @Override
    public ComponentRequirement<Aura> deepCopyModified(List<RecipeModifier> modifiers) {
        return new RequirementAura(getActionType(), visMin, visMax, fluxMin, fluxMax, vis, flux);
    }

    @Override
    public JEIComponent<Aura> provideJEIComponent() {
        return new JEIComponentAura(this);
    }

    public static class ResourceToken implements IResourceToken
    {
        public float visMin, visMax;
        public float fluxMin, fluxMax;
        private boolean visMatched;
        private boolean fluxMatched;
        public float vis;
        public float flux;

        public boolean isVisMatched() {
            return visMatched;
        }

        public boolean isFluxMatched() {
            return fluxMatched;
        }

        public void setVisMatched(boolean visMatched) {
            this.visMatched = visMatched;
        }

        public void setFluxMatched(boolean fluxMatched) {
            this.fluxMatched = fluxMatched;
        }

        public ResourceToken(float visMin, float visMax, float fluxMin, float fluxMax, float vis, float flux) {
            this.visMin = visMin;
            this.visMax = visMax;
            this.fluxMin = fluxMin;
            this.fluxMax = fluxMax;
            this.vis = vis;
            this.flux = flux;
        }

        @Override
        public void applyModifiers(RecipeCraftingContext modifiers, MachineComponent.IOType ioType, float durationMultiplier) {
            visMin = Misc.applyModifiers(modifiers,"vis_min",ioType, visMin,false);
            visMax = Misc.applyModifiers(modifiers,"vis_max",ioType, visMax,false);
            fluxMin = Misc.applyModifiers(modifiers,"flux_min",ioType, fluxMin,false);
            fluxMax = Misc.applyModifiers(modifiers,"flux_max",ioType, fluxMax,false);
            vis = Misc.applyModifiers(modifiers,"vis",ioType, vis,false);
            flux = Misc.applyModifiers(modifiers,"flux",ioType, flux,false);
        }

        @Override
        public boolean isEmpty() {
            return visMatched && fluxMatched;
        }
    }
}
