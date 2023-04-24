package pl.psi;

import lombok.Getter;
import pl.psi.mapElements.MapElement;
import pl.psi.player.Player;
import java.util.LinkedList;
import java.util.Map;

@Getter
public class EconomyEngine {
    // TODO Czy board może być observerem, czy nie?

    private final Board board;
    private final LinkedList<Player> players;
    private final TurnQueue turnQueue;

    // TODO Launcher - choose type of castle
    public EconomyEngine(LinkedList<Player> aPlayers, Map<Point, MapElement> aMapElements) {
        this.board = new Board(aMapElements);
        this.players = aPlayers;
        this.turnQueue = new TurnQueue(aPlayers);
        turnQueue.addObserver(board);
    }
}
