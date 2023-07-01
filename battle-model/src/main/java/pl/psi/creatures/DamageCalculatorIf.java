package pl.psi.creatures;

import pl.psi.MapObjectIf;

public interface DamageCalculatorIf
{
    int calculateDamage(Creature aAttacker, MapObjectIf aDefender) throws Exception;

    int calculateOutcome(int i);
}

