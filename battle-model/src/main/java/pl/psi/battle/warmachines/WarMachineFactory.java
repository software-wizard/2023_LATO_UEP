// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.battle.warmachines;

import pl.psi.eco.WarMachines.WarMachineStatistic;

public class WarMachineFactory {

    public WarMachine create(WarMachineStatistic aName, int aSkillLevel, final int amount )
    {
        switch(aName) {
            case BALLISTA:
                return new Ballista(amount, aSkillLevel);
            case CATAPULT:
                return new Catapult(amount, aSkillLevel);

            case FIRST_AID_TENT:
                return new FirstAidTent(amount, aSkillLevel);
        }
        throw new IllegalArgumentException( "Cannot recognize War Machine by name" );
    }
}
