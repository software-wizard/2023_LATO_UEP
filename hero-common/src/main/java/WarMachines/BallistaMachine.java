package WarMachines;

/*
Class for ballista from war machines. Describes all behaviours of ballista. Ballista moves as a first War Machine in queue
 */

/*
Basic Artillery gives ballista a 50% chance of double damage
Advanced Artillery gives ballista a 75% chance of double damage
Expert Artillery gives ballista a 100% chance of double damage

Basic Archery increases ballista's base damage (2-3) by 10%
Advanced Archery increases ballista's base damage (2-3) by 25%
Expert Archery increases ballista's base damage (2-3) by 50%
 */

import lombok.Getter;

public class BallistaMachine {

    private int heroAttackPoints;
    private int heroDefense;
    @Getter
    private int ballistaDamage;

//    0 = no artillery skill; 1 = basic artillery skill; 2 = advanced artillery skill; 3 = expert artillery skill
    private int heroArtillerySkill;

//    0 = no archery skill; 1 = basic archery skill; 2 = advanced archery skill; 3 = expert archery skill
    private int heroArcherySkill;

    public BallistaMachine(int aHeroAttackPoints, int aHeroDefense, int aHeroArtillerySkill, int aHeroArcherySkill) {
        heroAttackPoints = aHeroAttackPoints;
        heroDefense = aHeroDefense;
        heroArtillerySkill = aHeroArtillerySkill;
        heroArcherySkill = aHeroArcherySkill;
        ballistaDamage = calculateDamageFromBallista(aHeroAttackPoints);
    }

    public BallistaMachine(){

    }

    // Method uses given attack value to calculate damage which will be applied on creature attacked by ballista
    public int calculateDamageFromBallista(int attack){

    //Base damage (heroAttackPoints = 0)
        float lowerBoundary = 2;
        float upperBoundary = 3;


    /*
    If hero has archery skill - this has to be calculated before the added bonus of artillery skill AND attack skill

    As opposed to slightly misleading in-game skill description Archery increases the base damage of ranged attacks
    by 10%, 25% or 50% depending on the level of the skill. This does not mean that the total damage (final damage)
    is necessarily increased by the same percentage, as it might also be increased by other factors,
    such as high Attack skill - compare damage formula.
    */
        if (heroArcherySkill == 1){
            lowerBoundary *= 1.1;
            upperBoundary *= 1.1;
        } else if (heroArcherySkill == 2) {
            lowerBoundary *= 1.25;
            upperBoundary *= 1.25;
        } else if(heroArcherySkill == 3){
            lowerBoundary *= 1.5;
            upperBoundary *= 1.5;
        }


        lowerBoundary *= (attack + 1);
        upperBoundary *= (attack + 1);
        float damage = (float) getRandomNumber(lowerBoundary, upperBoundary);
        damage = Math.round(damage);



    //If hero has artillery skill
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
            damage *= 4;
        }
        return (int) damage;


    }

    private double getRandomNumber(float min, float max) {
        return ((Math.random() * (max - min)) + min);
    }
}
