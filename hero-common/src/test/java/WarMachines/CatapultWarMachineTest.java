package WarMachines;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatapultWarMachineTest {

    CatapultWarMachine catapultWarMachine = new CatapultWarMachine(0);

    @Test
    void getCatapultHP() {
        assertEquals(1000, catapultWarMachine.getCatapultHP());
    }

    @Test
    void getCatapultAttack() {
        assertEquals(10, catapultWarMachine.getCatapultAttack());
    }

    @Test
    void getCatapultShotsNumber() {
        assertEquals(24, catapultWarMachine.getCatapultShotsNumber());
    }

    @Test
    void getCatapultDefence() {
        assertEquals(10, catapultWarMachine.getCatapultDefence());
    }

    @Test
    void getCatapultCost() {
        assertEquals(2500, catapultWarMachine.getCatapultCost());
    }

    @Test
    void getCatapultFirstShotDamage() {
        CatapultWarMachine catapultWarMachine0 = new CatapultWarMachine(0);
        CatapultWarMachine catapultWarMachine1 = new CatapultWarMachine(1);
        CatapultWarMachine catapultWarMachine2 = new CatapultWarMachine(2);
        CatapultWarMachine catapultWarMachine3 = new CatapultWarMachine(3);

        int damage0 = catapultWarMachine0.getCatapultFirstShotDamage();
        assertTrue(0 <= damage0 && damage0 <= 2);

        int damage1 = catapultWarMachine1.getCatapultFirstShotDamage();
        assertTrue(1 <= damage1 && damage1 <= 2);

        int damage2 = catapultWarMachine2.getCatapultFirstShotDamage();
        assertTrue(1 <= damage2 && damage2 <= 2);

        int damage3 = catapultWarMachine3.getCatapultFirstShotDamage();
        assertEquals(2, damage3);
    }

    @Test
    void getCatapultSecondShotDamage() {
        CatapultWarMachine catapultWarMachine0 = new CatapultWarMachine(0);
        CatapultWarMachine catapultWarMachine1 = new CatapultWarMachine(1);
        CatapultWarMachine catapultWarMachine2 = new CatapultWarMachine(2);
        CatapultWarMachine catapultWarMachine3 = new CatapultWarMachine(3);

        int damage0 = catapultWarMachine0.getCatapultSecondShotDamage();
        assertEquals(0, damage0);

        int damage1 = catapultWarMachine1.getCatapultSecondShotDamage();
        assertEquals(0, damage1);

        int damage2 = catapultWarMachine2.getCatapultSecondShotDamage();
        assertTrue(1 <= damage2 && damage2 <= 2);

        int damage3 = catapultWarMachine3.getCatapultSecondShotDamage();
        assertEquals(2, damage3);
    }
}