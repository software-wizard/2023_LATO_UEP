package pl.psi;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.jupiter.api.Test;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.EconomyHeroStatistics;
import pl.psi.mapElements.*;
import pl.psi.player.Player;
import pl.psi.player.PlayerResources;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    @Test
    void shouldMapElementOnPoint() {
        final MapElement staticElement = new StaticElement();
        BiMap<Point, MapElement> mapElements = HashBiMap.create();
        mapElements.put(new Point(1, 1), staticElement);
        final Board board = new Board(mapElements);
        assertThat(board.getMapElement(new Point(1, 1)).isPresent()).isTrue();
    }

    @Test
    void shouldHeroOnPoint() {
        final EconomyHero economyHero = EconomyHero.builder().build();
        BiMap<Point, MapElement> mapElements = HashBiMap.create();
        mapElements.put(new Point(1, 1), economyHero);
        final Board board = new Board(mapElements);
        assertThat(board.getHero(new Point(1, 1)).isPresent()).isTrue();
    }

    @Test
    void shouldHeroMovesProperly() {
        final EconomyHero economyHero = new EconomyHero(EconomyHeroStatistics.builder()
                .moveRange(3)
                .build());
        BiMap<Point, MapElement> mapElements = HashBiMap.create();
        mapElements.put(new Point(1, 1), economyHero);
        final Board board = new Board(mapElements);

        board.move(economyHero, new Point(2, 2));
        assertThat(board.getHero(new Point(2, 2)).isPresent()).isTrue();
        assertThat(board.getHero(new Point(1, 1)).isPresent()).isFalse();

        board.move(economyHero, new Point(5, 3));
        assertThat(board.getHero(new Point(5, 3)).isPresent()).isFalse(); // For move range = 3
    }

    @Test
    void shouldHeroGetResource() {
        // Check if Player gets resource if Hero stands on the point
        BiMap<Point, MapElement> mapElements = HashBiMap.create();
        final EconomyHero economyHero = new EconomyHero(EconomyHeroStatistics.builder()
                .player(Player.builder()
                        .resources(PlayerResources.builder()
                                .gold(0)
                                .build())
                        .build())
                .moveRange(3)
                .build());
        mapElements.put(new Point(1, 1), economyHero);
        final pl.psi.mapElements.Resource gold = new pl.psi.mapElements.Resource(ResourceType.GOLD, 1);
        mapElements.put(new Point(2, 2), gold);
        final Board board = new Board(mapElements);

        assertEquals(0, economyHero.getEconomyHeroStatistics().getPlayer().getResources().getGold());
        board.move(economyHero, new Point(2, 2));
        assertEquals(1, economyHero.getEconomyHeroStatistics().getPlayer().getResources().getGold());
        // Check is point empty
        assertThat(board.getMapElement(new Point(2, 2)).isPresent()).isFalse();
        assertThat(board.getHero(new Point(2, 2)).isPresent()).isTrue();
    }

    @Test
    void shouldBarierWorksProperly() {
        final EconomyHero economyHero = new EconomyHero(EconomyHeroStatistics.builder().moveRange(3).build());
        final StaticElement barier = new StaticElement();
        BiMap<Point, MapElement> mapElements = HashBiMap.create();
        mapElements.put(new Point(1, 1), economyHero);
        mapElements.put(new Point(2, 2), barier);
        final Board board = new Board(mapElements);
        board.move(economyHero, new Point(2, 2));
        assertEquals(new Point(1, 1), board.getHeroPosition(economyHero));
        assertEquals(new Point(2, 2), board.getPosition(barier));
    }

    @Test
    void shouldLearningStoneWorksProperly() {
        // Learning Stone: gives visiting heroes +1000 experience upon their first visit.
        final EconomyHero economyHero = new EconomyHero(EconomyHeroStatistics.builder().moveRange(3).experience(0).build());
        final EconomyHero economyHero2 = new EconomyHero(EconomyHeroStatistics.builder().moveRange(3).experience(50).build());
        final LearningStone ls = new LearningStone();
        BiMap<Point, MapElement> mapElements = HashBiMap.create();
        mapElements.put(new Point(1, 1), economyHero);
        mapElements.put(new Point(2, 2), ls);
        mapElements.put(new Point(1, 2), economyHero2);
        final Board board = new Board(mapElements);
        assertEquals(0, economyHero.getEconomyHeroStatistics().getExperience());
        board.move(economyHero, new Point(2,2));
        assertEquals(1000, economyHero.getEconomyHeroStatistics().getExperience());
        board.move(economyHero, new Point(2, 3));
        board.move(economyHero, new Point(2, 2));
        assertEquals(1000, economyHero.getEconomyHeroStatistics().getExperience());
        board.move(economyHero, new Point(2, 3));
        assertEquals(50, economyHero2.getEconomyHeroStatistics().getExperience());
        board.move(economyHero2, new Point(2, 2));
        assertEquals(1050, economyHero2.getEconomyHeroStatistics().getExperience());
    }

    @Test
    void shouldMagicWellWorksProperly() {
        // Magic Well: a hero can restore 100% of mana reserves here once per turn.
        final EconomyHero economyHero = new EconomyHero(EconomyHeroStatistics.builder()
                .moveRange(3)//set moveRange to 3
                .maxMana(10)
                .mana(0)
                .build());
        final MagicWell mw = new MagicWell();
        BiMap<Point, MapElement> mapElements = HashBiMap.create();
        mapElements.put(new Point(1, 1), economyHero);
        mapElements.put(new Point(2, 2), mw);
        final LinkedList<Player> players = new LinkedList<>();
        final EconomyEngine engine = new EconomyEngine(players, mapElements);
        assertEquals(0, economyHero.getEconomyHeroStatistics().getMana());
        engine.getBoard().move(economyHero, new Point(2, 2));
        assertEquals(10, economyHero.getEconomyHeroStatistics().getMana());

        // Check if in the same turn Hero cant get mana
        engine.getBoard().move(economyHero, new Point(1, 1));
        economyHero.getEconomyHeroStatistics().setMana(5);
        engine.getBoard().move(economyHero, new Point(2, 2));
        assertEquals(5, economyHero.getEconomyHeroStatistics().getMana());
        engine.getBoard().move(economyHero, new Point(1, 1));

        // Check if in the other turn Hero can get mana
        engine.getTurnQueue().nextTurn();
        engine.getBoard().move(economyHero, new Point(2, 2));
        assertEquals(10, economyHero.getEconomyHeroStatistics().getMana());
    }

}
