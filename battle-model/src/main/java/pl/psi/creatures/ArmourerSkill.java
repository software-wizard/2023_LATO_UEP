package pl.psi.creatures;

import com.google.common.annotations.VisibleForTesting;
import lombok.ToString;

import java.util.Random;

public class ArmourerSkill extends AbstractCalculateDamageStrategy implements DamageCalculatorIf{
    private final DamageCalculatorIf decorated;

    public ArmourerSkill(DamageCalculatorIf calculator) {
        decorated = calculator;
    }

    @VisibleForTesting
    protected ArmourerSkill (Random aRand){
        this.decorated = new DefaultDamageCalculator(aRand);
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

    public void apply(Creature creature) {
        creature.setCalculator(new ArmourerSkill(creature.getCalculator()));
    }
}
