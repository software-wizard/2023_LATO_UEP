package pl.psi.creatures;

import java.util.Random;

public class OffenceSkill extends AbstractCalculateDamageStrategy implements DamageCalculatorIf{

    protected OffenceSkill(Random aRand) {
        super(aRand);
    }
    @Override
    public int calculateDamage(Creature aAttacker, Creature aDefender) {
        return calculateOutcome(super.calculateDamage(aAttacker, aDefender));
    }

    //basic offence
    @Override
    public int calculateOutcome(int i) {
        return (int)(i * 1.1);
    }

    public void apply(Creature attacker) {

        //TODO implement this method

    }
}
