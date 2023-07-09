package pl.psi;

import pl.psi.warmachines.WarMachine;
import pl.psi.WarMachines.WarMachineStatistic;
import pl.psi.WarMachines.WarMachineStats;
import org.junit.jupiter.api.Test;
import pl.psi.warmachines.WarMachineFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class NewTurnQueuePrototypeTest {

    final WarMachine warMachine1 = new WarMachine.Builder().statistic(WarMachineStats.builder().build()).build();
    final WarMachine warMachine2 = new WarMachine.Builder().statistic(WarMachineStats.builder().build()).build();
    final WarMachine warMachine3 = new WarMachine.Builder().statistic(WarMachineStats.builder().build()).build();

    TurnQueue turnQueue = new TurnQueue(List.of(warMachine1, warMachine2), List.of(warMachine3));

    @Test
    void getCurrentWarMachine() {
        assertEquals(turnQueue.getCurrentMapObject(), warMachine1);
    }


    @Test
    void shouldGoToNextWarMachine() {
        assertEquals(turnQueue.getCurrentMapObject(), warMachine1);

        turnQueue.next();

        assertEquals(turnQueue.getCurrentMapObject(), warMachine2);

        turnQueue.next();

        assertEquals(turnQueue.getCurrentMapObject(), warMachine3);

        turnQueue.next();

        assertEquals(turnQueue.getCurrentMapObject(), warMachine1);
    }

    @Test
    void removeWarMachine() {
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