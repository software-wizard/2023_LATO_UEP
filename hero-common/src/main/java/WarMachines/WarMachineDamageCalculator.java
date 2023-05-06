package WarMachines;

public class WarMachineDamageCalculator implements WarMachineDamageCalculatorIF {
    public int calculateDamage(WarMachine aAttacker, WarMachine aDefender) throws Exception {
        int damage;
        if (aAttacker.getStats().equals(WarMachineStatistic.BALLISTA)){
            //todo real hero artillery skill value and heroAttack value must be used
            // lack of archery skill verification
            damage = calculateDamageFromBallista(5, 0);
        } else if (aAttacker.getStats().equals(WarMachineStatistic.CATAPULT)) {
            //todo real hero ballistics skill value must be used
            damage = calculateFirstShotDamageFromCatapult(0) + calculateSecondShotDamageFromCatapult(0);
        } else{
            throw new Exception("Only ballista and catapult can apply damage");
        }
        return damage;
    }

    // Method uses given heroAttack value to calculate damage which will be applied on creature attacked by ballista
    public int calculateDamageFromBallista(int heroAttack, int heroArtillerySkill){
        int lowerBoundary = 2 * (heroAttack + 1);
        int upperBoundary = 3 * (heroAttack + 1);
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

    private double getRandomNumber(int min, int max) {
        return ((Math.random() * (max - min)) + min);
    }

    private int calculateFirstShotDamageFromCatapult(int heroBallisticSkills){
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

    private int calculateSecondShotDamageFromCatapult(int heroBallisticSkills) {
        int damage = 0;
        if (heroBallisticSkills >= 2){
            damage = calculateFirstShotDamageFromCatapult(heroBallisticSkills);
        }
        return damage;
    }
}
