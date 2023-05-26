package pl.psi.warmachines;

import WarMachines.WarMachineStatistic;
import pl.psi.MapObjectIf;

public class WarMachineDamageCalculator implements WarMachineDamageCalculatorIF {
    public int calculateDamage(WarMachine aAttacker, MapObjectIf aDefender) throws Exception {
        int damage;
        if (aAttacker.getStats().equals(WarMachineStatistic.BALLISTA)){
            //todo real hero artillery skill value and heroAttack value must be used
            damage = calculateDamageFromBallista(3, 0, 3, 0, WarMachineStatistic.BALLISTA.getAttack()+3);
        } else if (aAttacker.getStats().equals(WarMachineStatistic.CATAPULT)) {
            //todo real hero ballistics skill value must be used
            damage = calculateFirstShotDamageFromCatapult(0, 3,1, WarMachineStatistic.CATAPULT.getAttack()+3) + calculateSecondShotDamageFromCatapult(0, 3,1, WarMachineStatistic.CATAPULT.getAttack()+3);
        } else{
            throw new Exception("Only ballista and catapult can apply damage");
        }
        return damage;
    }

    // Method uses given attack value to calculate damage which will be applied on creature attacked by ballista
    private int calculateDamageFromBallista(int heroAttack, int heroArtillerySkill, int heroArcherySkill, int defenderDefenseSkill, int ballistaAttack){

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
        lowerBoundary *= (heroAttack + 1);
        upperBoundary *= (heroAttack + 1);
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

        //Heroes' defense skill is added to the defense rating of each of the creatures in their army,
        //which decreases the amount of damage they take from enemy attacks.
        //For each extra point of Defense skill over attackers's Attack skill,
        //the received damage gets reduced by 2.5 % (up to 70 %, which is 28 points in difference).

        //For each extra point of Attack skill over defender's Defense skill,
        //the inflicted damage gets increased by 5 % (up to 300 %, which is 60 points in difference).

        int pointDifference;

        if (ballistaAttack > defenderDefenseSkill) {
            pointDifference = ballistaAttack - defenderDefenseSkill;
            if (pointDifference>=60) {
                damage *=3;
            } else {
                damage = (float) (damage*pointDifference*1.05);
            }

        } else if (ballistaAttack < defenderDefenseSkill) {
            pointDifference = defenderDefenseSkill - ballistaAttack;
            if (pointDifference>=28) {
                damage *= 1.7;
            } else {
                damage = (float) (damage*pointDifference*0.975);
            }

        }

        return (int) damage;
    }

    private double getRandomNumber(float min, float max) {
        return ((Math.random() * (max - min)) + min);
    }

    private int calculateFirstShotDamageFromCatapult(int heroBallisticSkills, int heroAttack, int defenderDefenseSkill, int catapultAttack){
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


        int pointDifference;

        if (catapultAttack > defenderDefenseSkill) {
            pointDifference = catapultAttack - defenderDefenseSkill;
            if (pointDifference>=60) {
                damage *=3;
            } else {
                damage = (int) (damage*pointDifference*1.05);
            }

        } else if (catapultAttack < defenderDefenseSkill) {
            pointDifference = defenderDefenseSkill - catapultAttack;
            if (pointDifference>=28) {
                damage *= 1.7;
            } else {
                damage = (int) (damage*pointDifference*0.975);
            }

        }


        return damage;
    }

    private int calculateSecondShotDamageFromCatapult(int heroBallisticSkills, int heroAttack, int defenderDefenseSkill, int catapultAttack) {
        int damage = 0;
        if (heroBallisticSkills >= 2){
            damage = calculateFirstShotDamageFromCatapult(heroBallisticSkills, heroAttack, defenderDefenseSkill, catapultAttack);
        }
        int pointDifference;

        if (catapultAttack > defenderDefenseSkill) {
            pointDifference = catapultAttack - defenderDefenseSkill;
            if (pointDifference>=60) {
                damage *=3;
            } else {
                damage = (int) (damage*pointDifference*1.05);
            }

        } else if (catapultAttack < defenderDefenseSkill) {
            pointDifference = defenderDefenseSkill - catapultAttack;
            if (pointDifference>=28) {
                damage *= 1.7;
            } else {
                damage = (int) (damage*pointDifference*0.975);
            }

        }

        return damage;
    }
}
