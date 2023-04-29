package WarMachines;

/*
Class for ballista from war machines. Describes all behaviours of ballista
 */

public class BallistaMachine {


    public BallistaMachine() {
    }

    // Method uses given attack value to calculate damage which will be applied on creature attacked by ballista
    public int calculateDamageFromBallista(int attack){
        int lowerBoundary = 2 * (attack + 1);
        int upperBoundary = 3 * (attack + 1);
        float damage = (float) getRandomNumber(lowerBoundary, upperBoundary);
        return Math.round(damage);
    }

    public double getRandomNumber(int min, int max) {
        return ((Math.random() * (max - min)) + min);
    }
}
