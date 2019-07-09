package modulardiversity.crafttweaker;

import crafttweaker.annotations.ZenRegister;
import modulardiversity.components.requirements.AnchorType;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass(AnchorTypeWrapper.clazz)
@ZenRegister
public class AnchorTypeWrapper {
    public static final String clazz = "mods.modulardiversity.AnchorType";

    public AnchorType internal;

    public AnchorTypeWrapper(AnchorType internal) {
        this.internal = internal;
    }

    public AnchorType getInternal() {
        return internal;
    }

    @ZenMethod
    public static AnchorTypeWrapper getWorldSpawn() {
        return new AnchorTypeWrapper(new AnchorType.Spawn());
    }

    @ZenMethod
    public static AnchorTypeWrapper getOffset(double x, double y, double z) {
        return new AnchorTypeWrapper(new AnchorType.Offset(x,y,z));
    }

    @ZenMethod
    public static AnchorTypeWrapper getAnchor(String identifier) {
        return new AnchorTypeWrapper(new AnchorType.Anchor(identifier));
    }
}
