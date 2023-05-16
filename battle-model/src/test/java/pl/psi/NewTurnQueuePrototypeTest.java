package pl.psi;

import WarMachines.WarMachine;
import WarMachines.WarMachineStatistic;
import WarMachines.WarMachineStats;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewTurnQueuePrototypeTest {

    final WarMachine warMachine1 = new WarMachine.Builder().statistic(WarMachineStats.builder().build()).build();
    final WarMachine warMachine2 = new WarMachine.Builder().statistic(WarMachineStats.builder().build()).build();
    final WarMachine warMachine3 = new WarMachine.Builder().statistic(WarMachineStats.builder().build()).build();

    NewTurnQueuePrototype newTurnQueuePrototype = new NewTurnQueuePrototype(List.of(warMachine1, warMachine2), List.of(warMachine3));

    @Test
    void getCurrentWarMachine() {
        assertEquals(newTurnQueuePrototype.getCurrentMapObject(), warMachine1);
    }


    @Test
    void shouldGoToNextWarMachine() {
        assertEquals(newTurnQueuePrototype.getCurrentMapObject(), warMachine1);

        newTurnQueuePrototype.next();

        assertEquals(newTurnQueuePrototype.getCurrentMapObject(), warMachine2);

        newTurnQueuePrototype.next();

        assertEquals(newTurnQueuePrototype.getCurrentMapObject(), warMachine3);

        newTurnQueuePrototype.next();

        assertEquals(newTurnQueuePrototype.getCurrentMapObject(), warMachine1);
    }

    @Test
    void removeWarMachine() {
        WarMachine ballista1 = new WarMachine.Builder().statistic(WarMachineStatistic.BALLISTA).build();
        WarMachine ballista2 = new WarMachine.Builder().statistic(WarMachineStatistic.BALLISTA).build();
        WarMachine catapult = new WarMachine.Builder().statistic(WarMachineStatistic.CATAPULT).build();
        NewTurnQueuePrototype newTurnQueuePrototype1 = new NewTurnQueuePrototype(List.of(ballista1, catapult), List.of(ballista2));
        assertEquals(newTurnQueuePrototype1.getCurrentMapObject(), ballista1);

        newTurnQueuePrototype1.next();
        assertEquals(catapult, newTurnQueuePrototype1.getCurrentMapObject());

        newTurnQueuePrototype1.next();
        assertEquals(ballista2, newTurnQueuePrototype1.getCurrentMapObject());

        newTurnQueuePrototype1.next();
        assertEquals(ballista1, newTurnQueuePrototype1.getCurrentMapObject());

        newTurnQueuePrototype1.removeMapObject(catapult);
        newTurnQueuePrototype1.next();
        assertEquals(catapult, newTurnQueuePrototype1.getCurrentMapObject());

        newTurnQueuePrototype1.next();
        assertEquals(ballista2, newTurnQueuePrototype1.getCurrentMapObject());

        newTurnQueuePrototype1.removeMapObject(ballista2);
        assertEquals(ballista2, newTurnQueuePrototype1.getCurrentMapObject());

        newTurnQueuePrototype1.next();
        assertEquals(ballista1, newTurnQueuePrototype1.getCurrentMapObject());

        newTurnQueuePrototype1.next();
        assertNotEquals(ballista2, newTurnQueuePrototype1.getCurrentMapObject());
        assertEquals(ballista1, newTurnQueuePrototype1.getCurrentMapObject());

        newTurnQueuePrototype1.next();
        assertEquals(ballista1, newTurnQueuePrototype1.getCurrentMapObject());

        newTurnQueuePrototype1.next();
        assertEquals(ballista1, newTurnQueuePrototype1.getCurrentMapObject());

    }
}