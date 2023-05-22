package pl.psi.creatures;

import com.google.common.annotations.VisibleForTesting;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
public class Skill extends AbstractCalculateDamageStrategy implements DamageCalculatorIf {
    @Setter
    public int level;

    // TODO change Skill to match new properties

    private final DamageCalculatorIf decorated;

    public Skill(DamageCalculatorIf calculator) {
        decorated = calculator;
    }

    public void apply(Creature creature) {
    }
}
