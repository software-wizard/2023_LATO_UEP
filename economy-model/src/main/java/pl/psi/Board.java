package pl.psi;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import pl.psi.hero.Hero;
import pl.psi.mapElements.MagicWell;
import pl.psi.mapElements.MapElement;
import pl.psi.mapElements.Mine;
import pl.psi.player.Player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

public class Board implements PropertyChangeListener {
    private final BiMap<Point, MapElement> map = HashBiMap.create();
    private final BiMap<Point, Hero> mapHero = HashBiMap.create();
    private final Map<Point, MapElement> mapElements;

    public Board(Map<Point, MapElement> aMapElements) {
        mapElements = aMapElements;

        // Set elements like heroes, mountains, gold and so on, on the board.
        for (Point point : aMapElements.keySet()) {
            addMapElement(point, aMapElements.get(point));
        }
    }

    private void addMapElement(Point aPoint, MapElement aMapElement) {
        if (aMapElement instanceof Hero) {
            mapHero.put(aPoint, (Hero) aMapElement);
        } else {
            map.put(aPoint, aMapElement);
        }
    }

    Optional<MapElement> getMapElement(final Point aPoint) {
        return Optional.ofNullable(map.get(aPoint));
    }

    Optional<Hero> getHero(final Point aPoint) {
        return Optional.ofNullable(mapHero.get(aPoint));
    }

    void move(final Hero aHero, final Point aPoint )
    {
        if( canMove( aHero, aPoint ) )
        {
            if (map.get(aPoint)!=null) {
                map.get(aPoint).apply(aHero);

                if (map.get(aPoint).shouldBeRemoveAfterAction()) {
                    map.inverse().remove(map.get(aPoint));
                }
            }

            mapHero.inverse()
                    .remove( aHero );
            mapHero.put( aPoint, aHero );
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
        final Point oldPosition = getHeroPosition( aHero );
        return aPoint.distance( oldPosition.getX(), oldPosition.getY() ) < aHero.getHeroStatistics().getMoveRange();
    }

    Point getPosition( MapElement aMapElement )
    {
        return map.inverse()
                .get( aMapElement );
    }

    Point getHeroPosition( Hero aHero )
    {
        return mapHero.inverse()
                .get( aHero );
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(TurnQueue.END_OF_TURN)) {
            for (MapElement mapElement : mapElements.values()) {
                mapElement.endOfTurn();
            }
        }
    }
}