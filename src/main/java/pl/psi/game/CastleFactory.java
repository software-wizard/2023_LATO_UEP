// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.game;

import com.google.common.collect.Range;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class CastleFactory
{

    Creature create( int aTier, boolean aUpgraded, int aAmount )
    {
        switch( aTier )
        {
            case 1:
                new Creature( CreatureStatistics.builder()
                    .name( "Pikerman" )
                    .maxHp( 10 )
                    .attack( 4 )
                    .armor( 5 )
                    .damage( Range.closed( 1, 3 ) )
                    .moveRange( 4 )
                    .build() );
                break;
        }
        throw new IllegalArgumentException( "Cannot recognize params" );
    }
}
