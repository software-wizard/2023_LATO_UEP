package pl.psi.warmachines;

import WarMachines.WarMachineStatistic;
import WarMachines.WarMachineStatisticIf;
import pl.psi.MapObjectIf;
import pl.psi.creatures.Creature;

public class FirstAidTent extends WarMachine{
    @Override
    public boolean canHeal() {
        return true;
    }

    @Override
    public boolean canAttack() {
        return false;
    }
}
