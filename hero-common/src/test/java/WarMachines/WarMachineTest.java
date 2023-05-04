package WarMachines;

import org.junit.jupiter.api.Test;
import pl.psi.creatures.CreatureStatistic;

import static org.junit.jupiter.api.Assertions.*;

class WarMachineTest {

    //SHOULD RETURN 100
    @Test
    void getDamageINT() {
        WarMachine ballista = new WarMachine.Builder().statistic(WarMachineStatistic.BALLISTA).build();
        assertEquals(100,ballista.attack(new WarMachine()));
    }
}