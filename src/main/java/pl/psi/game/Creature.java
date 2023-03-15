// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.game;

import com.google.common.collect.Range;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Getter
public class Creature
{
    private final Random rand;
    private final Range< Integer > damage;
    private final int maxHp;
    private final int attack;
    private final int armor;
    private int currentHp;

    @Setter
    private DefaultDamageCalculator dmgCalculator = new DefaultDamageCalculator();

    public Creature( Range< Integer > aDamage, int aMaxHp )
    {
        this( aDamage, aMaxHp, 0, 0, new Random() );
    }

    Creature( Range< Integer > aDamage, int aMaxHp, Random aRand )
    {
        this( aDamage, aMaxHp, 0, 0, aRand );
    }

    public Creature( Range< Integer > aDamage, int aMaxHp, int aAttack, int aArmor )
    {
        this( aDamage, aMaxHp, aAttack, aArmor, new Random() );
    }

    public Creature( Range< Integer > aDamage, int aMaxHp, int aAttack, int aArmor, Random aRandom )
    {
        damage = aDamage;
        maxHp = aMaxHp;
        attack = aAttack;
        armor = aArmor;
        rand = aRandom;
        currentHp = maxHp;
    }

    public void attack( Creature aDefender )
    {
        int randomDmg = (int)(rand.nextDouble() * (damage.upperEndpoint() - damage.lowerEndpoint()))
            + damage.lowerEndpoint();
        double dmgToDeal = dmgCalculator.calculateDamage( randomDmg, this, aDefender );
        aDefender.currentHp = aDefender.currentHp - (int)dmgToDeal;
    }

    // public class Builder{
    //
    // }
}
