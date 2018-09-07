package modulardiversity.tile;

import betterwithmods.api.tile.ICrankable;
import modulardiversity.components.requirements.RequirementMechanical;
import net.minecraftforge.fml.common.Optional;

@Optional.Interface(iface = "betterwithmods.api.tile.IMechanicalPower",modid = "betterwithmods")
public class TileMechInputCrank extends TileMechInput implements ICrankable {
    public TileMechInputCrank() {
        super(1);
    }

    @Override
    public boolean consume(RequirementMechanical.ResourceToken token, boolean doConsume) {
        /*if(!token.isCrankAllowed())
            return false;*/
        return super.consume(token,doConsume);
    }
}
