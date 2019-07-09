package modulardiversity;

import modulardiversity.util.MachineList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModularDiversity.MODID, acceptedMinecraftVersions = "[1.12, 1.13)", dependencies = "required-after:modularmachinery;after:botania;after:embers;after:immersivepetroleum;after:buildcraftlib;after:pneumaticcraft;after:betterwithmods;")
@Mod.EventBusSubscriber
public class ModularDiversity
{
    public static final String MODID = "modulardiversity";

    public static boolean ImmersivePetroleumLoaded;
    public static boolean EmbersLoaded;
    public static boolean BotaniaLoaded;
    public static boolean BuildcraftLoaded;
    public static boolean BetterWithModsLoaded;
    public static boolean PneumaticCraftLoaded;
    public static boolean ProdigyTechLoaded;
    public static boolean MekanismLoaded;
    public static boolean MysticalMechanicsLoaded;

    public static int MANA_CAPACITY = 10000000;
    public static float MEKANISM_LASER_CAPACITY = 5.0E9F;

    Configuration configuration;

    @SidedProxy(clientSide = "modulardiversity.ClientProxy",serverSide = "modulardiversity.ServerProxy")
    public static IProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        BotaniaLoaded = Loader.isModLoaded("botania");
        EmbersLoaded = Loader.isModLoaded("embers");
        ImmersivePetroleumLoaded = Loader.isModLoaded("immersivepetroleum");
        BetterWithModsLoaded = Loader.isModLoaded("betterwithmods");
        PneumaticCraftLoaded = Loader.isModLoaded("pneumaticcraft");
        BuildcraftLoaded = Loader.isModLoaded("buildcraftlib");
        ProdigyTechLoaded = Loader.isModLoaded("prodigytech");
        MekanismLoaded = Loader.isModLoaded("mekanism");
        MysticalMechanicsLoaded = Loader.isModLoaded("mysticalmechanics");

        configuration = new Configuration(event.getSuggestedConfigurationFile());
        configuration.load();

        MANA_CAPACITY = configuration.getInt("manaHatchSize","hatch", MANA_CAPACITY, 0, Integer.MAX_VALUE, "Determines size of the mana hatch.");
        MEKANISM_LASER_CAPACITY = configuration.getFloat("mekanismLaserHatchSize","hatch", MEKANISM_LASER_CAPACITY, 0, Float.MAX_VALUE, "Determines size of the mekanism laser hatch.");

        if (configuration.hasChanged())
        {
            configuration.save();
        }

        MinecraftForge.EVENT_BUS.register(MachineList.class);

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
