package modulardiversity.components;

import betterwithmods.api.tile.IMechanicalPower;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import me.desht.pneumaticcraft.api.tileentity.IAirHandler;
import modulardiversity.components.requirements.*;
import modulardiversity.util.ICraftingResourceHolder;
import teamroots.embers.api.power.IEmberCapability;
import vazkii.botania.api.mana.IManaBlock;

public class MachineComponents {
    public static abstract class EmberHatch extends MachineComponent<ICraftingResourceHolder<RequirementEmber.ResourceToken>> {
        public EmberHatch(IOType ioType) {
            super(ioType);
        }

        @Override
        public ComponentType getComponentType() {
            return ComponentType.Registry.getComponent("ember");
        }
    }

    public static abstract class ManaHatch extends MachineComponent<ICraftingResourceHolder<RequirementMana.ResourceToken>> {
        public ManaHatch(IOType ioType) {
            super(ioType);
        }

        @Override
        public ComponentType getComponentType() {
            return ComponentType.Registry.getComponent("mana");
        }
    }

    public static abstract class MechanicalHatch extends MachineComponent<ICraftingResourceHolder<RequirementMechanical.ResourceToken>> {
        public MechanicalHatch(IOType ioType) {
            super(ioType);
        }

        @Override
        public ComponentType getComponentType() {
            return ComponentType.Registry.getComponent("mechanical");
        }
    }

    public static abstract class AirHatch extends MachineComponent<ICraftingResourceHolder<RequirementAir.ResourceToken>> {
        public AirHatch(IOType ioType) {
            super(ioType);
        }

        @Override
        public ComponentType getComponentType() {
            return ComponentType.Registry.getComponent("air");
        }
    }
    
    public static abstract class HotAirHatch extends MachineComponent<ICraftingResourceHolder<RequirementHotAir.ResourceToken>> {
        public HotAirHatch(IOType ioType) {
            super(ioType);
        }

        @Override
        public ComponentType getComponentType() {
            return ComponentType.Registry.getComponent("hotair");
        }
    }

    public static abstract class LaserHatch extends MachineComponent<ICraftingResourceHolder<RequirementLaser.ResourceToken>> {
        public LaserHatch(IOType ioType) {
            super(ioType);
        }

        @Override
        public ComponentType getComponentType() {
            return ComponentType.Registry.getComponent("laser");
        }

        public abstract void reset();
    }

    public static abstract class BiomeDetector extends MachineComponent<Integer> {
        public BiomeDetector(IOType ioType) {
            super(IOType.INPUT);
        }

        @Override
        public ComponentType getComponentType() {
            return ComponentType.Registry.getComponent("biome");
        }
    }

    public static abstract class DaylightDetector extends MachineComponent<Long> {

        public DaylightDetector(IOType ioType) {
            super(IOType.INPUT);
        }

        @Override
        public ComponentType getComponentType() {
            return ComponentType.Registry.getComponent("daylight");
        }
    }

    public static abstract class WeatherDetector extends MachineComponent<Integer> {
        public WeatherDetector(IOType ioType) {
            super(IOType.INPUT);
        }

        @Override
        public ComponentType getComponentType() {
            return ComponentType.Registry.getComponent("weather");
        }
    }

    public static abstract class MekLaserAcceptor extends MachineComponent<ICraftingResourceHolder<RequirementMekLaser.ResourceToken>> {
        public MekLaserAcceptor(IOType ioType) {
            super(ioType);
        }

        @Override
        public ComponentType getComponentType() {
            return ComponentType.Registry.getComponent("mlenergy");
        }
    }
}
