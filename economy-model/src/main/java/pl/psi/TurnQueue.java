package pl.psi;

import lombok.Getter;
import pl.psi.mapElements.MapElement;
import pl.psi.mapElements.Mine;
import pl.psi.player.Player;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TurnQueue {

    @Getter
    private int day = 1;
    private final Queue<Player> playersQueue;
    private LinkedList<Player> players;
    @Getter
    private Player currentPlayer;
    private final Map<Point, MapElement> mapElements;

    public TurnQueue(final LinkedList<Player> aPlayers, final Map<Point, MapElement> aMapElements) {
        mapElements = aMapElements;
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

        // Add for each Player resources if it has mines
        for (MapElement mapElement : mapElements.values()) {
            if (mapElement instanceof Mine) {
                ((Mine) mapElement).addResource();
            }
        }
    }


}
