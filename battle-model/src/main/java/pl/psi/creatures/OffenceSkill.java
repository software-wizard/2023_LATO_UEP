package pl.psi.creatures;

import com.google.common.annotations.VisibleForTesting;
import pl.psi.MapObjectIf;

import java.util.Random;

public class OffenceSkill extends AbstractCalculateDamageStrategy implements DamageCalculatorIf{

    private final DamageCalculatorIf decorated;

    public OffenceSkill(DamageCalculatorIf calculator) {
        decorated = calculator;
    }

    @VisibleForTesting
    protected OffenceSkill (Random aRand){
        this.decorated = new DefaultDamageCalculator(aRand);
    }

    @Override
    public int calculateDamage(Creature aAttacker, MapObjectIf aDefender) {
        return calculateOutcome(super.calculateDamage(aAttacker, aDefender));
    }

    //basic offence
    @Override
    public int calculateOutcome(int i) {
        return (int)(i * 1.1);
    }

    public void apply(Creature creature) {
        creature.setCalculator(new OffenceSkill(creature.getCalculator()));
    }
}
