// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.battle.warmachines;

import pl.psi.eco.MapObjectIf;

public interface WarMachineDamageCalculatorIF {
    int calculateDamage(WarMachine aAttacker, MapObjectIf aDefender) throws Exception;
}
