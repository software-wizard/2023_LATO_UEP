package pl.psi;

import lombok.Getter;
import pl.psi.hero.EconomyHero;
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

    // TODO getter Player

    // Fasade - easy for GUI
    public boolean canMove(final Point aPoint, final EconomyHero aChoosenEconomyHero) {
        return board.canMove(aChoosenEconomyHero, aPoint);
    }

    public void move(final Point aPoint, final EconomyHero aChoosenEconomyHero) {
        board.move(aChoosenEconomyHero, aPoint);
    }

}
