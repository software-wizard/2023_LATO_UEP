package pl.psi;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class Board
{
    private static final int MAX_WITDH = 14;
    private final BiMap< Point, MapObjectIf> map = HashBiMap.create();

    public Board( final List< MapObjectIf > mapObjectIfs1, final List< MapObjectIf > mapObjectIfs2 )
    {
        addMapObject( mapObjectIfs1, 3 );
        addMapObject( mapObjectIfs2, 11 );
    }

    private void addMapObject(final List< MapObjectIf > mapObjectIfs, final int aXPosition )
    {
        for( int i = 0; i < mapObjectIfs.size(); i++ )
        {
            map.put( new Point( aXPosition, i * 2 + 1 ), mapObjectIfs.get( i ) );
        }
    }

    public void removeMapObject(MapObjectIf mapObjectIf ){
        map.remove(getPosition(mapObjectIf));
    }

    Optional< MapObjectIf > getMapObject(final Point aPoint )
    {
        return Optional.ofNullable( map.get( aPoint ) );
    }

    void move( final MapObjectIf mapObjectIf, final Point aPoint )
    {
        if( canMove( mapObjectIf, aPoint ) )
        {
            map.inverse()
                .remove( mapObjectIf );
            map.put( aPoint, mapObjectIf );
        }
    }

    boolean canMove( final MapObjectIf mapObjectIf, final Point aPoint )
    {
        if( map.containsKey( aPoint ) )
        {
            return false;
        }
        final Point oldPosition = getPosition( mapObjectIf );
        return aPoint.distance( oldPosition.getX(), oldPosition.getY() ) < mapObjectIf.getMoveRange();
    }

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
