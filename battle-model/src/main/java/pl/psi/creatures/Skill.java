package pl.psi.creatures;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
public class Skill extends AbstractCalculateDamageStrategy implements DamageCalculatorIf{
    @Setter
    public int level;
    protected Skill(Random aRand) {
        super(aRand);
    }
}
