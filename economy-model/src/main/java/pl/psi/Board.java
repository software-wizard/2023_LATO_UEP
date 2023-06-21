package pl.psi;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.Getter;
import pl.psi.hero.EconomyHero;
import pl.psi.mapElements.MapElement;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

public class Board implements PropertyChangeListener {

    @Getter
    private int MapSize = 25;
    private final BiMap<Point, MapElement> map = HashBiMap.create();
    @Getter
    private final BiMap<Point, EconomyHero> mapHero = HashBiMap.create();
    @Getter
    private final BiMap<Point, MapElement> mapElements;

    public Board(BiMap<Point, MapElement> aMapElements) {
        mapElements = aMapElements;

        // Set elements like heroes, mountains, gold and so on, on the board.
        for (Point point : aMapElements.keySet()) {
            addMapElement(point, aMapElements.get(point));
        }
    }

    private void addMapElement(Point aPoint, MapElement aMapElement) {
        if (aMapElement instanceof EconomyHero) {
            mapHero.put(aPoint, (EconomyHero) aMapElement);
        } else {
            map.put(aPoint, aMapElement);
        }
    }

    Optional<MapElement> getMapElement(final Point aPoint) {
        return Optional.ofNullable(map.get(aPoint));
    }

    Optional<EconomyHero> getHero(final Point aPoint) {
        return Optional.ofNullable(mapHero.get(aPoint));
    }

    void move(final EconomyHero aEconomyHero, final Point aPoint )
    {
        if( canMove(aEconomyHero, aPoint ) )
        {
            if (map.get(aPoint)!=null) {
                map.get(aPoint).apply(aEconomyHero);

                if (map.get(aPoint).shouldBeRemoveAfterAction()) {
                    map.inverse().remove(map.get(aPoint));
                }
            }

            mapHero.inverse()
                    .remove(aEconomyHero);
            mapHero.put( aPoint, aEconomyHero);
        }
    }

    boolean canMove(final EconomyHero aEconomyHero, final Point aPoint )
    {
        if( map.containsKey( aPoint ) )
        {
            if (!map.get(aPoint).isInteractive()) {
                return false;
            }
        }
        final Point oldPosition = getHeroPosition(aEconomyHero);
        return aPoint.distance( oldPosition.getX(), oldPosition.getY() ) <= aEconomyHero.getHeroStatistics().getMoveRange();
    }

    Point getPosition( MapElement aMapElement )
    {
        return map.inverse()
                .get( aMapElement );
    }

    public Point getHeroPosition( EconomyHero aEconomyHero)
    {
        return mapHero.inverse()
                .get(aEconomyHero);
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