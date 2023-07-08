// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.battle;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import pl.psi.battle.creatures.Creature;
import pl.psi.eco.creatures.CreatureStats;

class BoardTest
{
    @Test
    void unitsMoveProperly()
    {
        final Creature creature = new Creature.Builder().statistic( CreatureStats.builder()
                        .moveRange( 7 )
                        .build() )
                .build();
        final List< Creature > c1 = List.of( creature );
        final List< Creature > c2 = List.of();
        final BattleBoard board = new BattleBoard( c1, c2 );

        board.move( creature, new Point(5, 3) );

        assertThat( board.getCreature( new Point( 5, 3) )
                .isPresent() ).isTrue();
    }
    @Test
    @Disabled
    void shouldChangeListOfPointsInToGrid()
    {
        final Creature creature = new Creature.Builder().statistic( CreatureStats.builder()
                        .moveRange( 4 )
                        .build() )
                .build();
        final List< Creature > c1 = List.of( creature );
        final List< Creature > c2 = List.of();
        final BattleBoard board = new BattleBoard( c1, c2 );
        List<Point> listOfPoints = board.availablePointsToGo(c1.get(0));
        int[][] grid = board.gridConstruction(listOfPoints);
        assertThat(grid).isNotEmpty();
        assertThat(grid).hasDimensions(6,5);
    }
}