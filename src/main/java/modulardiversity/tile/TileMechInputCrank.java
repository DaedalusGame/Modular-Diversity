package modulardiversity.tile;

import betterwithmods.api.tile.ICrankable;
import net.minecraftforge.fml.common.Optional;

@Optional.Interface(iface = "betterwithmods.api.tile.ICrankable",modid = "betterwithmods")
public class TileMechInputCrank extends TileMechInput implements ICrankable {
    public TileMechInputCrank() {
        super(1);
    }
}
