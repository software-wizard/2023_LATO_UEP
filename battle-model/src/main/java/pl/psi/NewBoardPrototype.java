package pl.psi;

import WarMachines.WarMachine;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.List;
import java.util.Optional;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class NewBoardPrototype
{
//    private static final int MAX_WITDH = 14;
    private final BiMap<Point, WarMachine> mapWarMachines = HashBiMap.create();
    

//    private final BiMap<Point, Creature> mapCreatures = HashBiMap.create();

    public NewBoardPrototype(final List< WarMachine > aWarMachines1, final List< WarMachine > aWarMachines2 )
    {
        addWarMachines( aWarMachines1, 3 );
        addWarMachines( aWarMachines2, 11 );
    }

//    public NewBoardPrototype(final List< Creature > aCreatures1, final List< WarMachine > aWarMachines1,
//                              final List< Creature > aCreatures2, final List< WarMachine > aWarMachines2 )
//    {
//        addWarMachines( aWarMachines1, 3 );
//        addWarMachines( aWarMachines2, 11 );
//        addCreatures( aCreatures1, 4 );
//        addCreatures( aCreatures, 6 );
//    }

    private void addWarMachines(final List< WarMachine > aWarMachines, final int aXPosition )
    {
        for( int i = 0; i < aWarMachines.size(); i++ )
        {
            mapWarMachines.put( new Point( aXPosition, i * 2 + 4 ), aWarMachines.get( i ) );
        }
    }

//    private void addCreatures(final List< Creature > aCreatures, final int aXPosition )
//    {
//        for( int i = 0; i < aCreatures.size(); i++ )
//        {
//            mapCreatures.put( new Point( aXPosition, i * 2 + 4 ), aCreatures.get( i ) );
//        }
//    }

    Optional< WarMachine > getWarMachine( final Point aPoint )
    {
        return Optional.ofNullable( mapWarMachines.get( aPoint ) );
    }

//    Optional< Creature > getCreature( final Point aPoint )
//    {
//        return Optional.ofNullable( mapCreatures.get( aPoint ) );
//    }


//    void move( final Creature aCreature, final Point aPoint )
//    {
//        if( canMove( aCreature, aPoint ) )
//        {
//            map.inverse()
//                .remove( aCreature );
//            map.put( aPoint, aCreature );
//        }
//    }

//    boolean canMove( final Creature aCreature, final Point aPoint )
//    {
//        if( map.containsKey( aPoint ) )
//        {
//            return false;
//        }
//        final Point oldPosition = getPosition( aCreature );
//        return aPoint.distance( oldPosition.getX(), oldPosition.getY() ) < aCreature.getMoveRange();
//    }

    Point getPosition( WarMachine aWarMachine )
    {
        return mapWarMachines.inverse()
            .get( aWarMachine );
    }

//    Optional< Creature > getCreature( final Point aPoint )
//    {
//        return Optional.ofNullable( map.get( aPoint ) );
//    }
}
