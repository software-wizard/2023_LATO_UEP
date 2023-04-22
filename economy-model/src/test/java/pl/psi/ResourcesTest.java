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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourcesTest {

    @Test
    void shouldPlayerHasGold() {
        final Player player = Player.builder().resources(PlayerResources.builder().gold(5).build()).build();
        assertEquals(5, player.getResources().getGold());
    }

    @Test
    void shouldHeroGetMine() {
        final Hero hero = Hero.builder()
                .heroStatistics(HeroStatistics.builder()
                        .moveRange(3)
                        .player(Player.builder().build())
                        .build()).
                build();
        final Mine goldMine = new Mine(Mine.ResourceType.GOLD);
        BiMap<Point, MapElement> mapElements = HashBiMap.create();
        mapElements.put(new Point(1, 1), hero);
        mapElements.put(new Point(2, 2), goldMine);
        final Board board = new Board(mapElements);

        board.move(hero, new Point(2, 2));
        assertEquals(1, hero.getHeroStatistics().getPlayer().getMines().getGoldMines().size());

    }

}
