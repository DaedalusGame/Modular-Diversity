package modulardiversity.util;

import net.minecraft.client.resources.I18n;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

public class DimensionNameUtil {
    private static Map<Integer, String> nameCache = new HashMap<>();

    public static String getUnlocalizedName(int dimension)
    {
        if(nameCache.isEmpty())
            recache();
        return "dimension."+nameCache.getOrDefault(dimension,Integer.toString(dimension));
    }

    @SideOnly(Side.CLIENT)
    public static String getLocalizedName(int dimension)
    {
        return I18n.format(getUnlocalizedName(dimension));
    }

    private static void recache() {
        Integer[] ids = DimensionManager.getStaticDimensionIDs();
        if(ids.length == nameCache.size())
            return;
        for(int dimension : ids) {
            if(nameCache.containsKey(dimension))
                continue;
            WorldProvider provider = DimensionManager.createProviderFor(dimension);
            nameCache.put(dimension,provider.getDimensionType().getName());
        }
    }

    @SubscribeEvent
    public static void tick(TickEvent.ClientTickEvent event) {
        recache();
    }
}
