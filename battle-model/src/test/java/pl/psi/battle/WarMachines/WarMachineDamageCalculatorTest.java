// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.battle.WarMachines;

import pl.psi.eco.WarMachines.WarMachineStatistic;
import pl.psi.battle.warmachines.WarMachine;
import pl.psi.battle.warmachines.WarMachineDamageCalculator;

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