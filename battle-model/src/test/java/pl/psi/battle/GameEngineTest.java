// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.battle;

import java.util.List;
import java.util.Optional;

import pl.psi.battle.creatures.CastleCreatureFactory;
import pl.psi.battle.creatures.Creature;
import pl.psi.eco.WarMachines.WarMachineStatistic;
import org.junit.jupiter.api.Test;


import pl.psi.eco.creatures.CreatureStatistic;
import pl.psi.battle.warmachines.WarMachine;
import pl.psi.battle.warmachines.WarMachineFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class GameEngineTest
{
    @Test
    void shoudWorksHeHe()
    {
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        final GameEngine gameEngine =
                new GameEngine(new Hero( List.of( creatureFactory.create( 1, false, 5 )), List.of(new WarMachineFactory().create(WarMachineStatistic.BALLISTA, 1, 1))),
                        new Hero(List.of( creatureFactory.create( 1, false, 5 )), List.of(new WarMachineFactory().create(WarMachineStatistic.BALLISTA, 1, 1))));

        gameEngine.attack( new Point( 1, 1) );
    }

    @Test
    void attack() throws Exception {
        WarMachine ballista = new WarMachineFactory().create(WarMachineStatistic.BALLISTA, 1, 1);
        WarMachine catapult = new WarMachineFactory().create(WarMachineStatistic.CATAPULT, 1, 1);
        assertEquals(250, ballista.getCurrentHp());
        assertEquals(1000, catapult.getCurrentHp());
        // cast to attacker and attack
        ((AttackerIF) ballista).attack(catapult);
        assertTrue(catapult.getCurrentHp() < 1000);
    }

    @Test
    void heal() throws Exception {
        WarMachine firstAidTent = new WarMachineFactory().create(WarMachineStatistic.FIRST_AID_TENT, 1, 1);
        WarMachine catapult = new WarMachineFactory().create(WarMachineStatistic.CATAPULT, 1, 1);
        WarMachine ballista = new WarMachineFactory().create(WarMachineStatistic.BALLISTA, 1, 1);
        assertEquals(250, ballista.getCurrentHp());
        ((AttackerIF) catapult).attack(ballista);
        ((AttackerIF) catapult).attack(ballista);
        assertTrue(ballista.getCurrentHp() < 250);
        ((HealerIF) firstAidTent).heal(ballista);
        ((HealerIF) firstAidTent).heal(ballista);
        ((HealerIF) firstAidTent).heal(ballista);
        ((HealerIF) firstAidTent).heal(ballista);
        assertEquals(250, ballista.getCurrentHp());
    }

    @Test
    void canAttack() {
        WarMachine ballista = new WarMachineFactory().create(WarMachineStatistic.BALLISTA, 1, 1);
        Creature skeleton = new Creature.Builder().statistic(CreatureStatistic.SKELETON).build();
        WarMachine catapult = new WarMachineFactory().create(WarMachineStatistic.CATAPULT, 1, 1);

        //currentMapObject will be taken from queue created by this game engine instance
        //currentMapObject will perform attack
        GameEngine gameEngine = new GameEngine(new Hero(List.of(), List.of(catapult)),
                new Hero(List.of(skeleton), List.of(ballista)));
        // (3,1) is default attacker position in game engine
        assertTrue(gameEngine.isCurrentMapObject(new Point(0,1)));

        assertEquals(Optional.of(catapult), gameEngine.getMapObject(new Point(0,1)));
        assertEquals(Optional.of(skeleton), gameEngine.getMapObject(new Point(14,1)));
        assertEquals(Optional.of(ballista), gameEngine.getMapObject(new Point(14,3)));

        assertTrue(gameEngine.canAttack(new Point(14,3)));
        assertFalse(gameEngine.canAttack(new Point(0,1)));
        assertTrue(gameEngine.canAttack(new Point(14,1)));
        assertFalse(gameEngine.canAttack(new Point(3,3)));

        gameEngine.pass();
        assertTrue(gameEngine.isCurrentMapObject(new Point(14,1)));
        assertFalse(gameEngine.canAttack(new Point(14,1)));
        assertFalse(gameEngine.canAttack(new Point(14,3)));
        assertFalse(gameEngine.canAttack(new Point(3,1)));

        gameEngine.pass();
        assertTrue(gameEngine.isCurrentMapObject(new Point(14,3)));
        assertTrue(gameEngine.canAttack(new Point(0, 1)));
        assertFalse(gameEngine.canAttack(new Point(14, 1)));
        assertFalse(gameEngine.canAttack(new Point(14, 3)));
    }


    @Test
    void canHeal() {
        Creature zombie = new Creature.Builder().statistic(CreatureStatistic.ZOMBIE).build();
        WarMachine firstAidTent = new WarMachineFactory().create(WarMachineStatistic.FIRST_AID_TENT, 1, 1);
        WarMachine ballista = new WarMachineFactory().create(WarMachineStatistic.BALLISTA, 1, 1);
        Creature skeleton = new Creature.Builder().statistic(CreatureStatistic.SKELETON).build();
        WarMachine catapult = new WarMachineFactory().create(WarMachineStatistic.CATAPULT, 1, 1);

        GameEngine gameEngine = new GameEngine(new Hero(List.of(zombie), List.of(firstAidTent, ballista)),
                new Hero(List.of(skeleton), List.of(catapult)));

        assertEquals(Optional.of(zombie), gameEngine.getMapObject(new Point(0,1)));
        assertEquals(Optional.of(firstAidTent), gameEngine.getMapObject(new Point(0,3)));
        assertEquals(Optional.of(ballista), gameEngine.getMapObject(new Point(0,5)));
        assertEquals(Optional.of(skeleton), gameEngine.getMapObject(new Point(14,1)));
        assertEquals(Optional.of(catapult), gameEngine.getMapObject(new Point(14,3)));
        assertTrue(gameEngine.isCurrentMapObject(new Point(0,1)));
        assertFalse(gameEngine.canHeal(new Point(14,1)));

        gameEngine.pass();
        assertTrue(gameEngine.isCurrentMapObject(new Point(0,3)));
        assertTrue(gameEngine.canHeal(new Point(0,1)));
        assertFalse(gameEngine.canHeal(new Point(0,3)));
        assertTrue(gameEngine.canHeal(new Point(0,5)));
        assertFalse(gameEngine.canHeal(new Point(14,1)));
        assertFalse(gameEngine.canHeal(new Point(14,3)));
    }

    @Test
    void canPerformAction() {
        WarMachine ballista = new WarMachineFactory().create(WarMachineStatistic.BALLISTA, 1, 1);
        GameEngine gameEngine = new GameEngine(new Hero(List.of(), List.of(ballista)),
                new Hero(List.of(), List.of()));
        assertTrue(gameEngine.canPerformAction());
    }
}