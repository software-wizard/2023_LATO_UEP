package pl.psi;

import java.util.ArrayList;
import java.util.Map;

public class EconomyEngine {

    private final Board board;

    // TODO Launcher - choose type of castle
    public EconomyEngine(ArrayList<Player> aPlayers, Map<Point, MapElement> aMapElements) {
        board = new Board(aPlayers, aMapElements);
    }

}
