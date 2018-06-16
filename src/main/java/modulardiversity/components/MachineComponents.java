package modulardiversity.components;

import betterwithmods.api.tile.IMechanicalPower;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import me.desht.pneumaticcraft.api.tileentity.IAirHandler;
import teamroots.embers.power.IEmberCapability;
import vazkii.botania.api.mana.IManaBlock;

public class MachineComponents {
    public static abstract class EmberHatch extends MachineComponent<IEmberCapability> {
        public EmberHatch(IOType ioType) {
            super(ioType);
        }

        @Override
        public ComponentType getComponentType() {
            return ComponentType.Registry.getComponent("embers");
        }
    }

    public static abstract class ManaHatch extends MachineComponent<IManaBlock> {
        public ManaHatch(IOType ioType) {
            super(ioType);
        }

        @Override
        public ComponentType getComponentType() {
            return ComponentType.Registry.getComponent("mana");
        }
    }

    public static abstract class MechanicalHatch extends MachineComponent<IMechanicalPower> {
        public MechanicalHatch(IOType ioType) {
            super(ioType);
        }

        @Override
        public ComponentType getComponentType() {
            return ComponentType.Registry.getComponent("mechanical");
        }
    }

    public static abstract class AirHatch extends MachineComponent<IAirHandler> {
        public AirHatch(IOType ioType) {
            super(ioType);
        }

        @Override
        public ComponentType getComponentType() {
            return ComponentType.Registry.getComponent("air");
        }
    }
}
