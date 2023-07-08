// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.battle.creatures;

import pl.psi.eco.MapObjectIf;

import java.util.Random;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
class ReduceDefenceCalculator extends AbstractCalculateDamageStrategy
{

    private final double factor;

    public ReduceDefenceCalculator()
    {
        super( new Random() );
        factor = 0.2;
    }

    @Override
    protected int getArmor( final MapObjectIf aDefender )
    {
        return (int)(aDefender.getArmor() * factor);
    }
}
