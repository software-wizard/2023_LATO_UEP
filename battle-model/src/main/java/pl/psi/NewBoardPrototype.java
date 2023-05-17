package pl.psi;

import WarMachines.MapObjectIf;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.List;
import java.util.Optional;

/*
Prototype class for board - currently only for war machines
 */

public class NewBoardPrototype
{
//    private static final int MAX_WITDH = 14;
    private final BiMap< Point, MapObjectIf> map = HashBiMap.create();

    public NewBoardPrototype(final List< MapObjectIf > mapObjectIfs, final List< MapObjectIf > mapObjectIfs1 )
    {
        addMapObject( mapObjectIfs, 3 );
        addMapObject( mapObjectIfs1, 11 );
    }

    private void addMapObject(final List< MapObjectIf > mapObjectIfs, final int aXPosition )
    {
        for( int i = 0; i < mapObjectIfs.size(); i++ )
        {
            map.put( new Point( aXPosition, i * 2 + 4 ), mapObjectIfs.get( i ) );
        }
    }

    public void removeMapObject(MapObjectIf mapObjectIf ){
        map.remove(getPosition(mapObjectIf));
    }

    Optional< MapObjectIf > getMapObject(final Point aPoint )
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

    Point getPosition( MapObjectIf mapObjectIf )
    {
        if ((map.inverse().get( mapObjectIf )) != null){
            return map.inverse()
                    .get( mapObjectIf );
        } else{
            return new Point(0,0);
        }
    }
}
