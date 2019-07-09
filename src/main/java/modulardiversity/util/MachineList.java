package modulardiversity.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.annotation.Nonnull;
import java.util.*;

public class MachineList {
    static WeakHashMap<World,MachineList> worldLists = new WeakHashMap<>();

    @SubscribeEvent
    public static void worldLoaded(WorldEvent.Load event) {
        World world = event.getWorld();
        MachineList list = new MachineList();
        MinecraftForge.EVENT_BUS.register(list);
        worldLists.put(world, list);
    }

    public static void wakeMachine(World world, BlockPos pos, @Nonnull String identifier, int time) {
        MachineList list = worldLists.get(world);
        if(list != null)
            list.wakeMachine(pos,identifier,time);
    }

    public static BlockPos getNearest(World world, BlockPos pos, @Nonnull String identifier) {
        MachineList list = worldLists.get(world);
        if(list != null)
            return list.getNearest(pos,identifier);
        return null;
    }

    int tick = 0;
    List<WokeMachine> machines = new ArrayList<>();
    HashMap<WokeMachine,WokeMachine> lookup = new HashMap<>();

    public void wakeMachine(BlockPos pos, @Nonnull String identifier, int time) {
        WokeMachine machine = new WokeMachine(pos, identifier, tick + time);
        WokeMachine existing = lookup.get(machine);
        if(existing != null) {
            existing.setDeathTime(tick + time);
        } else {
            machines.add(machine);
            lookup.put(machine,machine);
        }
    }

    public BlockPos getNearest(BlockPos pos, @Nonnull String identifier) { //This is about as optimized as it gets
        double nearestDist = Double.POSITIVE_INFINITY;
        BlockPos nearestPos = null;
        for (Iterator<WokeMachine> iterator = machines.iterator(); iterator.hasNext(); ) {
            WokeMachine machine = iterator.next();
            if(machine.isDead(tick)) {
                lookup.remove(machine);
                iterator.remove();
            } else if(identifier.equals(machine.getIdentifier())) {
                BlockPos machinePos = machine.getPos();
                double distance = pos.distanceSq(machinePos);
                if(distance < nearestDist && !machinePos.equals(pos)) {
                    nearestDist = distance;
                    nearestPos = machinePos;
                }
            }
        }
        return nearestPos;
    }

    @SubscribeEvent
    public void tick(TickEvent.WorldTickEvent event) {
        if(event.phase == TickEvent.Phase.END)
            tick++;
    }

    static class WokeMachine {
        BlockPos pos;
        String identifier;
        int deathTime;

        public BlockPos getPos() {
            return pos;
        }

        public String getIdentifier() {
            return identifier;
        }

        public boolean isDead(int tick) {
            return tick > deathTime;
        }

        public void setDeathTime(int deathTime) {
            this.deathTime = deathTime;
        }

        public WokeMachine(BlockPos pos, String identifier, int deathTime) {
            this.pos = pos;
            this.identifier = identifier;
            this.deathTime = deathTime;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof WokeMachine)
                return equals((WokeMachine) obj);
            return super.equals(obj);
        }

        private boolean equals(WokeMachine obj) {
            return obj.pos.equals(pos) && obj.identifier.equals(identifier);
        }

        @Override
        public int hashCode() {
            return pos.hashCode() ^ identifier.hashCode();
        }
    }
}
