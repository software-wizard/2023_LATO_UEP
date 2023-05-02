package WarMachines;

public class DamageCalculatorWarMachine {
    private int heroAttackPoints;
    private int heroDefense;
    private static int heroArtillerySkill;

    // Method uses given attack value to calculate damage which will be applied on creature attacked by ballista
    public static int calculateDamageFromBallista(int attack){
        int lowerBoundary = 2 * (attack + 1);
        int upperBoundary = 3 * (attack + 1);
        float damage = getRandomNumber(lowerBoundary, upperBoundary);
        damage = Math.round(damage);

    // If hero has artillery skill
        double probability = Math.random();
        if (heroArtillerySkill == 1 && probability >= 0.5){
            damage *= 2;
        } else if (heroArtillerySkill == 2) {
            if (probability > 0.25){
                damage *= 4;
            } else {
                damage *= 2;
            }
        } else if(heroArtillerySkill == 3){
            damage *= 6;
        }

        return (int) damage;
    }
    private static float getRandomNumber(int min, int max) {
        return (float) (Math.random() * (max - min)) + min;
    }
}
