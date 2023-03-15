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
public class DefaultDamageCalculator {

    double calculateDamage( int aRandomDmg, Creature aAttacker,Creature aDefender )
    {
        int diff = getDiff(aAttacker, aDefender);
        double percent;

        if( diff > 0 )
        {
            percent = 0.05 * diff;
        }
        else
        {
            percent = -0.025 * diff;
        }
        return aRandomDmg * (1.0 + percent);
    }

    int getDiff(Creature aAttacker, Creature aDefender) {
        return aAttacker.getAttack() - aDefender.getArmor();
    }
}
