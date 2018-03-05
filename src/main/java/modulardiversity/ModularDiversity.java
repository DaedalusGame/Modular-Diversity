package modulardiversity;

import modulardiversity.block.BlockEmberInputHatch;
import modulardiversity.block.BlockEmberOutputHatch;
import modulardiversity.block.BlockJackHatch;
import modulardiversity.tile.TileEmberInputHatch;
import modulardiversity.tile.TileEmberOutputHatch;
import modulardiversity.tile.TileJackHatch;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = ModularDiversity.MODID, version = ModularDiversity.VERSION, acceptedMinecraftVersions = "[1.12, 1.13)", dependencies = "required-after:modularmachinery;after:botania;after:embers;after:immersivepetroleum;")
@Mod.EventBusSubscriber
public class ModularDiversity
{
    public static final String MODID = "modulardiversity";
    public static final String VERSION = "0.1";

    public static boolean ImmersivePetroleumLoaded;
    public static boolean EmbersLoaded;
    public static boolean BotaniaLoaded;

    Configuration configuration;

    public static int ManaToFE;
    public static int EmberToFE;

    @SidedProxy(clientSide = "modulardiversity.ClientProxy",serverSide = "modulardiversity.ServerProxy")
    public static IProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        BotaniaLoaded = Loader.isModLoaded("botania");
        EmbersLoaded = Loader.isModLoaded("embers");
        ImmersivePetroleumLoaded = Loader.isModLoaded("immersivepetroleum");

        configuration = new Configuration(event.getSuggestedConfigurationFile());
        configuration.load();

        configuration.getCategory("conversion_rates").setComment("Configure conversion rates of different power inputs.");
        ManaToFE = configuration.get("conversion_rates","mana",10).getInt();
        EmberToFE = configuration.get("conversion_rates","ember",10).getInt();

        if (configuration.hasChanged())
        {
            configuration.save();
        }

        Registry.preInit();
        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit();
    }
}
