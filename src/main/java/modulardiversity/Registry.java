package modulardiversity;

import hellfirepvp.modularmachinery.ModularMachinery;
import hellfirepvp.modularmachinery.common.item.ItemBlockMachineComponent;
import hellfirepvp.modularmachinery.common.item.ItemBlockMachineComponentCustomName;
import modulardiversity.block.*;
import modulardiversity.tile.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

public class Registry {
    private static ArrayList<Block> BLOCKS = new ArrayList<>();
    private static ArrayList<Item> ITEMS = new ArrayList<>();

    public static void preInit()
    {
        MinecraftForge.EVENT_BUS.register(Registry.class);
        registerBlocks();
        registerTileEntities();
    }

    public static void registerBlocks()
    {
        if(ModularDiversity.EmbersLoaded) {
            BlockEmberInputHatch emberInputHatch = new BlockEmberInputHatch();
            BlockEmberOutputHatch emberOutputHatch = new BlockEmberOutputHatch();

            registerBlock("blockemberinputhatch",emberInputHatch, new ItemBlockMachineComponentCustomName(emberInputHatch));
            registerBlock("blockemberoutputhatch",emberOutputHatch, new ItemBlockMachineComponentCustomName(emberOutputHatch));
        }

        if(ModularDiversity.BotaniaLoaded) {
            BlockManaInputHatch manaInputHatch = new BlockManaInputHatch();
            BlockManaOutputHatch manaOutputHatch = new BlockManaOutputHatch();

            registerBlock("blockmanainputhatch",manaInputHatch, new ItemBlockMachineComponent(manaInputHatch));
            registerBlock("blockmanaoutputhatch",manaOutputHatch, new ItemBlockMachineComponent(manaOutputHatch));
        }

        if(ModularDiversity.ImmersivePetroleumLoaded) {
            BlockJackHatch jackHatch = new BlockJackHatch();

            registerBlock("blockjackhatch",jackHatch, new ItemBlockMachineComponent(jackHatch));
        }
    }

    public static void registerBlockModels()
    {
        for (Block block : BLOCKS) {
            ModularDiversity.proxy.registerBlockModel(block);
        }
    }

    public static void registerItemModels()
    {
        for (Item item : ITEMS) {
            ModularDiversity.proxy.registerItemModel(item);
        }
    }

    public static void registerBlock(String id,Block block, ItemBlock itemBlock)
    {
        block.setRegistryName(ModularDiversity.MODID,id);
        block.setUnlocalizedName(id);
        BLOCKS.add(block);
        registerItem(id,itemBlock);
    }

    public static void registerItem(String id,Item item)
    {
        item.setRegistryName(ModularDiversity.MODID,id);
        item.setUnlocalizedName(id);
        ITEMS.add(item);
    }

    public static void registerTileEntities()
    {
        if(ModularDiversity.EmbersLoaded) {
            registerTileEntity(TileEmberInputHatch.class);
            registerTileEntity(TileEmberOutputHatch.class);
        }
        if(ModularDiversity.BotaniaLoaded) {
            registerTileEntity(TileManaInputHatch.class);
            registerTileEntity(TileManaOutputHatch.class);
        }
        if(ModularDiversity.ImmersivePetroleumLoaded) {
            registerTileEntity(TileJackHatch.class);
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for (Block block : BLOCKS) {
            event.getRegistry().register(block);
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (Item item : ITEMS) {
            event.getRegistry().register(item);
        }
    }


    private static void registerTileEntity(Class<? extends TileEntity> tile)
    {
        GameRegistry.registerTileEntity(tile,tile.getSimpleName().toLowerCase());
    }
}
