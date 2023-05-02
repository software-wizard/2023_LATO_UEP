package WarMachines;

/*
Class for ballista from war machines. Describes all behaviours of ballista. Ballista moves as a first War Machine in queue
 */

/*
Basic Artillery gives ballista a 50% chance of double damage
Advanced Artillery gives ballista a 75% chance of double damage
Expert Artillery gives ballista a 100% chance of double damage
 */

import lombok.Getter;

public class BallistaMachine {

    private int heroAttackPoints;
    private int heroDefense;
    @Getter
    private int ballistaDamage;
    @Getter
    private final int ballistaAttack = 10 + heroAttackPoints;
    @Getter
    private final int ballistaDefense = 10 + heroDefense;
    @Getter
    private final int ballistaHP = 250;
    @Getter
    private final int ballistaCost = 2500;
    @Getter
    private final int ballistaDefaultShotsNumber = 24;
//    0 = no artillery skill; 1 = basic artillery skill; 2 = advanced artillery skill; 3 = expert artillery skill
    private int heroArtillerySkill;

    public BallistaMachine(int aHeroAttackPoints, int aHeroDefense, int aHeroArtillerySkill) {
        heroAttackPoints = aHeroAttackPoints;
        heroDefense = aHeroDefense;
        heroArtillerySkill = aHeroArtillerySkill;
        ballistaDamage = calculateDamageFromBallista(aHeroAttackPoints);
    }

    public BallistaMachine(){}



    // Method uses given attack value to calculate damage which will be applied on creature attacked by ballista
    public int calculateDamageFromBallista(int attack){
        int lowerBoundary = 2 * (attack + 1);
        int upperBoundary = 3 * (attack + 1);
        float damage = (float) getRandomNumber(lowerBoundary, upperBoundary);
        damage = Math.round(damage);

//        If hero has artillery skill
        double probability = Math.random();
        if (heroArtillerySkill == 1 && probability >= 0.5){
            damage *= 2;
        } else if (heroArtillerySkill == 2) {
            if (probability >= 0.25){
                damage *=4;
            } else {
                damage *= 2;
            }
        } else if(heroArtillerySkill == 3){
            damage *=4;
        }
        return (int) damage;
    }

    private double getRandomNumber(int min, int max) {
        return ((Math.random() * (max - min)) + min);
    }
}
