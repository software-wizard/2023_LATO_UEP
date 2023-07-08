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
import pl.psi.battle.warmachines.WarMachine;
import pl.psi.battle.warmachines.WarMachineFactory;

import static org.junit.jupiter.api.Assertions.*;

class GeneralBehaviourTest {

    @Test
    void catapultBehaviour() {
        WarMachine catapult = new WarMachineFactory().create(WarMachineStatistic.CATAPULT, 1, 1);
        assertTrue(catapult.canAttack());
        assertFalse(catapult.canHeal());
    }

    @Test
    void ballistaBehaviour() {
        WarMachine ballista = new WarMachineFactory().create(WarMachineStatistic.BALLISTA, 1, 1);
        assertTrue(ballista.canAttack());
        assertFalse(ballista.canHeal());
    }

    @Test
    void firstAidTentBehaviour() {
        WarMachine firstAidTent = new WarMachineFactory().create(WarMachineStatistic.FIRST_AID_TENT, 1, 1);
        assertFalse(firstAidTent.canAttack());
        assertTrue(firstAidTent.canHeal());
    }
    

    @Test
    void creatureBehaviour(){
        Creature skeleton = new NecropolisFactory().create(false, 1,1 );
        assertTrue(skeleton.canAttack());
        assertFalse(skeleton.canHeal());

        Creature walkingDead = new NecropolisFactory().create(true, 2,1 );
        assertTrue(walkingDead.canAttack());
        assertFalse(walkingDead.canHeal());
    }


}