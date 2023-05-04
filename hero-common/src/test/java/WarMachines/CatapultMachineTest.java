package WarMachines;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatapultMachineTest {

    CatapultMachine catapultMachine = new CatapultMachine(0);

//    @Test
//    void getCatapultHP() {
//        assertEquals(1000, catapultMachine.getCatapultHP());
//    }
//
//    @Test
//    void getCatapultAttack() {
//        assertEquals(10, catapultMachine.getCatapultAttack());
//    }
//
//    @Test
//    void getCatapultShotsNumber() {
//        assertEquals(24, catapultMachine.getCatapultShotsNumber());
//    }
//
//    @Test
//    void getCatapultDefence() {
//        assertEquals(10, catapultMachine.getCatapultDefence());
//    }
//
//    @Test
//    void getCatapultCost() {
//        assertEquals(2500, catapultMachine.getCatapultCost());
//    }

    @Test
    void getCatapultFirstShotDamage() {
        CatapultMachine catapultMachine0 = new CatapultMachine(0);
        CatapultMachine catapultMachine1 = new CatapultMachine(1);
        CatapultMachine catapultMachine2 = new CatapultMachine(2);
        CatapultMachine catapultMachine3 = new CatapultMachine(3);

        int damage0 = catapultMachine0.getCatapultFirstShotDamage();
        assertTrue(0 <= damage0 && damage0 <= 2);

        int damage1 = catapultMachine1.getCatapultFirstShotDamage();
        assertTrue(1 <= damage1 && damage1 <= 2);

        int damage2 = catapultMachine2.getCatapultFirstShotDamage();
        assertTrue(1 <= damage2 && damage2 <= 2);

        int damage3 = catapultMachine3.getCatapultFirstShotDamage();
        assertEquals(2, damage3);
    }

    @Test
    void getCatapultSecondShotDamage() {
        CatapultMachine catapultMachine0 = new CatapultMachine(0);
        CatapultMachine catapultMachine1 = new CatapultMachine(1);
        CatapultMachine catapultMachine2 = new CatapultMachine(2);
        CatapultMachine catapultMachine3 = new CatapultMachine(3);

        int damage0 = catapultMachine0.getCatapultSecondShotDamage();
        assertEquals(0, damage0);

        int damage1 = catapultMachine1.getCatapultSecondShotDamage();
        assertEquals(0, damage1);

        int damage2 = catapultMachine2.getCatapultSecondShotDamage();
        assertTrue(1 <= damage2 && damage2 <= 2);

        int damage3 = catapultMachine3.getCatapultSecondShotDamage();
        assertEquals(2, damage3);
    }
}