package pl.psi;

import pl.psi.warmachines.WarMachine;
import WarMachines.WarMachineStatistic;
import WarMachines.WarMachineStats;
import org.junit.jupiter.api.Test;

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
        WarMachine ballista1 = new WarMachine.Builder().statistic(WarMachineStatistic.BALLISTA).build();
        WarMachine ballista2 = new WarMachine.Builder().statistic(WarMachineStatistic.BALLISTA).build();
        WarMachine catapult = new WarMachine.Builder().statistic(WarMachineStatistic.CATAPULT).build();
        TurnQueue turnQueue1 = new TurnQueue(List.of(ballista1, catapult), List.of(ballista2));
        assertEquals(turnQueue1.getCurrentMapObject(), ballista1);

        turnQueue1.next();
        assertEquals(catapult, turnQueue1.getCurrentMapObject());

        turnQueue1.next();
        assertEquals(ballista2, turnQueue1.getCurrentMapObject());

        turnQueue1.next();
        assertEquals(ballista1, turnQueue1.getCurrentMapObject());

        turnQueue1.removeMapObject(catapult);
        turnQueue1.next();
        assertEquals(catapult, turnQueue1.getCurrentMapObject());

        turnQueue1.next();
        assertEquals(ballista2, turnQueue1.getCurrentMapObject());

        turnQueue1.removeMapObject(ballista2);
        assertEquals(ballista2, turnQueue1.getCurrentMapObject());

        turnQueue1.next();
        assertEquals(ballista1, turnQueue1.getCurrentMapObject());

        turnQueue1.next();
        assertNotEquals(ballista2, turnQueue1.getCurrentMapObject());
        assertEquals(ballista1, turnQueue1.getCurrentMapObject());

        turnQueue1.next();
        assertEquals(ballista1, turnQueue1.getCurrentMapObject());

        turnQueue1.next();
        assertEquals(ballista1, turnQueue1.getCurrentMapObject());

    }
}