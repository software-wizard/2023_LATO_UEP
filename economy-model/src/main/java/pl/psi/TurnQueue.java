package pl.psi;

import lombok.Getter;
import pl.psi.player.Player;

import java.util.LinkedList;
import java.util.Queue;

public class TurnQueue {

    @Getter
    private int day = 1;
    private final Queue<Player> playersQueue;
    private LinkedList<Player> players;
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
        // TODO reset moveRange Hero.
        day++;
        initQueue();
    }


}
