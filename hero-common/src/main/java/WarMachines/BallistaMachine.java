package WarMachines;

/*
Class for ballista from war machines. Describes all behaviours of ballista
 */

public class BallistaMachine {


    public BallistaMachine() {
    }

    // Method uses given attack value to calculate damage wich will be applied on creature attacked by ballisa
    public int calculateDamageFromBallista(int attack){
        int lowerBoundary = 2 * (attack + 11);
        int upperBoundary = 3 * (attack + 11);
        float damage = (float) getRandomNumber(lowerBoundary, upperBoundary);
        return Math.round(damage);
    }

    public double getRandomNumber(int min, int max) {
        return ((Math.random() * (max - min)) + min);
    }
}
