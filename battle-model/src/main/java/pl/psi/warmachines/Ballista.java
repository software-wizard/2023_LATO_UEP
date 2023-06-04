package pl.psi.warmachines;

import pl.psi.WarMachines.WarMachineStatistic;

public class Ballista extends WarMachine {

    public Ballista(int amount) {
        super(WarMachineStatistic.BALLISTA, new WarMachineDamageCalculator(), new FirstAidTentHealPointsCalculator(), amount);
    }
}
