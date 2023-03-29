// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.game;

import java.beans.PropertyChangeEvent;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class CreatureShooterDecorator extends Creature
{

    private final Creature decorated;

    public CreatureShooterDecorator( Creature aCreature )
    {
        super( null );
        decorated = aCreature;
    }

    @Override
    protected boolean shouldBeCounterAttacked()
    {
        return false;
    }

    @Override
    public int getCurrentHp()
    {
        return decorated.getCurrentHp();
    }

    @Override
    public int getMaxHp() {
        return decorated.getMaxHp();
    }

    @Override
    public void attack( Creature aDefender )
    {
        decorated.dealDamage( aDefender );
        if( shouldBeCounterAttacked() )
        {
            aDefender.dealDamage( this );
        }
    }

    @Override
    public void setDmgCalculator( DefaultDamageCalculator dmgCalculator )
    {
        decorated.setDmgCalculator( dmgCalculator );
    }

    @Override
    public int getAttack()
    {
        return decorated.getAttack();
    }

    @Override
    public int getArmor()
    {
        return decorated.getArmor();
    }

    @Override
    public String toString()
    {
        return decorated.toString();
    }

    @Override
    public int getMoveRange()
    {
        return decorated.getMoveRange();
    }

    @Override
    public void propertyChange( PropertyChangeEvent evt )
    {
        decorated.propertyChange( evt );
    }
}
