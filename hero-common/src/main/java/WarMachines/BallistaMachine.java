package WarMachines;

/*
Class for ballista from war machines. Describes all behaviours of ballista. Ballista moves as a first War Machine in queue
 */

/*
Basic Artillery gives ballista a 50% chance of double damage
Advanced Artillery gives ballista a 75% chance of double damage
Expert Artillery gives ballista a 100% chance of double damage
 */

import com.google.common.collect.Range;
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
        ballistaDamage = DamageCalculatorWarMachine.calculateDamageFromBallista(aHeroAttackPoints);
    }

    public BallistaMachine(){

    }


}
