package pl.psi;

import WarMachines.WarMachine;
import WarMachines.WarMachineStats;
import org.junit.jupiter.api.Test;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewTurnQueueWithWarMachinesTest {

    final Creature creature1 = new Creature.Builder().statistic( CreatureStats.builder()
                    .build() )
            .build();
    final Creature creature2 = new Creature.Builder().statistic( CreatureStats.builder()
                    .build() )
            .build();
    final Creature creature3 = new Creature.Builder().statistic( CreatureStats.builder()
                    .build() )
            .build();

    final WarMachine warMachine1 = new WarMachine.Builder().statistic(WarMachineStats.builder().build()).build();
    final WarMachine warMachine2 = new WarMachine.Builder().statistic(WarMachineStats.builder().build()).build();
    final WarMachine warMachine3 = new WarMachine.Builder().statistic(WarMachineStats.builder().build()).build();
    final WarMachine warMachine4 = new WarMachine.Builder().statistic(WarMachineStats.builder().build()).build();
    NewTurnQueueWithWarMachines newTurnQueueWithWarMachines = new NewTurnQueueWithWarMachines( List.of( creature1, creature2 ), List.of( creature3 ), List.of(warMachine1, warMachine2), List.of(warMachine3, warMachine4) );

    @Test
    void getCurrentCreature() {
        assertEquals(newTurnQueueWithWarMachines.getCurrentCreature(), creature1);
    }

    @Test
    void getCurrentWarMachine() {
        assertEquals(newTurnQueueWithWarMachines.getCurrentWarMachine(), warMachine1);
    }

    @Test
    void shouldGoToNextCreatureAndWarMachine() {
        assertEquals(newTurnQueueWithWarMachines.getCurrentCreature(), creature1);
        assertEquals(newTurnQueueWithWarMachines.getCurrentWarMachine(), warMachine1);

        newTurnQueueWithWarMachines.next();

        assertEquals(newTurnQueueWithWarMachines.getCurrentCreature(), creature2);
        assertEquals(newTurnQueueWithWarMachines.getCurrentWarMachine(), warMachine2);

        newTurnQueueWithWarMachines.next();

        assertEquals(newTurnQueueWithWarMachines.getCurrentCreature(), creature3);
        assertEquals(newTurnQueueWithWarMachines.getCurrentWarMachine(), warMachine3);

        newTurnQueueWithWarMachines.next();

        assertTrue(newTurnQueueWithWarMachines.getCurrentCreature() == null);
        assertEquals(newTurnQueueWithWarMachines.getCurrentWarMachine(), warMachine4);

        newTurnQueueWithWarMachines.next();

        assertEquals(newTurnQueueWithWarMachines.getCurrentCreature(), creature1);
        assertEquals(newTurnQueueWithWarMachines.getCurrentWarMachine(), warMachine1);
    }
}