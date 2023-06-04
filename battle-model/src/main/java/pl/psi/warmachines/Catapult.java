package pl.psi.warmachines;

import pl.psi.WarMachines.WarMachineStatistic;

public class Catapult extends WarMachine {
    public Catapult(int amount) {
        super(WarMachineStatistic.CATAPULT, new WarMachineDamageCalculator(), new FirstAidTentHealPointsCalculator(), amount);
    }
}
