package modulardiversity.components;

import betterwithmods.api.tile.IMechanicalPower;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import me.desht.pneumaticcraft.api.tileentity.IAirHandler;
import modulardiversity.components.requirements.RequirementAir;
import modulardiversity.components.requirements.RequirementEmber;
import modulardiversity.components.requirements.RequirementMana;
import modulardiversity.components.requirements.RequirementMechanical;
import modulardiversity.util.ICraftingResourceHolder;
import teamroots.embers.power.IEmberCapability;
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
}
