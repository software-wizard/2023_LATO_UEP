package pl.psi.warmachines;

import pl.psi.WarMachines.WarMachineStatistic;

public class FirstAidTent extends WarMachine{
    public FirstAidTent(int amount) {
        super(WarMachineStatistic.FIRST_AID_TENT, new WarMachineDamageCalculator(), new FirstAidTentHealPointsCalculator(), amount);
    }

    @Override
    public boolean canHeal() {
        return true;
    }

    @Override
    public boolean canAttack() {
        return false;
    }
}
