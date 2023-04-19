package pl.psi;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.jupiter.api.Test;
import pl.psi.hero.Hero;
import pl.psi.hero.HeroStatistics;
import pl.psi.mapElements.MapElement;
import pl.psi.mapElements.Resource;
import pl.psi.mapElements.StaticElement;
import pl.psi.player.Player;
import pl.psi.player.PlayerResources;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    // Verify Mock apply - Point

    @Test
    void shouldMapElementOnPoint() {
        final MapElement staticElement = new StaticElement();
        BiMap<Point, MapElement> mapElements = HashBiMap.create();
        mapElements.put(new Point(1, 1), staticElement);
        final Board board = new Board.Builder()
                .mapElements(mapElements)
                .build();
        assertThat(board.getMapElement(new Point(1, 1)).isPresent()).isTrue();
    }

    @Test
    void shouldHeroOnPoint() {
        final Hero hero = Hero.builder().build(); // TODO use builder, because if hero rise there will be a problem with test
        BiMap<Point, MapElement> mapElements = HashBiMap.create();
        mapElements.put(new Point(1, 1), hero);
        final Board board = new Board.Builder()
                .mapElements(mapElements)
                .build();
        assertThat(board.getMapElement(new Point(1, 1)).isPresent()).isTrue();
    }

    @Test
    void shouldHeroMovesProperly() {
        final Hero hero = new Hero(HeroStatistics.builder()
                .moveRange(3)
                .build());
        BiMap<Point, MapElement> mapElements = HashBiMap.create();
        mapElements.put(new Point(1, 1), hero);
        final Board board = new Board.Builder()
                .mapElements(mapElements)
                .build();
        board.move(hero, new Point(2, 2));
        assertThat(board.getMapElement(new Point(2, 2)).isPresent()).isTrue();

        assertThat(board.getMapElement(new Point(1, 1)).isPresent()).isFalse();

        board.move(hero, new Point(5, 3));
        assertThat(board.getMapElement(new Point(5, 3)).isPresent()).isFalse(); // For move range = 3
    }

    @Test
    void shouldHeroGetResource() {
        // Check if Player gets resource if Hero stands on the point
        BiMap<Point, MapElement> mapElements = HashBiMap.create();
        final Hero hero = new Hero(HeroStatistics.builder()
                .player(new Player("1", PlayerResources.builder().gold(0).build()))
                .moveRange(3)
                .build());
        mapElements.put(new Point(1, 1), hero);
        final Resource gold = new Resource(Resource.ResourceType.GOLD, 1);
        mapElements.put(new Point(2, 2), gold);
        final Resource wood = new Resource(Resource.ResourceType.WOOD, 1);
        mapElements.put(new Point(3, 2), wood);
        final Board board = new Board.Builder()
                .mapElements(mapElements)
                .build();
        assertEquals(0, hero.getHeroStatistics().getPlayer().getResources().getGold());
        board.move(hero, new Point(2, 2));
        assertEquals(1, hero.getHeroStatistics().getPlayer().getResources().getGold());
        board.move(hero, new Point(3, 2));
        assertEquals(1, hero.getHeroStatistics().getPlayer().getResources().getGold());
        board.move(hero, new Point(3, 3));
        assertEquals(1, hero.getHeroStatistics().getPlayer().getResources().getGold());
    }

    @Test
    void shouldBarierWorksProperly() {
        final Hero hero = new Hero(HeroStatistics.builder().moveRange(3).build());
        final StaticElement barier = new StaticElement();
        BiMap<Point, MapElement> mapElements = HashBiMap.create();
        mapElements.put(new Point(1, 1), hero);
        mapElements.put(new Point(2, 2), barier);
        final Board board = new Board.Builder().mapElements(mapElements).build();
        board.move(hero, new Point(2, 2));
        assertEquals(new Point(1, 1), board.getPosition(hero));
    }

//    @Test
//    void shouldPlayerSwitchProperly() {
//        // TODO
//        final Player player1 = new Player();
//        ArrayList<Hero> heroes1 = new ArrayList<Hero>();
//        heroes1.add(new Hero());
//        player1.setHeroes(heroes1);
//
//        final Player player2 = new Player();
//        ArrayList<Hero> heroes2 = new ArrayList<Hero>();
//        heroes2.add(new Hero());
//        player2.setHeroes(heroes2);
//
//    }

}
