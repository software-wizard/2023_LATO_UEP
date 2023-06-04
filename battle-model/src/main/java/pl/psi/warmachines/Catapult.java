package pl.psi.warmachines;

import pl.psi.AttackerIF;
import pl.psi.MapObjectIf;
import pl.psi.WarMachines.WarMachineStatistic;

public class Catapult extends WarMachine implements AttackerIF {
    public Catapult(int amount) {
        super(WarMachineStatistic.CATAPULT, new WarMachineDamageCalculator(), new FirstAidTentHealPointsCalculator(), amount);
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
}
