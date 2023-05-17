package pl.psi.creatures;

import java.util.Random;

public class DefenseSkill extends AbstractCalculateDamageStrategy implements DamageCalculatorIf{

    private static final int MAX_DEFENCE_DIFF = 28;
    private static final double DEFENCE_BONUS = 0.025;

    protected DefenseSkill(Random aRand){
        super(aRand);

    }

    @Override
    public int calculateDamage(Creature aAttacker, Creature aDefender) {
        return calculateOutcome(super.calculateDamage(aAttacker, aDefender));
    }

    // Defense skill modification
    @Override
    public int calculateOutcome(int i) {
        double reductionPercentage = calculateDamageReduction(i);
        return (int) (i * reductionPercentage);
    }
    private double calculateDamageReduction(int damage) {
        int defencePoints = damage / 2; // Calculate the difference between attacker's damage and defender's defense
        int cappedDifference = Math.min(defencePoints, MAX_DEFENCE_DIFF); // Cap the difference at the maximum allowed value
        double reductionPercentage = cappedDifference * DEFENCE_BONUS; // Calculate the reduction percentage based on the difference
        return 1 - reductionPercentage; // Return the resulting damage reduction percentage
    }
    public void apply(Creature defender) {
        // TODO: Implement this method
    }
}
