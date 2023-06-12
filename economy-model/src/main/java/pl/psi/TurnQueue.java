package pl.psi;

import lombok.Getter;
import pl.psi.player.Player;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.Queue;

public class TurnQueue {

    public static final String END_OF_TURN = "END_OF_TURN";
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);
    @Getter
    private int day = 1;
    private final Queue<Player> playersQueue;
    private final LinkedList<Player> players;
    @Getter
    private Player currentPlayer;

    public TurnQueue(final LinkedList<Player> aPlayers) {
        players = aPlayers;
        playersQueue = new LinkedList<>();
        initQueue();
        nextTurn();
    }

    private void initQueue() {
        this.playersQueue.addAll(players);
    }

    public void nextTurn() {
        if (playersQueue.isEmpty()) {
            nextDay();
        }
        currentPlayer = playersQueue.poll();
    }

    private void nextDay() {
        day++;
        observerSupport.firePropertyChange(END_OF_TURN, day - 1, day);
        initQueue();
    }

    void addObserver(PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aObserver);
    }

}
