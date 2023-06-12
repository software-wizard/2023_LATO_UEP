package pl.psi.warmachines;

import pl.psi.HealerIF;
import pl.psi.MapObjectIf;
import pl.psi.WarMachines.WarMachineStatistic;

public class FirstAidTent extends WarMachine implements HealerIF {
    public FirstAidTent(int amount) {
        super(WarMachineStatistic.FIRST_AID_TENT, new WarMachineDamageCalculator(), new FirstAidTentHealPointsCalculator(), amount);
    }

    @Override
    public void heal(MapObjectIf ally) throws Exception {
        if (isAlive()) {
            final int hp = getHPcalculator().calculateHealPoint(this, ally, ally.getCurrentHp());
            ally.setCurrentHp(hp);
            if ((ally.getCurrentHp() > ally.getMaxHp())) {
                ally.setCurrentHp(ally.getMaxHp());
            }
        }
    }

    @Override
    public boolean canHeal() {
        return true;
    }

    @Override
    public boolean isControllable() {
        return true;
    }

}
