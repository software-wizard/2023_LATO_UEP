// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.battle;

import org.junit.jupiter.api.Test;
import pl.psi.battle.creatures.Creature;
import pl.psi.battle.creatures.NecropolisFactory;
import pl.psi.eco.WarMachines.WarMachineStatistic;
import pl.psi.battle.warmachines.Ballista;
import pl.psi.battle.warmachines.WarMachine;
import pl.psi.battle.warmachines.WarMachineFactory;

import static org.junit.jupiter.api.Assertions.*;

class CounterAttackTest {

    @Test
    void getCounterAttackCounter() {
        Creature creature1 = new NecropolisFactory().create( true, 2, 1 );
        Creature creature2 = new NecropolisFactory().create( false, 2, 1);
        WarMachine warMachine1 = new WarMachineFactory().create(WarMachineStatistic.BALLISTA, 2, 1);
        WarMachine warMachine2 = new WarMachineFactory().create(WarMachineStatistic.FIRST_AID_TENT, 0, 1);

        assertEquals(creature1.getCounterAttackCounter(), 1);
        assertEquals(creature2.getCounterAttackCounter(), 1);
        assertEquals(warMachine1.getCounterAttackCounter(), 0);
        assertEquals(warMachine2.getCounterAttackCounter(), 0);
    }

    @Test
    void counterAttack() throws Exception {
        Creature creature1 = new NecropolisFactory().create( true, 2, 1 );
        Creature creature2 = new NecropolisFactory().create( false, 2, 1);
        WarMachine warMachine1 = new WarMachineFactory().create(WarMachineStatistic.BALLISTA, 2, 1);

        assertEquals(creature1.getMaxHp(), creature1.getCurrentHp());
        assertEquals(creature2.getMaxHp(), creature2.getCurrentHp());
        creature1.attack(creature2);
        assertEquals(1, creature1.getCounterAttackCounter());
        assertEquals(0, creature2.getCounterAttackCounter());
        assertTrue(creature1.getCurrentHp() < creature1.getMaxHp());
        assertTrue(creature2.getCurrentHp() < creature2.getMaxHp());

        int creature1Hp = creature1.getCurrentHp();
        creature1.attack(warMachine1);
        assertTrue(warMachine1.getCurrentHp() < warMachine1.getMaxHp());
        assertEquals(creature1Hp, creature1.getCurrentHp());

        int creature1HpNew = creature1.getCurrentHp();
        int warMachine1Hp = warMachine1.getCurrentHp();
        ((Ballista) warMachine1).attack(creature1);
        assertTrue(creature1.getCurrentHp() < creature1HpNew);
        assertEquals(warMachine1Hp, warMachine1.getCurrentHp());
    }

    @Test
    void counterAttackWithWarMachines() throws Exception {
        Creature creature1 = new NecropolisFactory().create( true, 2, 1 );
        WarMachine warMachine1 = new WarMachineFactory().create(WarMachineStatistic.BALLISTA, 2, 1);

        int creature1Hp = creature1.getCurrentHp();
        creature1.attack(warMachine1);
        assertTrue(warMachine1.getCurrentHp() < warMachine1.getMaxHp());
        assertEquals(creature1Hp, creature1.getCurrentHp());
        assertEquals(1, creature1.getCounterAttackCounter());

        int warMachine1Hp = warMachine1.getCurrentHp();
        ((Ballista) warMachine1).attack(creature1);
        assertTrue(creature1.getCurrentHp() < creature1Hp);
        assertEquals(warMachine1Hp, warMachine1.getCurrentHp());
        assertEquals(1, creature1.getCounterAttackCounter());
    }
}