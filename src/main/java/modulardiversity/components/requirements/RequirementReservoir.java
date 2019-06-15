package modulardiversity.components.requirements;

import flaxbeard.immersivepetroleum.api.crafting.PumpjackHandler;
import flaxbeard.immersivepetroleum.api.crafting.PumpjackHandler.OilWorldInfo;
import flaxbeard.immersivepetroleum.api.crafting.PumpjackHandler.ReservoirType;
import flaxbeard.immersivepetroleum.common.IPSaveData;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import modulardiversity.jei.JEIComponentReservoir;
import modulardiversity.jei.ingredients.Reservoir;
import modulardiversity.util.IResourceToken;
import modulardiversity.util.Misc;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

import java.util.List;

public class RequirementReservoir extends RequirementEnvironmental<Reservoir, RequirementReservoir.ResourceToken> {
    public String name;
    public int fluidMin, fluidMax;
    public int residualMin, residualMax;
    public int amount;

    public RequirementReservoir(MachineComponent.IOType actionType, String name, int fluidMin, int fluidMax, int residualMin, int residualMax, int amount) {
        super(ComponentType.Registry.getComponent("reservoir"), actionType);
        this.name = name;
        this.fluidMin = fluidMin;
        this.fluidMax = fluidMax;
        this.residualMin = residualMin;
        this.residualMax = residualMax;
        this.amount = amount;
    }

    public ReservoirType getType() {
        for (ReservoirType type : PumpjackHandler.reservoirList.keySet()) {
            if(type.name.equals(name))
                return type;
        }
        return null;
    }

    @Override
    protected String getInputProblem(ResourceToken token) {
        return "craftcheck.reservoir";
    }

    @Override
    protected String getOutputProblem(ResourceToken token) {
        return null;
    }

    @Override
    protected ResourceToken emitConsumptionToken(RecipeCraftingContext context) {
        return new ResourceToken(name, fluidMin,fluidMax,residualMin,residualMax,amount);
    }

    private OilWorldInfo getReservoir(World world, BlockPos pos)
    {
        ChunkPos chunkPos = new ChunkPos(pos);
        return PumpjackHandler.getOilWorldInfo(world,chunkPos.x,chunkPos.z);
    }

    @Override
    protected boolean consumeToken(MachineComponent component, RecipeCraftingContext context, ResourceToken token, boolean doConsume) {
        TileEntity tile = Misc.getTileEntity(component);
        if(tile != null)
        {
            OilWorldInfo reservoir = getReservoir(tile.getWorld(),tile.getPos());
            if(token.matches(tile.getWorld(),tile.getPos(),reservoir))
            {
                int current = reservoir.current;
                if(doConsume) {
                    reservoir.current = Math.max(0, current + token.getAmount());
                    IPSaveData.setDirty(tile.getWorld().provider.getDimension());
                }
                token.setAmount(token.getAmount() - current);
            }
        }
        return true;
    }

    @Override
    protected boolean generateToken(MachineComponent component, RecipeCraftingContext context, ResourceToken token, boolean doGenerate) {
        TileEntity tile = Misc.getTileEntity(component);
        if(tile != null)
        {
            OilWorldInfo reservoir = getReservoir(tile.getWorld(),tile.getPos());
            if(token.matches(tile.getWorld(),tile.getPos(),reservoir))
            {
                if(doGenerate) {
                    int current = reservoir.current;
                    reservoir.current = Math.max(0, current + token.getAmount());
                    IPSaveData.setDirty(tile.getWorld().provider.getDimension());
                }
                token.setAmount(0);
            }
        }
        return true;
    }

    @Override
    public ComponentRequirement<Reservoir> deepCopy() {
        return new RequirementReservoir(getActionType(),name,fluidMin,fluidMax,residualMin,residualMax,amount);
    }

    @Override
    public ComponentRequirement<Reservoir> deepCopyModified(List<RecipeModifier> modifiers) {
        return new RequirementReservoir(getActionType(),
                name,
                Misc.applyModifiers(modifiers,"reservoir_min",getActionType(), fluidMin,false),
                Misc.applyModifiers(modifiers,"reservoir_max",getActionType(), fluidMax,false),
                Misc.applyModifiers(modifiers,"reservoir_residual_min",getActionType(), fluidMin,false),
                Misc.applyModifiers(modifiers,"reservoir_residual_max",getActionType(), fluidMax,false),
                Misc.applyModifiers(modifiers,"reservoir",getActionType(), amount,false)
        );
    }

    @Override
    public JEIComponent<Reservoir> provideJEIComponent() {
        return new JEIComponentReservoir(this);
    }

    public static class ResourceToken implements IResourceToken
    {
        private String name;
        private int fluidMin, fluidMax;
        private int residualMin, residualMax;
        private int amount;

        public ResourceToken(String name, int fluidMin, int fluidMax, int residualMin, int residualMax, int amount) {
            this.name = name;
            this.fluidMin = fluidMin;
            this.fluidMax = fluidMax;
            this.residualMin = residualMin;
            this.residualMax = residualMax;
            this.amount = amount;
        }

        public boolean matches(World world, BlockPos pos, OilWorldInfo reservoir)
        {
            if(reservoir == null)
                return false;
            ChunkPos chunkPos = new ChunkPos(pos);
            int current = reservoir.current;
            int residual = PumpjackHandler.getResidualFluid(world,chunkPos.x,chunkPos.z);
            return reservoir.getType().name.equals(name) && current >= fluidMin && current <= fluidMax && residual >= residualMin && residual <= residualMax;
        }

        public int getFluidMin() {
            return fluidMin;
        }

        public int getFluidMax() {
            return fluidMax;
        }

        public int getResidualMin() {
            return residualMin;
        }

        public int getResidualMax() {
            return residualMax;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        @Override
        public void applyModifiers(RecipeCraftingContext modifiers, MachineComponent.IOType ioType, float durationMultiplier) {
            fluidMin = Misc.applyModifiers(modifiers,"reservoir_min",ioType, fluidMin,false);
            fluidMax = Misc.applyModifiers(modifiers,"reservoir_max",ioType, fluidMax,false);
            residualMin = Misc.applyModifiers(modifiers,"reservoir_residual_min",ioType, fluidMin,false);
            residualMax = Misc.applyModifiers(modifiers,"reservoir_residual_max",ioType, fluidMax,false);
            amount = Misc.applyModifiers(modifiers,"reservoir",ioType, amount,false);
        }

        @Override
        public boolean isEmpty() {
            return amount <= 0;
        }
    }
}
