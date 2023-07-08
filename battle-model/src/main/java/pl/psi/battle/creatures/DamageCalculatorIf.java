// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.battle.creatures;

import pl.psi.eco.MapObjectIf;

public interface DamageCalculatorIf
{
    int calculateDamage(Creature aAttacker, MapObjectIf aDefender) throws Exception;

    int calculateOutcome(int i);
}

