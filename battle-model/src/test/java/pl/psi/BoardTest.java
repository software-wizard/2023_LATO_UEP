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
                        .moveRange( 5 )
                        .build() )
                .build();
        final List< Creature > c1 = List.of( creature );
        final List< Creature > c2 = List.of();
        final Board board = new Board( c1, c2 );

        board.move( creature, new Point( 3, 3) );

        assertThat( board.getCreature( new Point( 3, 3) )
                .isPresent() ).isTrue();
    }
    // asercja w metodzie canMove
    // sprawdzic punkty do ktorych nie mozemy dojsc
    @Test
    void CreatureShouldMoveInDistance()
    {
        final Creature creature = new Creature.Builder().statistic( CreatureStats.builder()
                        .moveRange( 5 )
                        .build() )
                .build();
        final List< Creature > c1 = List.of( creature );
        final List< Creature > c2 = List.of();
        final Board board = new Board( c1, c2 );
        board.move(c1.get(0),new Point(5,5));

        assertThat( board.canMove(creature, new Point( 3, 3)))
                .isTrue();
        assertThat( board.canMove(creature, new Point(creature.getMoveRange()+1, creature.getMoveRange()+1)))
                .isFalse();
    }
}