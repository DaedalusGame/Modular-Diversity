package modulardiversity.util;

import com.google.common.collect.Sets;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.machine.MachineComponent.IOType;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import hellfirepvp.modularmachinery.common.util.IOInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.util.Collection;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Misc {
    public static <T> Collector<T, ?, String> englishList() {
        return Collector.of(EnglishListBuilder::new,EnglishListBuilder::add,EnglishListBuilder::merge,EnglishListBuilder::finish);
    }

    public static class EnglishListBuilder {
        StringBuilder builder = new StringBuilder();
        String next = "";

        private void writeNext() {
            if(next != null) {
                if(builder.length() > 0) //not empty, add comma
                    builder.append(", ");
                builder.append(next);
            }
        }

        private void add(Object t) {
            writeNext();
            next = t.toString();
        }

        private EnglishListBuilder merge(EnglishListBuilder other) {
            writeNext();
            builder.append(other.builder);
            next = other.next;
            return this;
        }

        private String finish() {
            if(builder.length() > 0)
                return builder + " and " + next;
            else
                return next;
        }
    }

    public static TileEntity getTileEntity(MachineComponent component)
    {
        Object provider = component.getContainerProvider();
        if(provider instanceof FluidTank)
            ReflectionHelper.getPrivateValue(FluidTank.class,(FluidTank)provider,"tile");
        if(provider instanceof IOInventory)
            return ((IOInventory) provider).getOwner();
        if(provider instanceof TileEntity)
            return (TileEntity) provider;
        return null;
    }

    public static int applyModifiers(RecipeCraftingContext context, String target, IOType ioType, int value, boolean isChance) {
        return (int)RecipeModifier.applyModifiers(context.getModifiers(target),target,ioType,value,isChance);
    }

    public static long applyModifiers(RecipeCraftingContext context, String target, IOType ioType, long value, boolean isChance) {
        return (long)RecipeModifier.applyModifiers(context.getModifiers(target),target,ioType,value,isChance);
    }

    public static float applyModifiers(RecipeCraftingContext context, String target, IOType ioType, float value, boolean isChance) {
        return RecipeModifier.applyModifiers(context.getModifiers(target),target,ioType,value,isChance);
    }

    public static double applyModifiers(RecipeCraftingContext context, String target, IOType ioType, double value, boolean isChance) {
        return RecipeModifier.applyModifiers(context.getModifiers(target),target,ioType,(float)value,isChance);
    }

    public static int applyModifiers(Collection<RecipeModifier> context, String target, IOType ioType, int value, boolean isChance) {
        return (int)RecipeModifier.applyModifiers(context,target,ioType,value,isChance);
    }

    public static long applyModifiers(Collection<RecipeModifier> context, String target, IOType ioType, long value, boolean isChance) {
        return (long)RecipeModifier.applyModifiers(context,target,ioType,value,isChance);
    }

    public static float applyModifiers(Collection<RecipeModifier> context, String target, IOType ioType, float value, boolean isChance) {
        return RecipeModifier.applyModifiers(context,target,ioType,value,isChance);
    }

    public static double applyModifiers(Collection<RecipeModifier> context, String target, IOType ioType, double value, boolean isChance) {
        return RecipeModifier.applyModifiers(context,target,ioType,(float)value,isChance);
    }
}
