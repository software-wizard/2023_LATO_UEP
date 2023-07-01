package pl.psi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import pl.psi.WarMachines.WarMachineStatistic;
import pl.psi.WarMachines.WarMachineStats;
import org.junit.jupiter.api.Test;

import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;
import pl.psi.warmachines.WarMachine;
import pl.psi.warmachines.WarMachineFactory;

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
        WarMachine ballista = new WarMachineFactory().create(WarMachineStatistic.BALLISTA, 1, 2);
        WarMachine catapult = new WarMachineFactory().create(WarMachineStatistic.CATAPULT, 1, 2);
        WarMachine firstAidTent = new WarMachineFactory().create(WarMachineStatistic.FIRST_AID_TENT, 1, 2);
        List<MapObjectIf> mapObjectIf1 = new ArrayList<>(List.of(ballista, catapult));
        List<MapObjectIf> mapObjectIf2 = new ArrayList<>(List.of(firstAidTent));
        TurnQueue turnQueue1 = new TurnQueue(mapObjectIf1, mapObjectIf2);
        assertEquals(ballista, turnQueue1.getCurrentMapObject());

        turnQueue1.next();
        assertEquals(catapult, turnQueue1.getCurrentMapObject());

        turnQueue1.next();
        assertEquals(firstAidTent, turnQueue1.getCurrentMapObject());

        turnQueue1.next();
        assertEquals(ballista, turnQueue1.getCurrentMapObject());

        turnQueue1.removeMapObject(catapult);
        turnQueue1.next();
        assertEquals(firstAidTent, turnQueue1.getCurrentMapObject());

        turnQueue1.next();
        assertEquals(ballista, turnQueue1.getCurrentMapObject());

        turnQueue1.removeMapObject(ballista);
        assertEquals(ballista, turnQueue1.getCurrentMapObject());

        turnQueue1.next();
        assertEquals(firstAidTent, turnQueue1.getCurrentMapObject());

        turnQueue1.next();
        assertEquals(firstAidTent, turnQueue1.getCurrentMapObject());

        turnQueue1.next();
        assertEquals(firstAidTent, turnQueue1.getCurrentMapObject());

    }
}