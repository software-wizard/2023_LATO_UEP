package pl.psi.warmachines;

import pl.psi.MapObjectIf;

public interface WarMachineDamageCalculatorIF {
    int calculateDamage(WarMachine aAttacker, MapObjectIf aDefender) throws Exception;
}
