package pl.psi.warmachines;

import lombok.Setter;
import pl.psi.ControlIndicator;
import pl.psi.HealerIF;
import pl.psi.MapObjectIf;
import pl.psi.WarMachines.WarMachineStatistic;

public class FirstAidTent extends WarMachine implements HealerIF {
    @Setter
    private boolean canBeControlledByPlayer;
    public FirstAidTent(int amount) {
        super(WarMachineStatistic.FIRST_AID_TENT, new WarMachineDamageCalculator(), new FirstAidTentHealPointsCalculator(), amount);
        canBeControlledByPlayer = new ControlIndicator().indicateControl(WarMachineStatistic.FIRST_AID_TENT);
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
        return canBeControlledByPlayer;
    }

}
