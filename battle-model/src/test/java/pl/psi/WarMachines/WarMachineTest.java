package pl.psi.WarMachines;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pl.psi.AttackerIF;
import pl.psi.warmachines.WarMachine;
import pl.psi.warmachines.WarMachineFactory;

import static org.junit.jupiter.api.Assertions.*;

class WarMachineTest {
    // Changed all WarMachine.Builder() to WarMachineFactory().create()

    @Test
    void shouldLoseHP() throws Exception {
        WarMachine ammoCart = new WarMachineFactory().create(WarMachineStatistic.AMMO_CART, 1, 1);
        assertEquals(100, ammoCart.getCurrentHp());
        WarMachine ballista = new WarMachineFactory().create(WarMachineStatistic.BALLISTA, 1, 1);
        ((AttackerIF) ballista).attack(ammoCart);
        assertTrue(ammoCart.getCurrentHp() < 100);
        System.out.println("Ammo cart hp after first attack: " + ammoCart.getCurrentHp());
        ((AttackerIF) ballista).attack(ammoCart);
        System.out.println("Ammo cart hp after second attack: " + ammoCart.getCurrentHp());
    }

    @Test
    void getCurrentHp() throws Exception {
        WarMachine ballista = new WarMachineFactory().create(WarMachineStatistic.BALLISTA, 1, 1);
        WarMachine catapult = new WarMachineFactory().create(WarMachineStatistic.CATAPULT, 1, 1);
        assertEquals(1000, catapult.getCurrentHp());
        assertEquals(1, catapult.getAmount());

        ((AttackerIF) ballista).attack(catapult);
        assertTrue(catapult.getCurrentHp() < 1000);
        assertEquals(1, catapult.getAmount());

        for (int i = 0; i < 7; i++) {
            ((AttackerIF) ballista).attack(catapult);
        }

        assertEquals(0, catapult.getAmount());
    }

    //    @Test
//    void attackTEST() throws Exception {
//        int avgDamage = 0;
//        for (int i = 0; i < 10000; i++) {
//            WarMachine ballista = new WarMachine.Builder().statistic(WarMachineStatistic.BALLISTA).build();
//            avgDamage += ballista.attack(new WarMachine());
//        }
//        avgDamage /= 10000;
//        assertEquals(250, avgDamage);
//        WarMachine ammoCart = new WarMachine.Builder().statistic(WarMachineStatistic.AMMO_CART).build();
//        try {
//            ammoCart.attack(new WarMachine());
//        } catch (Exception e){
//            System.err.println(e);
//        }
//        WarMachine catapult = new WarMachine.Builder().statistic(WarMachineStatistic.CATAPULT).build();
//        int catapultDamage = catapult.attack(new WarMachine());
//        assertTrue(0 <= catapultDamage && catapultDamage <= 2);
//        int iteration = 0;
//        while (true){
//            iteration++;
//            catapultDamage = catapult.attack(new WarMachine());
//            if (catapultDamage == 2){
//                System.out.println("Catapult damage: " + catapultDamage);
//                System.out.println("Number of iterations: " + iteration);
//                break;
//            }
//        }
//    }
    @Test //Test works for ArtillerySkill = 0 and ArcherySkill = 3
    void shouldSubtractAmount() throws Exception {
        WarMachine ballista = new WarMachine.Builder().statistic(WarMachineStatistic.BALLISTA).build();
        MapObjectIf dreadKnight = new NecropolisFactory().create( true, 6, 5);

        ballista.attack(dreadKnight);
        assertTrue(dreadKnight.getAmount() <= 5);
        assertFalse(dreadKnight.getAmount() < 0);
        assertTrue(dreadKnight.getCurrentHp() <= dreadKnight.getMaxHp());

        ballista.attack(dreadKnight);
        assertTrue(dreadKnight.getAmount() <= 3);
        assertFalse(dreadKnight.getAmount() < 0);
        assertTrue(dreadKnight.getCurrentHp() <= dreadKnight.getMaxHp());

        ballista.attack(dreadKnight);
        assertTrue(dreadKnight.getAmount() <= 1);
        assertFalse(dreadKnight.getAmount() < 0);
        assertTrue(dreadKnight.getCurrentHp() <= dreadKnight.getMaxHp());

    }
}