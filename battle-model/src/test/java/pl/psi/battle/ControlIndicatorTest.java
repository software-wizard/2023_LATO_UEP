// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.battle;

import org.junit.jupiter.api.Test;
import pl.psi.eco.WarMachines.WarMachineStatistic;
import pl.psi.battle.warmachines.WarMachine;
import pl.psi.battle.warmachines.WarMachineFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControlIndicatorTest {

//    @Test
//    void indicateControl() {
//        HashMap<String, Integer> skills = new HashMap<>();
//        skills.put("artillery", 1);
//        skills.put("ballistics", 1);
//        skills.put("firstAid", 1);
//        ControlIndicator controlIndicator = new ControlIndicator(skills);
//        assertTrue(controlIndicator.indicateControl(WarMachineStatistic.BALLISTA));
//        assertTrue(controlIndicator.indicateControl(WarMachineStatistic.CATAPULT));
//        assertTrue(controlIndicator.indicateControl(WarMachineStatistic.FIRST_AID_TENT));
//
//        HashMap<String, Integer> skills2 = new HashMap<>();
//        skills.put("artillery", 0);
//        skills.put("ballistics", 0);
//        skills.put("firstAid", 0);
//        ControlIndicator controlIndicator1 = new ControlIndicator(skills2);
//
//        assertFalse(controlIndicator1.indicateControl(WarMachineStatistic.BALLISTA));
//        assertFalse(controlIndicator1.indicateControl(WarMachineStatistic.CATAPULT));
//        assertFalse(controlIndicator1.indicateControl(WarMachineStatistic.FIRST_AID_TENT));
//    }

    @Test
    void isControllable() {
        WarMachine ballista = new WarMachineFactory().create(WarMachineStatistic.BALLISTA, 1, 1);
        WarMachine firstAidTent = new WarMachineFactory().create(WarMachineStatistic.FIRST_AID_TENT, 1, 1);
        Hero hero = new Hero(List.of(), List.of(ballista, firstAidTent));
        for (WarMachine warMachine : hero.getWarMachines()) {
            assertTrue(warMachine.isControllable());
        }
    }

    @Test
    void isNotControllable(){
        WarMachine catapult = new WarMachineFactory().create(WarMachineStatistic.CATAPULT, 0, 1);
        WarMachine ballista = new WarMachineFactory().create(WarMachineStatistic.BALLISTA, 0, 1);
        Hero hero = new Hero(List.of(), List.of(catapult, ballista));
        for (WarMachine warMachine : hero.getWarMachines()) {
            assertFalse(warMachine.isControllable());
        }
    }
}
