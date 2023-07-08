//package pl.psi;
//
//import com.google.common.collect.BiMap;
//import com.google.common.collect.HashBiMap;
//import org.junit.jupiter.api.Test;
//import pl.psi.mapElements.MapElement;
//import pl.psi.player.Player;
//
//import java.util.LinkedList;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class TurnQueueTest {
//
//    @Test
//    void shouldPlayerSwitchProperly() {
//        LinkedList<Player> players = new LinkedList<Player>();
//        Player player1 = Player.builder().name("1").build();
//        Player player2 = Player.builder().name("2").build();
//        players.add(player1);
//        players.add(player2);
//
//        BiMap<Point, MapElement> mapElements = HashBiMap.create();
//
//        final EconomyEngine engine = new EconomyEngine(players, mapElements);
//        assertEquals(1, engine.getTurnQueue().getDay());
//
//        assertEquals(player1, engine.getTurnQueue().getCurrentPlayer());
//        engine.getTurnQueue().nextTurn();
//        assertEquals(player2, engine.getTurnQueue().getCurrentPlayer());
//        engine.getTurnQueue().nextTurn();
//        assertEquals(player1, engine.getTurnQueue().getCurrentPlayer());
//        engine.getTurnQueue().nextTurn();
//        assertEquals(player2, engine.getTurnQueue().getCurrentPlayer());
//        engine.getTurnQueue().nextTurn();
//        assertEquals(3, engine.getTurnQueue().getDay());
//
//    }
//
//    @Test
//    void shouldPlayerSwitchProperlyEqualsTest() {
//        LinkedList<Player> players = new LinkedList<Player>();
//        players.add(Player.builder().name("1").build());
//        players.add(Player.builder().name("2").build());
//
//        BiMap<Point, MapElement> mapElements = HashBiMap.create();
//
//        final EconomyEngine engine = new EconomyEngine(players, mapElements);
//        assertEquals(1, engine.getTurnQueue().getDay());
//
//        assertEquals(Player.builder().name("1").build(), engine.getTurnQueue().getCurrentPlayer());
//        engine.getTurnQueue().nextTurn();
//        assertEquals(Player.builder().name("2").build(), engine.getTurnQueue().getCurrentPlayer());
//        engine.getTurnQueue().nextTurn();
//        engine.getTurnQueue().nextTurn();
//        assertEquals(2, engine.getTurnQueue().getDay());
//
//    }
//
//}
