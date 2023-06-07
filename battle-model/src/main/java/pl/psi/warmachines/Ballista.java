package pl.psi.warmachines;

import pl.psi.AttackerIF;
import pl.psi.MapObjectIf;
import pl.psi.WarMachines.WarMachineStatistic;

public class Ballista extends WarMachine implements AttackerIF {

    public Ballista(int amount) {
        super(WarMachineStatistic.BALLISTA, new WarMachineDamageCalculator(), new FirstAidTentHealPointsCalculator(), amount);
    }

    @Override
    public void attack(final MapObjectIf aDefender) throws Exception {
        if (isAlive()) {
            final int damage = getCalculator().calculateDamage(this, aDefender);
            applyDamage(aDefender, damage);
            // defender.applyDamage(damage); todo
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

}
