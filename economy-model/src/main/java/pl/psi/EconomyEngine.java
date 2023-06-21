package pl.psi;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.Getter;
import pl.psi.hero.EconomyHero;
import pl.psi.mapElements.*;
import pl.psi.player.Player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

@Getter
public class EconomyEngine {

    private final Board board;
    private final TurnQueue turnQueue;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);

    public EconomyEngine(LinkedList<Player> aPlayers) {

        this.board = new Board(getMapElements(aPlayers));
        this.turnQueue = new TurnQueue(aPlayers);
        turnQueue.addObserver(board);

    }

    private BiMap<Point, MapElement> getMapElements(LinkedList<Player> aPlayers) {
        BiMap<Point, MapElement> aMapElements = HashBiMap.create();
        aMapElements.put(new Point(0, 0), aPlayers.get(0).getEconomyHero());
        aMapElements.put(new Point(24, 24), aPlayers.get(1).getEconomyHero());

        Castle castle1 = new Castle(Castle.FractionType.NECROPOLIS);
        aMapElements.put(new Point(2, 2), castle1);

        Random rand = new Random();
        List<String> elements = Arrays.asList("LearningStone", "MagicWell",
                "Mine", "Resource", "StaticElement", "Resource");

        for (int i = 0; i < 35; i++) {

            Point currentPoint = null;
            boolean isEmpty = false;
            while (!isEmpty) {
                int x = rand.nextInt(25);
                int y = rand.nextInt(25);
                if (aMapElements.get(new Point(x, y)) == null) {
                    isEmpty = true;
                }
                currentPoint = new Point(x, y);
            }

            String randomElement = elements.get(rand.nextInt(elements.size()));
            System.out.println(randomElement);
            System.out.println(currentPoint);
            switch (randomElement) {
                case "LearningStone":
                    aMapElements.put(currentPoint, new LearningStone());
                    break;
                case "MagicWell":
                    aMapElements.put(currentPoint, new MagicWell());
                    break;
                case "Mine":
                    ResourceType[] values = ResourceType.values();
                    int length = values.length;
                    int randIndex = new Random().nextInt(length);

                    aMapElements.put(currentPoint, new Mine(values[randIndex]));
                    break;
                case "Resource":
                    aMapElements.put(currentPoint, new Resource(ResourceType.GOLD, 1000));
                    break;
                case "StaticElement":
                    aMapElements.put(currentPoint, new StaticElement());
                    break;
            }
        }

        return aMapElements;
    }
    public EconomyHero getEcoHero(){
        return turnQueue.getCurrentPlayer().getEconomyHero();
    }
    public MapElement getMapElement(Point aPoint) {
        return board.getMapElements().get(aPoint);
    }

    public EconomyHero getEconomyHero(Point aPoint) {
        return board.getMapHero().get(aPoint);
    }
    public boolean canMove(final Point aPoint, final EconomyHero aChoosenEconomyHero) {
        return board.canMove(aChoosenEconomyHero, aPoint);
    }

    public void move(final Point aPoint, final EconomyHero aChoosenEconomyHero) {
        board.move(aChoosenEconomyHero, aPoint);
        observerSupport.firePropertyChange(new PropertyChangeEvent(this, "", null, null));
    }

    public void addObserver(final PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aObserver);
        turnQueue.addObserver(aObserver);
    }
    
    public Player getCurrentPlayer() {
        return turnQueue.getCurrentPlayer();
    }

    public void endTurn() {
        turnQueue.nextTurn();
    }

    public int getCurrentDay() {
        return turnQueue.getDay();
    }

    public int getMapSize() {
        return this.board.getMapSize();
    }

}
