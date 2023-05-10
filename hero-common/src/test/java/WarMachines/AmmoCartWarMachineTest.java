package WarMachines;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmmoCartWarMachineTest {

    AmmoCartWarMachine ammoCartWarMachine = new AmmoCartWarMachine();

    @Test
    void getAmmoCartHP() {
        assertEquals(100, ammoCartWarMachine.getAmmoCartHP());
    }

    @Test
    void getAmmoCartCost() {
        assertEquals(1000, ammoCartWarMachine.getAmmoCartCost());
    }

    @Test
    void getAmmoCartDefence() {
        assertEquals(5, ammoCartWarMachine.getAmmoCartDefence());
    }
}