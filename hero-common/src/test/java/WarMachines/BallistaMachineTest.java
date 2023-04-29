package WarMachines;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BallistaMachineTest {
    BallistaMachine ballistaMachine = new BallistaMachine();
    @Test
    void calculateDamageFromBallista() {
        int attack = 5;
        int damage = ballistaMachine.calculateDamageFromBallista(attack);
        assertTrue(2*(attack+1) <= damage && damage <= 3*(attack+1));
        assertFalse(damage < 2*(attack+1));
        assertFalse(damage > 3*(attack+1));
        assertFalse(damage > 100*attack);
    }

    @Test
    void calculateAverageDamageFromBallista(){
        int attack = 25;
        int expectedDamage = (2*(attack+1) + 3*(attack+1))/2;
        int averageDamage = 0;
        int iterations = 1000000;
        for (int i = 0; i < iterations; i++) {
            averageDamage += ballistaMachine.calculateDamageFromBallista(attack);
        }
        averageDamage /= iterations;
        averageDamage = Math.round(averageDamage);

        assertEquals(expectedDamage, averageDamage);
        assertEquals(65,averageDamage);
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