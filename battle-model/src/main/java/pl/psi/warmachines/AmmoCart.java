package pl.psi.warmachines;

import pl.psi.WarMachines.WarMachineStatistic;

public class AmmoCart extends WarMachine {
    public AmmoCart(int amount) {
        super(WarMachineStatistic.AMMO_CART, new WarMachineDamageCalculator(), new FirstAidTentHealPointsCalculator(), amount);
    }
}
