package pl.psi.warmachines;

import lombok.Setter;
import pl.psi.AttackerIF;
import pl.psi.ControlIndicator;
import pl.psi.MapObjectIf;
import pl.psi.WarMachines.WarMachineStatistic;

public class Catapult extends WarMachine implements AttackerIF {
    @Setter
    private boolean canBeControlledByPlayer;
    public Catapult(int amount) {
        super(WarMachineStatistic.CATAPULT, new WarMachineDamageCalculator(), new FirstAidTentHealPointsCalculator(), amount);
        canBeControlledByPlayer = new ControlIndicator().indicateControl(WarMachineStatistic.CATAPULT);
    }

    @Override
    public void attack(MapObjectIf aDefender) throws Exception {
        if (isAlive()) {
            final int damage = getCalculator().calculateDamage(this, aDefender);
            applyDamage(aDefender, damage);
        }
    }

    @Override
    public boolean canAttackFromDistance() {
        return true;
    }

    @Override
    public boolean canAttack() {
        return true;
    }

    @Override
    public boolean isControllable() {
        return canBeControlledByPlayer;
    }
}
