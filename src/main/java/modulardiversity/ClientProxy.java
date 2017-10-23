package modulardiversity;

import hellfirepvp.modularmachinery.common.block.BlockDynamicColor;
import hellfirepvp.modularmachinery.common.block.BlockVariants;
import hellfirepvp.modularmachinery.common.item.ItemDynamicColor;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class ClientProxy implements IProxy {
    ArrayList<BlockDynamicColor> COLOR_BLOCKS = new ArrayList<>();
    ArrayList<ItemDynamicColor> COLOR_ITEMS = new ArrayList<>();

    @Override
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void registerItemModel(Block block)
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event)
    {
        Registry.registerBlockModels();
        Registry.registerItemModels();
    }

    @Override
    public void init() {
        BlockColors blockColors = Minecraft.getMinecraft().getBlockColors();
        ItemColors itemColors = Minecraft.getMinecraft().getItemColors();

        for (BlockDynamicColor block : COLOR_BLOCKS) {
            blockColors.registerBlockColorHandler(block::getColorMultiplier,(Block)block);
        }

        for (ItemDynamicColor item : COLOR_ITEMS) {
            itemColors.registerItemColorHandler(item::getColorFromItemstack,(Item)item);
        }
    }

    @Override
    public void postInit() {

    }

    @Override
    public void registerBlockModel(Block block) {
        if(block instanceof BlockDynamicColor)
            COLOR_BLOCKS.add((BlockDynamicColor) block);
    }

    @Override
    public void registerItemModel(Item item) {
        if(item instanceof ItemDynamicColor)
            COLOR_ITEMS.add((ItemDynamicColor) item);
        if(item instanceof ItemBlock)
        {
            ItemBlock itemBlock = (ItemBlock) item;
            Block block = itemBlock.getBlock();
            ResourceLocation resloc = block.getRegistryName();
            if(block instanceof BlockVariants) {
                for (IBlockState state : ((BlockVariants) block).getValidStates()) {
                    ModelLoader.setCustomModelResourceLocation(item, block.getMetaFromState(state), new ModelResourceLocation(resloc, ((BlockVariants) block).getBlockStateName(state)));
                }
            }
            else
                ModelLoader.setCustomModelResourceLocation(item,0,new ModelResourceLocation(resloc, "inventory"));
        }
    }
}
