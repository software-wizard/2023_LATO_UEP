package pl.psi.creatures;

import WarMachines.WarMachine;

public interface DamageCalculatorIf
{
    int calculateDamage( Creature aAttacker, Creature aDefender );
}
