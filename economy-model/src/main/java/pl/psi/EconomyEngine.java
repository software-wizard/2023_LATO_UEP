package pl.psi;

import lombok.Getter;
import pl.psi.mapElements.MapElement;
import pl.psi.player.Player;
import java.util.LinkedList;
import java.util.Map;

@Getter
public class EconomyEngine {

    private final Board board;
    private final TurnQueue turnQueue;

    // TODO Launcher - choose type of castle
    public EconomyEngine(LinkedList<Player> aPlayers, Map<Point, MapElement> aMapElements) {
        // TODO engine dostarcza canMove - battleEngine
        this.board = new Board(aMapElements);
        this.turnQueue = new TurnQueue(aPlayers);
        turnQueue.addObserver(board);
    }
}
