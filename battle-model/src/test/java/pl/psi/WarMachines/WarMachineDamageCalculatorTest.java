package pl.psi.WarMachines;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pl.psi.warmachines.WarMachine;
import pl.psi.warmachines.WarMachineDamageCalculator;

import static org.junit.jupiter.api.Assertions.*;

class WarMachineDamageCalculatorTest {

    WarMachineDamageCalculator warMachineDamageCalculator = new WarMachineDamageCalculator();
    WarMachine ballista = new WarMachine.Builder().statistic(WarMachineStatistic.BALLISTA).amount(1).build();
    WarMachine catapult = new WarMachine.Builder().statistic(WarMachineStatistic.CATAPULT).amount(1).build();

//    @Disabled
//    @Test
//    void calculateDamage() throws Exception {
//        int damage = warMachineDamageCalculator.calculateDamage(ballista, catapult);
//        assertTrue(0 < damage);
//        System.out.println("First calculation for damage value: " + damage);
//        int averageDamage = 0;
//
//        for (int i = 0; i < 1000000; i++) {
//            averageDamage += warMachineDamageCalculator.calculateDamage(ballista, catapult);
//        }
//
//        averageDamage /= 1000000;
//        assertEquals(15, averageDamage);
//    }
}