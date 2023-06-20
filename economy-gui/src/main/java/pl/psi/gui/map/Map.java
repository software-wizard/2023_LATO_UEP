package pl.psi.gui.map;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import pl.psi.EconomyEngine;
import pl.psi.Point;
import pl.psi.ResourceType;
import pl.psi.gui.Start;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.HeroStatistics;
import pl.psi.mapElements.*;
import pl.psi.player.Player;

import java.io.IOException;
import java.util.*;

public class Map extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        // Full size screen
        Screen screen = Screen.getPrimary();
        primaryStage.setX(screen.getVisualBounds().getMinX());
        primaryStage.setY(screen.getVisualBounds().getMinY());
        primaryStage.setWidth(screen.getVisualBounds().getWidth());
        primaryStage.setHeight(screen.getVisualBounds().getHeight());

        Scene scene = null;

        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation( Start.class.getClassLoader()
                .getResource( "fxml/map.fxml" ) );

        // Code only for test - MapReader
        LinkedList<Player> players = new LinkedList<Player>();
        players.add(Player.builder()
                .economyHero(EconomyHero.builder()
                        .heroStatistics(HeroStatistics.builder()
                                .moveRange(7)
                                .build())
                        .build())
                .build());
        players.get(0).getEconomyHero().setPlayer(players.get(0));

        players.add(Player.builder()
                .economyHero(EconomyHero.builder()
                        .heroStatistics(HeroStatistics.builder()
                                .moveRange(7)
                                .build())
                        .build())
                .build());
        players.get(1).getEconomyHero().setPlayer(players.get(1));
        // TODO - jak to lepiej rozwiązać, przekazanie referencji potrzebne, by sprawdzić, dla danego herosa jest dany player, np. kolor

        BiMap<Point, MapElement> mapElements = HashBiMap.create();
        mapElements.put(new Point(0, 0), players.get(0).getEconomyHero());
        mapElements.put(new Point(24, 24), players.get(1).getEconomyHero());

        Random rand = new Random();
        List<String> elements = Arrays.asList("LearningStone", "MagicWell",
                "Mine", "Resource", "StaticElement", "Resource");

        for (int i = 0; i < 35; i++) {

            Point currentPoint = null;
            boolean isEmpty = false;
            while (!isEmpty) {
                int x = rand.nextInt(25);
                int y = rand.nextInt(25);
                if (mapElements.get(new Point(x, y)) == null) {
                    isEmpty = true;
                }
                currentPoint = new Point(x, y);
            }

            String randomElement = elements.get(rand.nextInt(elements.size()));
            System.out.println(randomElement);
            System.out.println(currentPoint);
            switch (randomElement) {
                case "LearningStone":
                    mapElements.put(currentPoint, new LearningStone());
                    break;
                case "MagicWell":
                    mapElements.put(currentPoint, new MagicWell());
                    break;
                case "Mine":
                    ResourceType[] values = ResourceType.values();
                    int length = values.length;
                    int randIndex = new Random().nextInt(length);

                    mapElements.put(currentPoint, new Mine(values[randIndex]));
                    break;
                case "Resource":
                    mapElements.put(currentPoint, new Resource(ResourceType.GOLD, 1000));
                    break;
                case "StaticElement":
                    mapElements.put(currentPoint, new StaticElement());
                    break;
            }
        }

        EconomyEngine economyEngine = new EconomyEngine(players, mapElements);

        loader.setController( new MapController(economyEngine) );
        scene = new Scene( loader.load());
        scene.getStylesheets().add("fxml/map.css");

        primaryStage.setScene( scene );
        primaryStage.show();
    }
}
