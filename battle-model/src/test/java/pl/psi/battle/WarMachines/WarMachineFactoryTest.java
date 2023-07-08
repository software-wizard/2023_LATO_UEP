// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.battle.WarMachines;

import org.junit.jupiter.api.Test;
import pl.psi.battle.GameEngine;
import pl.psi.battle.Hero;
import pl.psi.battle.Point;
import pl.psi.eco.WarMachines.WarMachineStatistic;
import pl.psi.battle.creatures.CastleCreatureFactory;
import pl.psi.battle.warmachines.WarMachine;
import pl.psi.battle.warmachines.WarMachineFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WarMachineFactoryTest {

    WarMachineFactory warMachineFactory = new WarMachineFactory();

    @Test
    void GameEngineWithWarMachineFactoryTest()
    {
        CastleCreatureFactory creatureFactory = new CastleCreatureFactory();

        final GameEngine gameEngine =
                new GameEngine(new Hero(List.of( creatureFactory.create( 1, false, 5 )), List.of(warMachineFactory.create(WarMachineStatistic.BALLISTA, 1, 1))),
                        new Hero(List.of( creatureFactory.create( 1, false, 5 )), List.of(warMachineFactory.create(WarMachineStatistic.CATAPULT, 1, 1))));

        gameEngine.attack( new Point( 1, 1 ) );

    }

    @Test
    void createWarMachineWithWarMachineFactoryBallista()
    {

        WarMachine ballista = warMachineFactory.create(WarMachineStatistic.BALLISTA,1,2);
        assertNotNull(ballista);
        assertEquals(ballista.getStats(), WarMachineStatistic.BALLISTA);
        assertEquals(ballista.getAmount(), 2);
        assertEquals(ballista.getMaxHp(), WarMachineStatistic.BALLISTA.getMaxHp());

    }

    @Test
    void createWarMachineWithWarMachineFactoryCatapult()
    {

        WarMachine catapult = warMachineFactory.create(WarMachineStatistic.CATAPULT,1,10);
        assertNotNull(catapult);
        assertEquals(catapult.getStats(), WarMachineStatistic.CATAPULT);
        assertEquals(catapult.getAmount(), 10);
        assertEquals(catapult.getMaxHp(), WarMachineStatistic.CATAPULT.getMaxHp());

    }
    @Test
    void createWarMachineWithWarMachineFactoryFirstAidTent()
    {

        WarMachine firstAidTent = warMachineFactory.create(WarMachineStatistic.FIRST_AID_TENT,1,5);
        assertNotNull(firstAidTent);
        assertEquals(firstAidTent.getStats(), WarMachineStatistic.FIRST_AID_TENT);
        assertEquals(firstAidTent.getAmount(), 5);
        assertEquals(firstAidTent.getMaxHp(), WarMachineStatistic.FIRST_AID_TENT.getMaxHp());

    }
}
