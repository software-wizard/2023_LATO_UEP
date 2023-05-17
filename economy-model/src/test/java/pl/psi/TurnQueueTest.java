package pl.psi;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.jupiter.api.Test;
import pl.psi.mapElements.MapElement;
import pl.psi.player.Player;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TurnQueueTest {

    @Test
    void shouldPlayerSwitchProperly() {
        LinkedList<Player> players = new LinkedList<Player>();
        Player player1 = Player.builder().build();
        Player player2 = Player.builder().build();
        players.add(player1);
        players.add(player2);

        BiMap<Point, MapElement> mapElements = HashBiMap.create();

        final EconomyEngine engine = new EconomyEngine(players, mapElements);
        assertEquals(1, engine.getTurnQueue().getDay());

        assertEquals(player1, engine.getTurnQueue().getCurrentPlayer());
        engine.getTurnQueue().nextTurn();
        assertEquals(player2, engine.getTurnQueue().getCurrentPlayer());
        engine.getTurnQueue().nextTurn();
        assertEquals(player1, engine.getTurnQueue().getCurrentPlayer());
        engine.getTurnQueue().nextTurn();
        assertEquals(player2, engine.getTurnQueue().getCurrentPlayer());
        engine.getTurnQueue().nextTurn();
        assertEquals(3, engine.getTurnQueue().getDay());

    }

}
