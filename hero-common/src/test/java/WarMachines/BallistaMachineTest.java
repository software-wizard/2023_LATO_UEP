package WarMachines;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BallistaMachineTest {
    BallistaMachine ballistaMachine = new BallistaMachine();
    @Test
    void caclculateDamageFromBallista() {
        int attack = 5;
        int damage = ballistaMachine.caclculateDamageFromBallista(attack);
        assertTrue(2*(attack+11) <= damage && damage <= 3*(attack+11));
        assertFalse(damage < 2*(attack+11));
        assertFalse(damage > 3*(attack+11));
        assertFalse(damage > 100*attack);

    }

    @Test
    void getRandomNumber() {
        int lowerBoundary = 1;
        int upperBoundary = 2;
        double randomNumber = ballistaMachine.getRandomNumber(lowerBoundary,upperBoundary);
        assertTrue((lowerBoundary <= randomNumber) && (randomNumber <= upperBoundary));
        assertFalse(randomNumber < lowerBoundary);
        assertFalse(randomNumber > upperBoundary);
    }
}