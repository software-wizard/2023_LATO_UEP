// ******************************************************************
//  
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//  
// ******************************************************************

package pl.psi.game;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class ReduceArmorDamageCalculator extends DefaultDamageCalculator
{
    private final double factor;

    public ReduceArmorDamageCalculator( double aFactor )
    {
        factor = aFactor;
    }

    @Override
    int getDiff( Creature aAttacker, Creature aDefender )
    {
        return aAttacker.getAttack() - (int)factor * aDefender.getArmor();
    }
}
