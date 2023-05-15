package pl.psi;

import WarMachines.WarMachine;
import WarMachines.WarMachineStatistic;
import WarMachines.WarMachineStats;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TemporaryTurnQueueWithoutCreaturesTest {

    final WarMachine warMachine1 = new WarMachine.Builder().statistic(WarMachineStats.builder().build()).build();
    final WarMachine warMachine2 = new WarMachine.Builder().statistic(WarMachineStats.builder().build()).build();
    final WarMachine warMachine3 = new WarMachine.Builder().statistic(WarMachineStats.builder().build()).build();

    TemporaryTurnQueueWithoutCreatures temporaryTurnQueueWithoutCreatures = new TemporaryTurnQueueWithoutCreatures(List.of(warMachine1, warMachine2), List.of(warMachine3));

    @Test
    void getCurrentWarMachine() {
        assertEquals(temporaryTurnQueueWithoutCreatures.getCurrentWarMachine(), warMachine1);
    }


    @Test
    void shouldGoToNextWarMachine() {
        assertEquals(temporaryTurnQueueWithoutCreatures.getCurrentWarMachine(), warMachine1);

        temporaryTurnQueueWithoutCreatures.next();

        assertEquals(temporaryTurnQueueWithoutCreatures.getCurrentWarMachine(), warMachine2);

        temporaryTurnQueueWithoutCreatures.next();

        assertEquals(temporaryTurnQueueWithoutCreatures.getCurrentWarMachine(), warMachine3);

        temporaryTurnQueueWithoutCreatures.next();

        assertEquals(temporaryTurnQueueWithoutCreatures.getCurrentWarMachine(), warMachine1);
    }

    @Test
    void removeWarMachine() {
        WarMachine ballista1 = new WarMachine.Builder().statistic(WarMachineStatistic.BALLISTA).build();
        WarMachine ballista2 = new WarMachine.Builder().statistic(WarMachineStatistic.BALLISTA).build();
        WarMachine catapult = new WarMachine.Builder().statistic(WarMachineStatistic.CATAPULT).build();
        TemporaryTurnQueueWithoutCreatures temporaryTurnQueueWithoutCreatures1 = new TemporaryTurnQueueWithoutCreatures(List.of(ballista1, catapult), List.of(ballista2));
        assertEquals(temporaryTurnQueueWithoutCreatures1.getCurrentWarMachine(), ballista1);

        temporaryTurnQueueWithoutCreatures1.next();
        assertEquals(catapult, temporaryTurnQueueWithoutCreatures1.getCurrentWarMachine());

        temporaryTurnQueueWithoutCreatures1.next();
        assertEquals(ballista2,temporaryTurnQueueWithoutCreatures1.getCurrentWarMachine());

        temporaryTurnQueueWithoutCreatures1.next();
        assertEquals(ballista1, temporaryTurnQueueWithoutCreatures1.getCurrentWarMachine());

        temporaryTurnQueueWithoutCreatures1.removeWarMachine(catapult);
        temporaryTurnQueueWithoutCreatures1.next();
        assertEquals(catapult, temporaryTurnQueueWithoutCreatures1.getCurrentWarMachine());

        temporaryTurnQueueWithoutCreatures1.next();
        assertEquals(ballista2, temporaryTurnQueueWithoutCreatures1.getCurrentWarMachine());

        temporaryTurnQueueWithoutCreatures1.removeWarMachine(ballista2);
        assertEquals(ballista2, temporaryTurnQueueWithoutCreatures1.getCurrentWarMachine());

        temporaryTurnQueueWithoutCreatures1.next();
        assertEquals(ballista1, temporaryTurnQueueWithoutCreatures1.getCurrentWarMachine());

        temporaryTurnQueueWithoutCreatures1.next();
        assertNotEquals(ballista2, temporaryTurnQueueWithoutCreatures1.getCurrentWarMachine());
        assertEquals(ballista1, temporaryTurnQueueWithoutCreatures1.getCurrentWarMachine());

        temporaryTurnQueueWithoutCreatures1.next();
        assertEquals(ballista1, temporaryTurnQueueWithoutCreatures1.getCurrentWarMachine());

        temporaryTurnQueueWithoutCreatures1.next();
        assertEquals(ballista1, temporaryTurnQueueWithoutCreatures1.getCurrentWarMachine());

    }
}