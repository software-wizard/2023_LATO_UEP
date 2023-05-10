package pl.psi;

import WarMachines.WarMachine;
import WarMachines.WarMachineStats;
import org.junit.jupiter.api.Test;

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
}