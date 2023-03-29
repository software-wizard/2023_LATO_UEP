// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.game;

import lombok.Getter;
import lombok.Setter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */

public class Creature implements PropertyChangeListener
{
    private final CreatureStatistics stats;
    private final Random rand;
    @Getter
    private int currentHp;

    @Setter
    private DefaultDamageCalculator dmgCalculator = new DefaultDamageCalculator();

    public Creature( CreatureStatistics aStats )
    {
        this( aStats, new Random() );
    }

    public Creature( CreatureStatistics aStats, Random aRand )
    {
        stats = aStats;
        rand = aRand;
        currentHp = stats != null ? getMaxHp(): 1;
    }

    public int getMaxHp()
    {
        return stats.getMaxHp();
    }

    public void attack( Creature aDefender )
    {
        dealDamage( aDefender );
        if( shouldBeCounterAttacked() )
        {
            aDefender.dealDamage( this );
        }
    }

    protected boolean shouldBeCounterAttacked()
    {
        return true;
    }

    void dealDamage(Creature aDefender)
    {
        int randomDmg = (int)(rand.nextDouble() * (stats.getDamage()
            .upperEndpoint()
            - stats.getDamage()
                .lowerEndpoint()))
            + stats.getDamage()
                .lowerEndpoint();
        double dmgToDeal = dmgCalculator.calculateDamage( randomDmg, this, aDefender );
        aDefender.currentHp = aDefender.currentHp - (int)dmgToDeal;
    }

    public int getAttack()
    {
        return stats.getAttack();
    }

    public int getArmor()
    {
        return stats.getArmor();
    }

    @Override
    public String toString()
    {
        return stats.getName();
    }

    public int getMoveRange()
    {
        return stats.getMoveRange();
    }

    @Override
    public void propertyChange( PropertyChangeEvent evt )
    {
        if( evt.getPropertyName()
            .equals( "END_OF_TURN" ) )
        {

        }
    }

    // public class Builder{
    //
    // }
}
