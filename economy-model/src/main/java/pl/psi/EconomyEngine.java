package pl.psi;

import com.google.common.collect.BiMap;
import lombok.Getter;
import pl.psi.hero.EconomyHero;
import pl.psi.mapElements.MapElement;
import pl.psi.mapElements.Mine;
import pl.psi.player.Player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.Optional;

@Getter
public class EconomyEngine {

    private final Board board;
    private final TurnQueue turnQueue;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);

    public EconomyEngine(LinkedList<Player> aPlayers, BiMap<Point, MapElement> aMapElements) {

//        aMapElements.values().setFireMethod(this::fireEvent);

        this.board = new Board(aMapElements);
        this.turnQueue = new TurnQueue(aPlayers);
        turnQueue.addObserver(board);
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
        fireEvent("");
    }

    private void fireEvent(String aType) {
        observerSupport.firePropertyChange(new PropertyChangeEvent(this, aType, null, null));
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
