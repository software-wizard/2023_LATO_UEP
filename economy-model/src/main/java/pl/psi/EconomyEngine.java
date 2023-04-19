package pl.psi;

import pl.psi.mapElements.MapElement;
import pl.psi.player.Player;

import java.util.ArrayList;
import java.util.Map;

public class EconomyEngine {

    private final Board board;

    // TODO Launcher - choose type of castle
    public EconomyEngine(ArrayList<Player> aPlayers, Map<Point, MapElement> aMapElements) {
        board = new Board(aPlayers, aMapElements);
    }

}
