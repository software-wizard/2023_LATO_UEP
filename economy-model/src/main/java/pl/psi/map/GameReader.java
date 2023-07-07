package pl.psi.map;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import pl.psi.Point;
import pl.psi.ResourceType;
import pl.psi.creatures.EconomyCreature;
import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.HeroStatistics;
import pl.psi.mapElements.*;
import pl.psi.player.Player;
import pl.psi.player.PlayerResources;

import java.awt.*;
import java.util.*;
import java.util.List;

public class GameReader {

    private LinkedList<Player> Players;

    public GameReader(LinkedList<Player> aPlayers) {
        this.Players = aPlayers;
    }

    public LinkedList<Player> buildPlayers() {

        try {
            EconomyCreature c1 = new EconomyNecropolisFactory().create(false, 1, 10);

            for (int i = 0; i < Players.size(); i++) {

                PlayerResources playerResources = PlayerResources.builder()
                        .wood(0)
                        .ore(0)
                        .gold(5000)
                        .crystal(0)
                        .gems(0)
                        .build();

                EconomyHero economyHero = EconomyHero.builder()
                        .heroStatistics(HeroStatistics.builder()
                                .moveRange(7)
                                .experience(1)
                                .level(1)
                                .mana(1)
                                .maxMana(1)
                                .build())
                        .fraction(Castle.FractionType.NECROPOLIS)
                        .heroArmy(new ArrayList<>())
                        .build();

                economyHero.getHeroArmy().add(c1);

                Player aPlayer = Player.builder()
                        .name(Players.get(i).getName())
                        .resources(playerResources)
                        .economyHero(economyHero)
                        .town(Players.get(i).getTown())
                        .heroName(Players.get(i).getHeroName())
                        .bonus(Players.get(i).getBonus())
                        .color(Color.yellow)
                        .build();

                Players.set(i, aPlayer);

            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return Players;
    }


    public BiMap<pl.psi.Point, MapElement> createMapElements() {
        BiMap<pl.psi.Point, MapElement> aMapElements = HashBiMap.create();

        for (int i = 0; i < Players.size(); i++) {
            Random rand = new Random();
            aMapElements.put(new pl.psi.Point(rand.nextInt(24), rand.nextInt(24)), Players.get(i).getEconomyHero());
        }

        Castle castle1 = new Castle(Castle.FractionType.NECROPOLIS);
        aMapElements.put(new pl.psi.Point(2, 2), castle1);

        Random rand = new Random();
        List<String> elements = Arrays.asList("LearningStone", "MagicWell",
                "Mine", "Resource", "StaticElement", "Resource", "StaticElement", "StaticElement",
                "StaticElement", "StaticElement", "StaticElement");

        for (int i = 0; i < 45; i++) {

            pl.psi.Point currentPoint = null;
            boolean isEmpty = false;
            while (!isEmpty) {
                int x = rand.nextInt(25);
                int y = rand.nextInt(25);
                if (aMapElements.get(new pl.psi.Point(x, y)) == null) {
                    isEmpty = true;
                }
                currentPoint = new Point(x, y);
            }

            String randomElement = elements.get(rand.nextInt(elements.size()));
            ResourceType[] values = ResourceType.values();
            int length = values.length;
            int randIndex;
            switch (randomElement) {
                case "LearningStone":
                    aMapElements.put(currentPoint, new LearningStone());
                    break;
                case "MagicWell":
                    aMapElements.put(currentPoint, new MagicWell());
                    break;
                case "Mine":
                    randIndex = new Random().nextInt(length);

                    aMapElements.put(currentPoint, new Mine(values[randIndex]));
                    break;
                case "Resource":
                    randIndex = new Random().nextInt(length);
                    int resourceAmount;

                    if (values[randIndex].equals(ResourceType.GOLD)) {
                        resourceAmount = 1000;
                    } else {
                        resourceAmount = 2;
                    }
                    aMapElements.put(currentPoint, new Resource(values[randIndex], resourceAmount));
                    break;
                case "StaticElement":
                    aMapElements.put(currentPoint, new StaticElement());
                    break;
            }
        }

        return aMapElements;
    }

}
