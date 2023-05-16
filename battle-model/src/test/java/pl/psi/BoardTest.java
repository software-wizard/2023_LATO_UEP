package pl.psi;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

class BoardTest
{
    @Test
    void unitsMoveProperly()
    {
        final Creature creature = new Creature.Builder().statistic( CreatureStats.builder()
                        .moveRange( 3 )
                        .build() )
                .build();
        final List< Creature > c1 = List.of( creature );
        final List< Creature > c2 = List.of();
        final Board board = new Board( c1, c2 );

        board.move( creature, new Point(3, 3) );

        assertThat( board.getCreature( new Point( 3, 3) )
                .isPresent() ).isTrue();
    }

    @Test
    void availablePointsToGoShouldFindAllAvailablePoints()
    {
        final Creature creature = new Creature.Builder().statistic( CreatureStats.builder()
                        .moveRange( 4 )
                        .build() )
                .build();
        final List< Creature > c1 = List.of( creature );
        final List< Creature > c2 = List.of();
        final Board board = new Board( c1, c2 );
        board.availablePointsToGo(c1.get(0));
        assertThat(board.availablePointsToGo(c1.get(0))).isNotEmpty();
        assertThat(board.availablePointsToGo(c1.get(0))).hasSize(18);
    }
    @Test
    void shouldChangeListOfPointsInToGrid()
    {
        final Creature creature = new Creature.Builder().statistic( CreatureStats.builder()
                        .moveRange( 4 )
                        .build() )
                .build();
        final List< Creature > c1 = List.of( creature );
        final List< Creature > c2 = List.of();
        final Board board = new Board( c1, c2 );
        List<Point> listOfPoints = board.availablePointsToGo(c1.get(0));
        int[][] grid = board.gridConstruction(listOfPoints);
        assertThat(grid).isNotEmpty();
        assertThat(grid).hasDimensions(5,4);

    }
}