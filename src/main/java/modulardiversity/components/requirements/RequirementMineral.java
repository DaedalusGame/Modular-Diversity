package modulardiversity.components.requirements;

import blusunrize.immersiveengineering.api.tool.ExcavatorHandler;
import blusunrize.immersiveengineering.common.IESaveData;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import modulardiversity.jei.JEIComponentMineral;
import modulardiversity.jei.ingredients.Mineral;
import modulardiversity.util.IResourceToken;
import modulardiversity.util.Misc;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

import java.util.List;

public class RequirementMineral extends RequirementEnvironmental<Mineral, RequirementMineral.ResourceToken> {
    public String name;
    public int oreMin, oreMax;
    public int amount;

    public RequirementMineral(MachineComponent.IOType actionType, String name, int oreMin, int oreMax, int amount) {
        super(ComponentType.Registry.getComponent("mineral"), actionType);
        this.name = name;
        this.oreMin = oreMin;
        this.oreMax = oreMax;
        this.amount = amount;
    }

    @Override
    protected String getInputProblem(ResourceToken token) {
        return "craftcheck.mineral";
    }

    @Override
    protected String getOutputProblem(ResourceToken token) {
        return null;
    }

    @Override
    protected ResourceToken emitConsumptionToken(RecipeCraftingContext context) {
        return new ResourceToken(name, oreMin, oreMax,amount);
    }

    @Override
    protected boolean consumeToken(MachineComponent component, RecipeCraftingContext context, ResourceToken token, boolean doConsume) {
        TileEntity tile = Misc.getTileEntity(component);
        if(tile != null)
        {
            ChunkPos pos = new ChunkPos(tile.getPos());
            ExcavatorHandler.MineralWorldInfo mineral = ExcavatorHandler.getMineralWorldInfo(tile.getWorld(), pos.x, pos.z);
            if(token.matches(tile.getWorld(),tile.getPos(), mineral))
            {
                int depletion = mineral.depletion;
                if(doConsume) {
                    mineral.depletion = Math.max(0, depletion - token.amount);
                    IESaveData.setDirty(tile.getWorld().provider.getDimension());
                }
                token.setAmount(token.getAmount() - (ExcavatorHandler.mineralVeinCapacity - depletion));
            }
        }
        return true;
    }

    @Override
    protected boolean generateToken(MachineComponent component, RecipeCraftingContext context, ResourceToken token, boolean doGenerate) {
        TileEntity tile = Misc.getTileEntity(component);
        if(tile != null)
        {
            ChunkPos pos = new ChunkPos(tile.getPos());
            ExcavatorHandler.MineralWorldInfo mineral = ExcavatorHandler.getMineralWorldInfo(tile.getWorld(), pos.x, pos.z);
            if(token.matches(tile.getWorld(),tile.getPos(),mineral))
            {
                if(doGenerate) {
                    int depletion = mineral.depletion;
                    mineral.depletion = Math.max(0, depletion - token.amount);
                    IESaveData.setDirty(tile.getWorld().provider.getDimension());
                }
                token.setAmount(0);
            }
        }
        return true;
    }

    @Override
    public ComponentRequirement<Mineral> deepCopy() {
        return new RequirementMineral(getActionType(),name, oreMin, oreMax,amount);
    }

    @Override
    public ComponentRequirement<Mineral> deepCopyModified(List<RecipeModifier> modifiers) {
        return new RequirementMineral(getActionType(),
                name,
                Misc.applyModifiers(modifiers,"mineral_min",getActionType(), oreMin,false),
                Misc.applyModifiers(modifiers,"mineral_max",getActionType(), oreMax,false),
                Misc.applyModifiers(modifiers,"mineral",getActionType(), amount,false)
        );
    }

    @Override
    public JEIComponent<Mineral> provideJEIComponent() {
        return new JEIComponentMineral(this);
    }

    public static class ResourceToken implements IResourceToken
    {
        private String name;
        private int oreMin, oreMax;
        private int amount;

        public ResourceToken(String name, int oreMin, int oreMax, int amount) {
            this.name = name;
            this.oreMin = oreMin;
            this.oreMax = oreMax;
            this.amount = amount;
        }

        public boolean matches(World world, BlockPos pos, ExcavatorHandler.MineralWorldInfo reservoir)
        {
            if(reservoir == null)
                return false;
            ChunkPos chunkPos = new ChunkPos(pos);
            ExcavatorHandler.MineralMix mix = reservoir.mineralOverride != null ? reservoir.mineralOverride : reservoir.mineral;
            int current = ExcavatorHandler.mineralVeinCapacity - reservoir.depletion;
            return mix.name.equals(name) && current >= oreMin && current <= oreMax;
        }

        public int getOreMin() {
            return oreMin;
        }

        public int getOreMax() {
            return oreMax;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        @Override
        public void applyModifiers(RecipeCraftingContext modifiers, MachineComponent.IOType ioType, float durationMultiplier) {
            oreMin = Misc.applyModifiers(modifiers,"mineral_min",ioType, oreMin,false);
            oreMax = Misc.applyModifiers(modifiers,"mineral_max",ioType, oreMax,false);
            amount = Misc.applyModifiers(modifiers,"mineral",ioType, amount,false);
        }

        @Override
        public boolean isEmpty() {
            return amount <= 0;
        }
    }
}
