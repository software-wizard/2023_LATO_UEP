package pl.psi.warmachines;

import lombok.Setter;
import pl.psi.AttackerIF;
import pl.psi.ControlIndicator;
import pl.psi.MapObjectIf;
import pl.psi.WarMachines.WarMachineStatistic;

public class Ballista extends WarMachine implements AttackerIF {
    @Setter
    private boolean canBeControlledByPlayer;

    public Ballista(int amount) {
        super(WarMachineStatistic.BALLISTA, new WarMachineDamageCalculator(), new FirstAidTentHealPointsCalculator(), amount);
        canBeControlledByPlayer = new ControlIndicator().indicateControl(WarMachineStatistic.BALLISTA);
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

    @Override
    public boolean isControllable() {
        return canBeControlledByPlayer;
    }


}
