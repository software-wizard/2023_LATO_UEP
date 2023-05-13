package pl.psi.creatures;

import java.util.Random;

public class DefenceSkill extends AbstractCalculateDamageStrategy implements DamageCalculatorIf{
    public int level;
    protected DefenceSkill(Random aRand, int level) {
        super(aRand);
    }

    private double calculateInfluence (int x){
       if (x == 0){
           return 1;
       } else if (x == 1) {
           return 1.1;
       } else if (x == 2) {
           return 1.2;
       } else {
           return 1.3;
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
