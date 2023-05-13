package pl.psi.creatures;

import java.util.Random;

public class ArmourerSkill extends AbstractCalculateDamageStrategy implements DamageCalculatorIf{
    public int level;
    protected ArmourerSkill(Random aRand, int level) {
        super(aRand);
    }

    private double calculateInfluence (int x){
       if (x == 0){
           return 1;
       } else if (x == 1) {
           return 0.9;
       } else if (x == 2) {
           return 0.8;
       } else {
           return 0.7;
       }
    }

    @Override
    public int calculateDamage(Creature aAttacker, Creature aDefender) {
        return calculateOutcome(super.calculateDamage(aAttacker, aDefender));
    }

    //basic offence
    @Override
    public int calculateOutcome(int i) {
        return (int)(i * calculateInfluence(level));
    }
}
