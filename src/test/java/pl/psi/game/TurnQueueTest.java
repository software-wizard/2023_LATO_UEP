// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.game;

import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
class TurnQueueTest
{
    @Test
    void turnQueueShouldWorkProperly()
    {
        Creature c1 = mock( Creature.class );
        when( c1.getMoveRange() ).thenReturn( 10 );
        Creature c2 = new Creature( CreatureStatistics.builder()
            .name( "c2" )
            .moveRange( 1 )
            .build() );
        Creature c3 = new Creature( CreatureStatistics.builder()
            .name( "c3" )
            .moveRange( 5 )
            .build() );

        TurnQueue queue = new TurnQueue( List.of( c1, c2, c3 ) );

        assertThat( queue.getActiveCreature() ).isEqualTo( c1 );

        queue.nextCreature();
        assertThat( queue.getActiveCreature() ).isEqualTo( c3 );

        queue.nextCreature();
        assertThat( queue.getActiveCreature() ).isEqualTo( c2 );
        verify( c1, times( 1 ) ).propertyChange( mock( PropertyChangeEvent.class ) );

        queue.nextCreature();
        assertThat( queue.getActiveCreature() ).isEqualTo( c1 );

    }
}