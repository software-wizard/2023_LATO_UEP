package pl.psi.creatures;

import lombok.ToString;

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
           return 0.95;
       } else if (x == 2) {
           return 0.90;
       } else {
           return 0.85;
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
