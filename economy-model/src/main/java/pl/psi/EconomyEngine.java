package pl.psi;

import lombok.Builder;
import lombok.Getter;
import pl.psi.mapElements.MapElement;
import pl.psi.player.Player;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Getter
public class EconomyEngine {

    private final Board board;
    private final LinkedList<Player> players;
    private final TurnQueue turnQueue;

    // TODO Launcher - choose type of castle
    public EconomyEngine(LinkedList<Player> aPlayers, Map<Point, MapElement> aMapElements) {
        this.board = new Board(aMapElements);
        this.players = aPlayers;
        this.turnQueue = new TurnQueue(aPlayers);
    }

//    public static class Builder {
//
//        private Map<Point, MapElement> mapElements = new HashMap<Point, MapElement>();
//
//        public Builder mapElements(final Map<Point, MapElement> aMapElements) {
//            mapElements = aMapElements;
//            return this;
//        }
//
//        public Board build() {
//            return new Board(mapElements);
//        }
//    }

}
