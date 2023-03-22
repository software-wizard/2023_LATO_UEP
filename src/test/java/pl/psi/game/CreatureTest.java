// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.game;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
class CreatureTest
{

    @Test
    void creatureShouldAttackProperly()
    {
        Creature attacker = new Creature( CreatureStatistics.builder()
            .damage( Range.closed( 10, 10 ) )
            .maxHp( 1 )
            .build() );
        Creature defender = new Creature( CreatureStatistics.builder()
                .damage( Range.closed( 0, 0 ) )
                .maxHp( 100 )
                .build() );

        attacker.attack( defender );

        assertThat( defender.getCurrentHp() ).isEqualTo( 90 );
    }

//    @Test
//    void creatureShouldAttackProperlyWithRandDmg()
//    {
//        Creature attacker = new Creature( Range.closed( 1, 10 ), 1, new TestRandom() );
//        Creature defender = new Creature( Range.closed( 0, 0 ), 100 );
//
//        attacker.attack( defender );
//
//        assertThat( defender.getCurrentHp() ).isEqualTo( 95 );
//    }
//
//    @Test
//    void creatureShouldAttackProperlyWithAttackAndArmorStatistic()
//    {
//        Creature attacker = new Creature( Range.closed( 1, 10 ), 1, 30, 10, new TestRandom() );
//        Creature defender = new Creature( Range.closed( 0, 0 ), 100, 10, 10 );
//
//        attacker.attack( defender );
//
//        assertThat( defender.getCurrentHp() ).isEqualTo( 90 );
//    }
//
//    @Test
//    void creatureShouldAttackProperlyWithReduceArmor()
//    {
//        Creature attacker = new Creature( Range.closed( 1, 10 ), 1, 30, 10, new TestRandom() );
//        attacker.setDmgCalculator( new ReduceArmorDamageCalculator( 0.5 ) );
//        Creature defender = new Creature( Range.closed( 0, 0 ), 100, 10, 10 );
//
//        attacker.attack( defender );
//
//        assertThat( defender.getCurrentHp() ).isEqualTo( 88 );
//    }

    private class TestRandom extends Random
    {
        @Override
        public double nextDouble()
        {
            return 0.5;
        }
    }
}