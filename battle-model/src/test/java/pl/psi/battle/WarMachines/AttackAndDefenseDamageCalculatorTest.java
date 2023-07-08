// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.battle.WarMachines;
import org.junit.jupiter.api.Test;
import pl.psi.battle.AttackerIF;
import pl.psi.eco.WarMachines.WarMachineStatistic;
import pl.psi.battle.warmachines.WarMachine;
import pl.psi.battle.warmachines.WarMachineFactory;

import static org.junit.jupiter.api.Assertions.*;

public class AttackAndDefenseDamageCalculatorTest {
    WarMachine ballistaAttacker = new WarMachineFactory().create(WarMachineStatistic.BALLISTA, 1, 1);
    WarMachine ballistaDefender = new WarMachineFactory().create(WarMachineStatistic.BALLISTA, 1, 1);

    WarMachine catapultAttacker = new WarMachineFactory().create(WarMachineStatistic.CATAPULT, 1, 1);
    WarMachine catapultDefender = new WarMachineFactory().create(WarMachineStatistic.CATAPULT, 1, 1);

    /*
    disclaimer - these tests are for FIXED hero's and defender's statistics
    to do: rewrite these tests once hero's and defender's (hero 2) statistics are accessed by WarMachineDamageCalculator
     */
    @Test
    void calculateDamageForBallistaAttackLvl3DefendersDefenseLvl0() throws Exception {
        /*
        hero's attack = 3
        hero's archery skill = 3
        hero's artillery skill = 0
        defender's defense skill = 0
        ballista's attack = 10+3

        lower and upper base dmg boundaries for Ballista are 2-3
        archery skill = 3, so the new boundaries are 3-4.5
        Ballista's dmg calculation is random(lowerBoundary*(herosAttackSkill+1), upperBoundary*(herosAttackSkill+1))
        random(3*(3+1), 4.5*(3*1)) - so the new boundaries are 12-18
        the difference between Ballista's attack (10 + hero's attack) and the defender's defense skill is 13
        the dmg output has to be increased by 5% for each point (in this case 13*1.05=13.65)
        so the final boundaries are 163.8-245.7
        */

        ((AttackerIF) ballistaAttacker).attack(ballistaDefender);

        int damageOutput = ballistaDefender.getMaxHp()-ballistaDefender.getCurrentHp();

        System.out.println("Test 1 HP after attacking: "+ ballistaDefender.getCurrentHp());
        System.out.println("Test 1 Damage output: "+(damageOutput));

        assertTrue(damageOutput>=163);
        assertTrue(damageOutput<=246);

    }

    @Test
    void calculateDamageForCatapultAttackLvl3DefendersDefenseLvl1() throws Exception {
        /*
        hero's attack = 3
        hero's ballistics skill = 0
        hero's artillery skill = 0
        defender's defense skill = 1
        catapult's attack = 10+3

        hero's ballistics skill is lvl 0, so catapult's dmg is either 0, 1 or 2
        the difference between Catapult's attack (10 + hero's attack) and the defender's defense skill is 12
        the dmg output has to be increased by 5% for each point (in this case 12*1.05=12.6)
        so the final dmg outputs are either 0, 12 (1*12.6 rounded) or 25 (2*12,6 rounded)
         */
        ((AttackerIF) catapultAttacker).attack(catapultDefender);

        int damageOutput = catapultDefender.getMaxHp()-catapultDefender.getCurrentHp();

        System.out.println("Test 2 HP after attacking: "+ catapultDefender.getCurrentHp());
        System.out.println("Test 2 Damage output: "+(damageOutput));

        assertTrue(damageOutput==0 || damageOutput==12 || damageOutput==25);
    }
    @Test
    void calculateDamageForCatapultAttackLvl3DefendersDefenseLvl1ButWithBallista() throws Exception {
        ((AttackerIF) catapultAttacker).attack(ballistaDefender);

        int damageOutput = ballistaDefender.getMaxHp()-ballistaDefender.getCurrentHp();

        System.out.println("Test 3 HP after attacking: "+ ballistaDefender.getCurrentHp());
        System.out.println("Test 3 Damage output: "+(damageOutput));

        assertTrue(damageOutput==0 || damageOutput==12 || damageOutput==25);
    }
    @Test
    void calculateDamageForBallistaAttackLvl3DefendersDefenseLvl0WithCatapult() throws Exception {
        ((AttackerIF) ballistaAttacker).attack(catapultDefender);

        int damageOutput = catapultDefender.getMaxHp()-catapultDefender.getCurrentHp();

        System.out.println("Test 4 HP after attacking: "+ catapultDefender.getCurrentHp());
        System.out.println("Test 4 Damage output: "+(damageOutput));

        assertTrue(damageOutput>=163);
        assertTrue(damageOutput<=246);
    }

}
