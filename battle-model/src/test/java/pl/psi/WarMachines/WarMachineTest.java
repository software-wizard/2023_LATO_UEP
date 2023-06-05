package pl.psi.WarMachines;

import org.junit.jupiter.api.Test;
import pl.psi.MapObjectIf;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatistic;
import pl.psi.creatures.NecropolisFactory;
import pl.psi.warmachines.WarMachine;
import pl.psi.warmachines.WarMachineFactory;

import static org.junit.jupiter.api.Assertions.*;

class WarMachineTest {

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

    @Test
    void shouldLoseHP() throws Exception {
        WarMachine ammoCart = new WarMachine.Builder().statistic(WarMachineStatistic.AMMO_CART).amount(1).build();
        assertEquals(100, ammoCart.getCurrentHp());
        WarMachine ballista = new WarMachine.Builder().statistic(WarMachineStatistic.BALLISTA).amount(1).build();
        ballista.attack(ammoCart);
        assertTrue(ammoCart.getCurrentHp() < 100);
        System.out.println("Ammo cart hp after first attack: " + ammoCart.getCurrentHp());
        ballista.attack(ammoCart);
        System.out.println("Ammo cart hp after second attack: " + ammoCart.getCurrentHp());
    }

    @Test
    void getCurrentHp() throws Exception {
        WarMachine ballista = new WarMachine.Builder().statistic(WarMachineStatistic.BALLISTA).build();
        WarMachine catapult = new WarMachine.Builder().statistic(WarMachineStatistic.CATAPULT).build();
        assertEquals(1000, catapult.getCurrentHp());

        ballista.attack(catapult);
        assertTrue(catapult.getCurrentHp() < 1000);

        for (int i = 0; i < 10; i++) {
            ballista.attack(catapult);
            int hp = catapult.getCurrentHp();
        }

        assertEquals(0, catapult.getCurrentHp());
    }
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