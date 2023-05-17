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
//        final List< Creature > c1 = List.of( creature );
//        final List< Creature > c2 = List.of();
        final Board board = new Board( List.of(creature), List.of() );

        board.move( creature, new Point( 3, 3 ) );

        assertThat( board.getMapObject( new Point( 3, 3 ) )
            .isPresent() ).isTrue();
    }

}