package WarMachines;

/*
Catapult is always with Hero. It can't be purchased. Hex size: 2
 */

import lombok.Getter;

public class CatapultMachine {
    @Getter
    private final int catapultFirstShotDamage;
    @Getter
    private final int catapultSecondShotDamage;

    //    0 = no ballistic skill; 1 = basic ballistic skill; 2 = advanced ballistic skill; 3 = expert ballistic skill
    public CatapultMachine(int heroBallisticSkills) {
        this.catapultFirstShotDamage = calculateFirstShotDamage(heroBallisticSkills);
        this.catapultSecondShotDamage = calculateSecondShotDamage(heroBallisticSkills);
    }

    private int calculateFirstShotDamage(int heroBallisticSkills){
        double probability = Math.random();
        int damage;
        switch (heroBallisticSkills){
            case 0:
                if (probability <= 0.1){
                    damage = 0;
                } else if (probability <= 0.4) {
                    damage = 2;
                } else {
                    damage = 1;
                }
                break;
            case 1:
            case 2:
                if (probability <= 0.5){
                    damage = 1;
                } else {
                    damage = 2;
                }
                break;
            case 3:
                damage = 2;
                break;
            default:
                damage = 0;
        }
        return damage;
    }

    private int calculateSecondShotDamage(int heroBallisticSkills) {
        int damage = 0;
        if (heroBallisticSkills >= 2){
            damage = calculateFirstShotDamage(heroBallisticSkills);
        }
        return damage;
    }
}
