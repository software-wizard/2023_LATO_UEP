package pl.psi;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.Getter;
import pl.psi.hero.EconomyHero;
import pl.psi.mapElements.MapElement;
import pl.psi.player.Player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

public class Board implements PropertyChangeListener {

    @Getter
    private int MapSize = 25;
    private LinkedList<Player> Players;
    private final BiMap<Point, MapElement> map = HashBiMap.create();
    @Getter
    private final BiMap<Point, EconomyHero> mapHero = HashBiMap.create();
    @Getter
    private final BiMap<Point, MapElement> mapElements;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);

    public Board(BiMap<Point, MapElement> aMapElements, LinkedList<Player> aPlayers) {
        mapElements = aMapElements;
        this.Players = aPlayers;

        // Set elements like heroes, mountains, gold and so on, on the board.
        for (Point point : aMapElements.keySet()) {
            addMapElement(point, aMapElements.get(point));
        }
    }

    public void addObserver(PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aObserver);
    }

    public Player getPlayer(EconomyHero aEconomyHero) {
        for (Player player : this.Players) {
            if (player.getEconomyHero().equals(aEconomyHero)) {
                return player;
            }
        }
        return null;
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

    void move(final EconomyHero aEconomyHero, final Point aPoint) {
        if (canMove(aEconomyHero, aPoint)) {

            aEconomyHero.getHeroStatistics().setMoveRange(aEconomyHero.getHeroStatistics().getMoveRange()
                    - (int) Math.ceil(aPoint.distance(getHeroPosition(aEconomyHero).getX(), getHeroPosition(aEconomyHero).getY())));

            if (map.get(aPoint) != null) {

                map.get(aPoint).apply(aEconomyHero, getPlayer(aEconomyHero));

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (mapHero.containsKey(new Point(aPoint.getX() + i, aPoint.getY() + j))) {
                            if (aEconomyHero != mapHero.get(new Point(aPoint.getX() + i, aPoint.getY() + j))) {
                                System.out.println("O Bogowie, walka! " + i + " " + j);
                                try {
                                    //TODO: jak zaimportowac klase EcoBattleConverter z innego modulu?
//                                EcoBattleConverter converter = new EcoBattleConverter();
//                                converter.startBattle(aEconomyHero, mapHero.get(new Point(aPoint.getX() + i, aPoint.getY() + j)));

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }

                if (map.get(aPoint).shouldBeRemoveAfterAction()) {
                    map.inverse().remove(map.get(aPoint));
                    mapElements.inverse().remove(mapElements.get(aPoint));
                }
            }

            mapHero.inverse()
                    .remove(aEconomyHero);
            mapHero.put(aPoint, aEconomyHero);
            observerSupport.firePropertyChange("HERO_MOVED", null, null);
        }
    }

    boolean canMove(final EconomyHero aEconomyHero, final Point aPoint) {
        if (map.containsKey(aPoint)) {
            if (!map.get(aPoint).isInteractive()) {
                return false;
            }
        }

        if (mapHero.containsKey(aPoint)) {
            if (!mapHero.get(aPoint).isInteractive()) {
                return false;
            }
        }
        final Point oldPosition = getHeroPosition(aEconomyHero);
        return aPoint.distance(oldPosition.getX(), oldPosition.getY()) <= aEconomyHero.getHeroStatistics().getMoveRange();
    }

    Point getPosition(MapElement aMapElement) {
        return map.inverse()
                .get(aMapElement);
    }

    public Point getHeroPosition(EconomyHero aEconomyHero) {
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