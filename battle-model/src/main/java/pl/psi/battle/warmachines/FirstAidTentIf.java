// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.battle.warmachines;

import pl.psi.eco.MapObjectIf;

public interface FirstAidTentIf {
    int calculateHealPoint(WarMachine aAttacker, MapObjectIf aAlly, int currentHP) throws Exception;
}
