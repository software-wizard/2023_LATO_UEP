package pl.psi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import WarMachines.WarMachineStatistic;
import WarMachines.WarMachineStats;
import org.junit.jupiter.api.Test;

import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;
import pl.psi.warmachines.WarMachine;

class TurnQueueTest
{
    @Test
    void shouldAddPawnsCorrectly()
    {
        final Creature creature1 = new Creature.Builder().statistic( CreatureStats.builder()
            .build() )
            .build();
        final Creature creature2 = new Creature.Builder().statistic( CreatureStats.builder()
            .build() )
            .build();
        final Creature creature3 = new Creature.Builder().statistic( CreatureStats.builder()
            .build() )
            .build();
        final TurnQueue turnQueue = new TurnQueue( List.of( creature1, creature2 ), List.of( creature3 ) );

        assertEquals( turnQueue.getCurrentMapObject(), creature1 );
        turnQueue.next();
        assertEquals( turnQueue.getCurrentMapObject(), creature2 );
        turnQueue.next();
        assertEquals( turnQueue.getCurrentMapObject(), creature3 );
        turnQueue.next();
        assertEquals( turnQueue.getCurrentMapObject(), creature1 );
    }

    final WarMachine warMachine1 = new WarMachine.Builder().statistic(WarMachineStats.builder().build()).build();
    final WarMachine warMachine2 = new WarMachine.Builder().statistic(WarMachineStats.builder().build()).build();
    final WarMachine warMachine3 = new WarMachine.Builder().statistic(WarMachineStats.builder().build()).build();

    TurnQueue turnQueue = new TurnQueue(List.of(warMachine1, warMachine2), List.of(warMachine3));

    @Test
    void getCurrentMapObject() {
        assertEquals(turnQueue.getCurrentMapObject(), warMachine1);
    }


    @Test
    void shouldGoToNextMapObject() {
        assertEquals(turnQueue.getCurrentMapObject(), warMachine1);

        turnQueue.next();

        assertEquals(turnQueue.getCurrentMapObject(), warMachine2);

        turnQueue.next();

        assertEquals(turnQueue.getCurrentMapObject(), warMachine3);

        turnQueue.next();

        assertEquals(turnQueue.getCurrentMapObject(), warMachine1);
    }

    @Test
    void removeMapObject() {
        WarMachine ballista = new WarMachine.Builder().statistic(WarMachineStatistic.BALLISTA).build();
        WarMachine catapult = new WarMachine.Builder().statistic(WarMachineStatistic.CATAPULT).build();
        WarMachine ammoCart = new WarMachine.Builder().statistic(WarMachineStatistic.AMMO_CART).build();
        TurnQueue turnQueue1 = new TurnQueue(List.of(ballista, catapult), List.of(ammoCart));
        assertEquals(ballista, turnQueue1.getCurrentMapObject());

        turnQueue1.next();
        assertEquals(catapult, turnQueue1.getCurrentMapObject());

        turnQueue1.next();
        assertEquals(ammoCart, turnQueue1.getCurrentMapObject());

        turnQueue1.next();
        assertEquals(ballista, turnQueue1.getCurrentMapObject());

        turnQueue1.removeMapObject(catapult);
        turnQueue1.next();
        assertEquals(ammoCart, turnQueue1.getCurrentMapObject());

        turnQueue1.next();
        assertEquals(ballista, turnQueue1.getCurrentMapObject());

        turnQueue1.removeMapObject(ballista);
        assertEquals(ballista, turnQueue1.getCurrentMapObject());

        turnQueue1.next();
        assertEquals(ammoCart, turnQueue1.getCurrentMapObject());

        turnQueue1.next();
        assertEquals(ammoCart, turnQueue1.getCurrentMapObject());

        turnQueue1.next();
        assertEquals(ammoCart, turnQueue1.getCurrentMapObject());

    }
}