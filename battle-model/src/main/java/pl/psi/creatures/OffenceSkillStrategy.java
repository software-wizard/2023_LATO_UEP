package pl.psi.creatures;

import java.util.Random;

public class OffenceSkillStrategy extends AbstractCalculateDamageStrategy implements DamageCalculatorIf{

    protected OffenceSkillStrategy(Random aRand) {
        super(aRand);
    }

    @Override
    protected int getArmor(Creature aDefender) {
        return super.getArmor(aDefender);
    }
    // po co to w offence skillu?

    @Override
    public int calculateDamage(Creature aAttacker, Creature aDefender) {
        return calculateOutcome(super.calculateDamage(aAttacker, aDefender));
    }

    //basic offence
    @Override
    public int calculateOutcome(int i) {
        return (int)(i * 1.1);
    }

}
