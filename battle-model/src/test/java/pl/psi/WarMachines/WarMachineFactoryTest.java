package pl.psi.WarMachines;

import org.junit.jupiter.api.Test;
import pl.psi.GameEngine;
import pl.psi.Hero;
import pl.psi.Point;
import pl.psi.creatures.CastleCreatureFactory;
import pl.psi.warmachines.WarMachine;
import pl.psi.warmachines.WarMachineFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WarMachineFactoryTest {

    WarMachineFactory warMachineFactory = new WarMachineFactory();

    @Test
    void GameEngineWithWarMachineFactoryTest()
    {
        CastleCreatureFactory creatureFactory = new CastleCreatureFactory();

        final GameEngine gameEngine =
                new GameEngine(new Hero(List.of( creatureFactory.create( 1, false, 5 )), List.of(warMachineFactory.create(WarMachineStatistic.BALLISTA, 1, 1))),
                        new Hero(List.of( creatureFactory.create( 1, false, 5 )), List.of(warMachineFactory.create(WarMachineStatistic.CATAPULT, 1, 1))));

        gameEngine.attack( new Point( 1, 1 ) );

    }
    @Test
    void createWarMachineWithWarMachineFactoryAmmoCart()
    {

        WarMachine ammoCart = warMachineFactory.create(WarMachineStatistic.AMMO_CART,1,1);
        assertNotNull(ammoCart);
        assertEquals(ammoCart.getStats(), WarMachineStatistic.AMMO_CART);
        assertEquals(ammoCart.getAmount(), 1);
        assertEquals(ammoCart.getMaxHp(), WarMachineStatistic.AMMO_CART.getMaxHp());

    }

    @Test
    void createWarMachineWithWarMachineFactoryBallista()
    {

        WarMachine ballista = warMachineFactory.create(WarMachineStatistic.BALLISTA,1,2);
        assertNotNull(ballista);
        assertEquals(ballista.getStats(), WarMachineStatistic.BALLISTA);
        assertEquals(ballista.getAmount(), 2);
        assertEquals(ballista.getMaxHp(), WarMachineStatistic.BALLISTA.getMaxHp());

    }

    @Test
    void createWarMachineWithWarMachineFactoryCatapult()
    {

        WarMachine catapult = warMachineFactory.create(WarMachineStatistic.CATAPULT,1,10);
        assertNotNull(catapult);
        assertEquals(catapult.getStats(), WarMachineStatistic.CATAPULT);
        assertEquals(catapult.getAmount(), 10);
        assertEquals(catapult.getMaxHp(), WarMachineStatistic.CATAPULT.getMaxHp());

    }
    @Test
    void createWarMachineWithWarMachineFactoryFirstAidTent()
    {

        WarMachine firstAidTent = warMachineFactory.create(WarMachineStatistic.FIRST_AID_TENT,1,5);
        assertNotNull(firstAidTent);
        assertEquals(firstAidTent.getStats(), WarMachineStatistic.FIRST_AID_TENT);
        assertEquals(firstAidTent.getAmount(), 5);
        assertEquals(firstAidTent.getMaxHp(), WarMachineStatistic.FIRST_AID_TENT.getMaxHp());

    }
}
