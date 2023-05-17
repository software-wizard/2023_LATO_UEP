package pl.psi.warmachines;

import pl.psi.MapObjectIf;

public interface FirstAidTentIf {
    int calculateHealPoint(WarMachine aAttacker, MapObjectIf aComrade, int currentHP) throws Exception;
}
