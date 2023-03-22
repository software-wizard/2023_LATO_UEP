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
    private final CreatureStatistics stats;
    private final Random rand;
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
        currentHp = getMaxHp();
    }

    private int getMaxHp()
    {
        return stats.getMaxHp();
    }

    public void attack( Creature aDefender )
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

    // public class Builder{
    //
    // }
}
