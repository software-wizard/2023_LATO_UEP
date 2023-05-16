package pl.psi;

import WarMachines.WarMachine;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import pl.psi.creatures.Creature;

import java.util.List;
import java.util.Optional;

/*
Prototype class for board - currently only for war machines
 */

public class NewBoardPrototype
{
//    private static final int MAX_WITDH = 14;
    private final BiMap< Point, WarMachine> map = HashBiMap.create();

    public NewBoardPrototype(final List< WarMachine > aWarMachines1, final List< WarMachine > aWarMachines2 )
    {
        addWarMachines( aWarMachines1, 3 );
        addWarMachines( aWarMachines2, 11 );
    }

    private void addWarMachines(final List< WarMachine > aWarMachines, final int aXPosition )
    {
        for( int i = 0; i < aWarMachines.size(); i++ )
        {
            map.put( new Point( aXPosition, i * 2 + 4 ), aWarMachines.get( i ) );
        }
    }

    public void removeWarMachine(WarMachine warMachine ){
        map.remove(getPosition(warMachine));
    }

    Optional< WarMachine > getWarMachine( final Point aPoint )
    {
        return Optional.ofNullable( map.get( aPoint ) );
    }

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
        if ((map.inverse().get( aWarMachine )) != null){
            return map.inverse()
                    .get( aWarMachine );
        } else{
            return new Point(0,0);
        }
    }
}