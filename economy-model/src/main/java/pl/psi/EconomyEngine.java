package pl.psi;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.psi.creatures.EconomyCreature;
import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.HeroStatistics;
import pl.psi.mapElements.*;
import pl.psi.player.Player;
import pl.psi.player.PlayerResources;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EconomyEngine {

    private final Board board;
    private final TurnQueue turnQueue;
    private EconomyEngine economyEngine;
    private final LinkedList<Player> aPlayers;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);


    public EconomyEngine(LinkedList<Player> aPlayers) {

        LinkedList<Player> newPlayers = buildPlayers(aPlayers);

        this.aPlayers = newPlayers;
        this.board = new Board(getMapElements(newPlayers));
        this.turnQueue = new TurnQueue(newPlayers);
        turnQueue.addObserver(board);
    }



    public LinkedList<Player> buildPlayers(LinkedList<Player> aPlayers) {

        try {
        EconomyCreature c1 = new EconomyNecropolisFactory().create(false,1,10);

            for (int i=0; i < aPlayers.size(); i++) {

            PlayerResources playerResources = PlayerResources.builder()
                    .wood(1000)
                    .ore(1000)
                    .gold(1000)
                    .crystal(1000)
                    .gems(1000)
                    .build();

            EconomyHero economyHero = EconomyHero.builder()
                    .heroStatistics(HeroStatistics.builder()
                            .player(aPlayers.get(i))
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
                    .name(aPlayers.get(i).getName())
                    .resources(playerResources)
                    .economyHero(economyHero)
                    .town(aPlayers.get(i).getTown())
                    .heroName(aPlayers.get(i).getHeroName())
                    .bonus(aPlayers.get(i).getBonus())
                    .color(Color.yellow)
                    .build();

            aPlayers.set(i, aPlayer);

        }

    } catch (Exception e) {

        e.printStackTrace();
    }

        return  aPlayers;
    }


    private BiMap<Point, MapElement> getMapElements(LinkedList<Player> aPlayers) {
        BiMap<Point, MapElement> aMapElements = HashBiMap.create();

//        // Create Players
//        aMapElements.put(new Point(0, 0), aPlayers.get(0).getEconomyHero());
//        aMapElements.put(new Point(24, 24), aPlayers.get(1).getEconomyHero());
        for (int i=0; i < aPlayers.size(); i++) {
            Random rand = new Random();
            aMapElements.put(new Point(rand.nextInt(24), rand.nextInt(24)), aPlayers.get(i).getEconomyHero());
        }

        Castle castle1 = new Castle(Castle.FractionType.NECROPOLIS);
        aMapElements.put(new Point(2, 2), castle1);

        Random rand = new Random();
        List<String> elements = Arrays.asList("LearningStone", "MagicWell",
                "Mine", "Resource", "StaticElement", "Resource");

        for (int i = 0; i < 35; i++) {

            Point currentPoint = null;
            boolean isEmpty = false;
            while (!isEmpty) {
                int x = rand.nextInt(25);
                int y = rand.nextInt(25);
                if (aMapElements.get(new Point(x, y)) == null) {
                    isEmpty = true;
                }
                currentPoint = new Point(x, y);
            }

            String randomElement = elements.get(rand.nextInt(elements.size()));
            System.out.println(randomElement);
            System.out.println(currentPoint);
            switch (randomElement) {
                case "LearningStone":
                    aMapElements.put(currentPoint, new LearningStone());
                    break;
                case "MagicWell":
                    aMapElements.put(currentPoint, new MagicWell());
                    break;
                case "Mine":
                    ResourceType[] values = ResourceType.values();
                    int length = values.length;
                    int randIndex = new Random().nextInt(length);

                    aMapElements.put(currentPoint, new Mine(values[randIndex]));
                    break;
                case "Resource":
                    aMapElements.put(currentPoint, new Resource(ResourceType.GOLD, 1000));
                    break;
                case "StaticElement":
                    aMapElements.put(currentPoint, new StaticElement());
                    break;
            }
        }

        return aMapElements;
    }
    public EconomyHero getEcoHero(){
        return turnQueue.getCurrentPlayer().getEconomyHero();
    }
    public MapElement getMapElement(Point aPoint) {
        return board.getMapElements().get(aPoint);
    }

    public EconomyHero getEconomyHero(Point aPoint) {
        return board.getMapHero().get(aPoint);
    }
    public boolean canMove(final Point aPoint, final EconomyHero aChoosenEconomyHero) {
        return board.canMove(aChoosenEconomyHero, aPoint);
    }

    public void move(final Point aPoint, final EconomyHero aChoosenEconomyHero) {
        board.move(aChoosenEconomyHero, aPoint);
        fireEvent("");
    }

    private void fireEvent(String aType) {
        observerSupport.firePropertyChange(new PropertyChangeEvent(this, aType, null, null));
    }

    public void addObserver(final PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aObserver);
        turnQueue.addObserver(aObserver);

    }
    
    public Player getCurrentPlayer() {
        return turnQueue.getCurrentPlayer();
    }

    public void endTurn() {
        turnQueue.nextTurn();
    }

    public int getCurrentDay() {
        return turnQueue.getDay();
    }

    public int getMapSize() {
        return this.board.getMapSize();
    }

}
