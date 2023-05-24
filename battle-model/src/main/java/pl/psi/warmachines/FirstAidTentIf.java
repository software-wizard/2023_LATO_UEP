package pl.psi.warmachines;

import pl.psi.MapObjectIf;

public interface FirstAidTentIf {
    int calculateHealPoint(WarMachine aAttacker, MapObjectIf aAlly, int currentHP) throws Exception;
}
