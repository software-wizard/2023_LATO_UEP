package pl.psi;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.jupiter.api.Test;
import pl.psi.hero.Hero;
import pl.psi.hero.HeroStatistics;
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
        final Hero hero = Hero.builder()
                .heroStatistics(HeroStatistics.builder()
                        .moveRange(3)
                        .player(player)
                        .build()).
                build();
        final Mine goldMine = new Mine(Resource.ResourceType.GOLD);
        final Mine woodMine = new Mine(Resource.ResourceType.WOOD);
        BiMap<Point, MapElement> mapElements = HashBiMap.create();
        mapElements.put(new Point(1, 1), hero);
        mapElements.put(new Point(2, 2), goldMine);
        mapElements.put(new Point(3, 3), woodMine);
        LinkedList<Player> players = new LinkedList<Player>();
        players.add(player);
        final EconomyEngine engine = new EconomyEngine(players, mapElements);
        engine.getBoard().move(hero, new Point(2, 2));
        assertEquals(0, hero.getHeroStatistics().getPlayer().getResources().getGold());
        engine.getTurnQueue().nextTurn();
        assertEquals(1000, hero.getHeroStatistics().getPlayer().getResources().getGold());
        engine.getBoard().move(hero, new Point(3, 3));
        assertEquals(0, hero.getHeroStatistics().getPlayer().getResources().getWood());
        engine.getTurnQueue().nextTurn();
        assertEquals(2, hero.getHeroStatistics().getPlayer().getResources().getWood());
        engine.getTurnQueue().nextTurn();
        assertEquals(4, hero.getHeroStatistics().getPlayer().getResources().getWood());

    }

}
