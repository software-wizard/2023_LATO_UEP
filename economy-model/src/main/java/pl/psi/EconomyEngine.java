package pl.psi;

import lombok.Getter;
import pl.psi.hero.EconomyHero;
import pl.psi.mapElements.MapElement;
import pl.psi.player.Player;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.Map;

@Getter
public class EconomyEngine {

    private final Board board;
    private final TurnQueue turnQueue;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);

    public EconomyEngine(LinkedList<Player> aPlayers, Map<Point, MapElement> aMapElements) {
        this.board = new Board(aMapElements);
        this.turnQueue = new TurnQueue(aPlayers);
        turnQueue.addObserver(board);
    }

    // Fasade - easy for GUI
    public boolean canMove(final Point aPoint, final EconomyHero aChoosenEconomyHero) {
        return board.canMove(aChoosenEconomyHero, aPoint);
    }

    public void move(final Point aPoint, final EconomyHero aChoosenEconomyHero) {
        board.move(aChoosenEconomyHero, aPoint);
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

}
