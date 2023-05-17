package pl.psi;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.jupiter.api.Test;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.EconomyHeroStatistics;
import pl.psi.mapElements.MapElement;
import pl.psi.mapElements.Mine;
import pl.psi.player.Player;
import pl.psi.player.PlayerResources;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourcesTest {

    @Test
    void shouldPlayerHasGold() {
        final Player player = Player.builder().resources(PlayerResources.builder().gold(5).build()).build();
        assertEquals(5, player.getResources().getGold());
    }

    @Test
    void shouldHeroGetMine() {
        final Player player = Player.builder()
                .resources(PlayerResources.builder()
                        .gold(0)
                        .wood(0)
                        .build())
                .build();
        final EconomyHero economyHero = EconomyHero.builder()
                .economyHeroStatistics(EconomyHeroStatistics.builder()
                        .moveRange(3)
                        .player(player)
                        .build()).
                build();
        final Mine goldMine = new Mine(ResourceType.GOLD);
        final Mine woodMine = new Mine(ResourceType.WOOD);
        BiMap<Point, MapElement> mapElements = HashBiMap.create();
        mapElements.put(new Point(1, 1), economyHero);
        mapElements.put(new Point(2, 2), goldMine);
        mapElements.put(new Point(3, 3), woodMine);
        LinkedList<Player> players = new LinkedList<Player>();
        players.add(player);
        final EconomyEngine engine = new EconomyEngine(players, mapElements);
        engine.getBoard().move(economyHero, new Point(2, 2));
        assertEquals(0, economyHero.getEconomyHeroStatistics().getPlayer().getResources().getGold());
        engine.getTurnQueue().nextTurn();
        assertEquals(1000, economyHero.getEconomyHeroStatistics().getPlayer().getResources().getGold());
        engine.getBoard().move(economyHero, new Point(3, 3));
        assertEquals(0, economyHero.getEconomyHeroStatistics().getPlayer().getResources().getWood());
        engine.getTurnQueue().nextTurn();
        assertEquals(2, economyHero.getEconomyHeroStatistics().getPlayer().getResources().getWood());
        engine.getTurnQueue().nextTurn();
        assertEquals(4, economyHero.getEconomyHeroStatistics().getPlayer().getResources().getWood());

    }

}
