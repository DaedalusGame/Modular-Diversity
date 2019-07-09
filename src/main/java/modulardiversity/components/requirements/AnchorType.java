package modulardiversity.components.requirements;

import com.google.gson.JsonObject;
import modulardiversity.util.JsonUtil;
import modulardiversity.util.MachineList;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AnchorType {
    static Map<String,IAnchorTypeDeserializer> typeMap = new HashMap<>();

    public static final AnchorType DEFAULT = new Offset();

    static {
        registerType("default", json -> DEFAULT);
        registerType("spawn", json -> new Spawn());
        registerType("anchor", json -> {
            String identifier = JsonUtil.get(json,"identifier","default");

            return new Anchor(identifier);
        });
        registerType("offset", json -> {
            double x = JsonUtil.get(json,"x",0.0);
            double y = JsonUtil.get(json,"y",0.0);
            double z = JsonUtil.get(json,"z",0.0);

            return new Offset(x,y,z);
        });
    }

    public static void registerType(String identifier, IAnchorTypeDeserializer deserializer) {
        typeMap.put(identifier,deserializer);
    }

    public static AnchorType get(JsonObject json) {
        String identifier = JsonUtil.get(json,"type","default");
        IAnchorTypeDeserializer deserializer = typeMap.get(identifier);
        if(deserializer != null) {
            return deserializer.deserialize(json);
        } else {
            return null;
        }
    }

    public abstract Vec3d getAnchorPoint(World world, BlockPos pos);

    public abstract void addTooltip(List<String> tooltip);

    public static class Anchor extends AnchorType {
        private String identifier;

        public Anchor(String identifier) {
            this.identifier = identifier;
        }

        @Override
        public Vec3d getAnchorPoint(World world, BlockPos pos) {
            BlockPos nearestPos = MachineList.getNearest(world,pos,identifier);
            if(nearestPos != null)
                return new Vec3d(nearestPos.getX(),nearestPos.getY(),nearestPos.getZ());
            else
                return null;
        }

        @Override
        public void addTooltip(List<String> tooltip) {
            tooltip.add(I18n.format("tooltip.anchor.anchor",identifier));
        }
    }

    public static class Offset extends AnchorType {
        double x, y, z;

        public Offset() {
        }

        public Offset(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public Vec3d getAnchorPoint(World world, BlockPos pos) {
            return new Vec3d(x,y,z);
        }

        @Override
        public void addTooltip(List<String> tooltip) {
            if(x != 0 || y != 0 || z != 0){
                tooltip.add(I18n.format("tooltip.anchor.offset",x,y,z));
            }
        }
    }

    public static class Spawn extends AnchorType {

        @Override
        public Vec3d getAnchorPoint(World world, BlockPos pos) {
            BlockPos spawn = world.getSpawnPoint();
            return new Vec3d(spawn.getX(),spawn.getY(),spawn.getZ());
        }

        @Override
        public void addTooltip(List<String> tooltip) {
            tooltip.add(I18n.format("tooltip.anchor.spawn"));
        }
    }
}
