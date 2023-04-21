package pl.psi;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import pl.psi.hero.Hero;
import pl.psi.mapElements.MapElement;
import pl.psi.player.Player;

import java.util.*;

public class Board {

    private final int MAX_WIDTH = 5;
    private final BiMap< Point, MapElement> map = HashBiMap.create();

    // Builder for testing purpose
    public static class Builder {

        private Map<Point, MapElement> mapElements = new HashMap<Point, MapElement>();

        public Builder mapElements(final Map<Point, MapElement> aMapElements) {
            mapElements = aMapElements;
            return this;
        }

        public Board build() {
            return new Board(mapElements);
        }
    }

    public Board(Map<Point, MapElement> aMapElements) {

        // Set elements like heroes, mountains, gold and so on, on the board.
        for (Point point : aMapElements.keySet()) {
            addMapElement(point, aMapElements.get(point));
        }
    }

    private void addMapElement(Point aPoint, MapElement aMapElement) {
        map.put(aPoint, aMapElement);
    }

    Optional<MapElement> getMapElement(final Point aPoint) {
        return Optional.ofNullable(map.get(aPoint));
    }

    void move(final Hero aHero, final Point aPoint )
    {
        if( canMove( aHero, aPoint ) )
        {
            if (map.get(aPoint)!=null) {
                map.get(aPoint).apply(aHero); // TODO another map for Hero?
            }

            map.inverse()
                    .remove( aHero );
            map.put( aPoint, aHero );
        }
    }

    boolean canMove( final Hero aHero, final Point aPoint )
    {
        if( map.containsKey( aPoint ) )
        {
            if (!map.get(aPoint).isInteractive()) {
                return false;
            }
        }
        final Point oldPosition = getPosition( aHero );
        return aPoint.distance( oldPosition.getX(), oldPosition.getY() ) < aHero.getHeroStatistics().getMoveRange();
    }

    Point getPosition( MapElement aMapElement )
    {
        return map.inverse()
                .get( aMapElement );
    }
}
