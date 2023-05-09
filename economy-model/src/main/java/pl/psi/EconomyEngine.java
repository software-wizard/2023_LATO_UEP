package pl.psi;

import lombok.Getter;
import pl.psi.hero.Hero;
import pl.psi.mapElements.MapElement;
import pl.psi.player.Player;
import java.util.LinkedList;
import java.util.Map;

@Getter
public class EconomyEngine {

    private final Board board;
    private final TurnQueue turnQueue;

    public EconomyEngine(LinkedList<Player> aPlayers, Map<Point, MapElement> aMapElements) {
        this.board = new Board(aMapElements);
        this.turnQueue = new TurnQueue(aPlayers);
        turnQueue.addObserver(board);
    }

    // TODO ENDPOINT, engine dostarcza canMove, jak w battleEngine

    public boolean canMove(final Point aPoint, final Hero aChoosenHero) {
        return board.canMove(aChoosenHero, aPoint);
    }

    public void move(final Point aPoint, final Hero aChoosenHero) {
        board.move(aChoosenHero, aPoint);
    }

}
