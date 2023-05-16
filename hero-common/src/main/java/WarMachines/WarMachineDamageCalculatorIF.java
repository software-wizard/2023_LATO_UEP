package WarMachines;

public interface WarMachineDamageCalculatorIF {
    int calculateDamage(WarMachine aAttacker, MapObjectIf aDefender) throws Exception;
}
