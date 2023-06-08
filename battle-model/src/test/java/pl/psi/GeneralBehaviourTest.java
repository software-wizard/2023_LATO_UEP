package pl.psi;

import org.junit.jupiter.api.Test;
import pl.psi.WarMachines.WarMachineStatistic;
import pl.psi.creatures.Creature;
import pl.psi.creatures.NecropolisFactory;
import pl.psi.warmachines.WarMachine;
import pl.psi.warmachines.WarMachineFactory;

import static org.junit.jupiter.api.Assertions.*;

class GeneralBehaviourTest {

    @Test
    void catapultBehaviour() {
        WarMachine catapult = new WarMachineFactory().create(WarMachineStatistic.CATAPULT, 1, 1);
        assertTrue(catapult.canAttack());
        assertFalse(catapult.canHeal());
    }

    @Test
    void ballistaBehaviour() {
        WarMachine ballista = new WarMachineFactory().create(WarMachineStatistic.BALLISTA, 1, 1);
        assertTrue(ballista.canAttack());
        assertFalse(ballista.canHeal());
    }

    @Test
    void firstAidTentBehaviour() {
        WarMachine firstAidTent = new WarMachineFactory().create(WarMachineStatistic.FIRST_AID_TENT, 1, 1);
        assertFalse(firstAidTent.canAttack());
        assertTrue(firstAidTent.canHeal());
    }

    @Test
    void ammoCartBehaviour() {
        WarMachine ammoCart = new WarMachineFactory().create(WarMachineStatistic.AMMO_CART, 1, 1);
        assertFalse(ammoCart.canAttack());
        assertFalse(ammoCart.canHeal());
    }

    @Test
    void creatureBehaviour(){
        Creature skeleton = new NecropolisFactory().create(false, 1,1 );
        assertTrue(skeleton.canAttack());
        assertFalse(skeleton.canHeal());

        Creature walkingDead = new NecropolisFactory().create(true, 2,1 );
        assertTrue(walkingDead.canAttack());
        assertFalse(walkingDead.canHeal());
    }


}