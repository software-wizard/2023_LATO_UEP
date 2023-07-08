// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco.gui.map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import pl.psi.eco.gui.Start;

import java.io.IOException;

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
//        LinkedList<Player> players = new LinkedList<Player>();
//        players.add(Player.builder()
//                .economyHero(EconomyHero.builder()
//                        .heroStatistics(HeroStatistics.builder()
//                                .moveRange(7)
//                                .build())
//                        .build())
//                .build());
//        players.get(0).getEconomyHero().setPlayer(players.get(0));
//
//        players.add(Player.builder()
//                .economyHero(EconomyHero.builder()
//                        .heroStatistics(HeroStatistics.builder()
//                                .moveRange(7)
//                                .build())
//                        .build())
//                .build());
//        players.get(1).getEconomyHero().setPlayer(players.get(1));
//        // TODO - jak to lepiej rozwiązać, przekazanie referencji potrzebne, by sprawdzić, dla danego herosa jest dany player, np. kolor
//        // EconomyEngine iteruje po Player i szuka EconomyHero
//        BiMap<Point, MapElement> mapElements = HashBiMap.create();
//        mapElements.put(new Point(0, 0), players.get(0).getEconomyHero());
//        mapElements.put(new Point(24, 24), players.get(1).getEconomyHero());
//
//        Castle castle1 = new Castle(Castle.FractionType.NECROPOLIS);
//
//        mapElements.put(new Point(2, 2), castle1);
//
//
//        Random rand = new Random();
//        List<String> elements = Arrays.asList("LearningStone", "MagicWell",
//                "Mine", "Resource", "StaticElement", "Resource");
//
//        for (int i = 0; i < 35; i++) {
//
//            Point currentPoint = null;
//            boolean isEmpty = false;
//            while (!isEmpty) {
//                int x = rand.nextInt(25);
//                int y = rand.nextInt(25);
//                if (mapElements.get(new Point(x, y)) == null) {
//                    isEmpty = true;
//                }
//                currentPoint = new Point(x, y);
//            }
//
//            String randomElement = elements.get(rand.nextInt(elements.size()));
//            System.out.println(randomElement);
//            System.out.println(currentPoint);
//            switch (randomElement) {
//                case "LearningStone":
//                    mapElements.put(currentPoint, new LearningStone());
//                    break;
//                case "MagicWell":
//                    mapElements.put(currentPoint, new MagicWell());
//                    break;
//                case "Mine":
//                    ResourceType[] values = ResourceType.values();
//                    int length = values.length;
//                    int randIndex = new Random().nextInt(length);
//
//                    mapElements.put(currentPoint, new Mine(values[randIndex]));
//                    break;
//                case "Resource":
//                    mapElements.put(currentPoint, new Resource(ResourceType.GOLD, 1000));
//                    break;
//                case "StaticElement":
//                    mapElements.put(currentPoint, new StaticElement());
//                    break;
//            }
//        }

//        EconomyEngine economyEngine = new EconomyEngine(players, mapElements);
//
//        loader.setController( new MapController(economyEngine) );
//        scene = new Scene( loader.load());
//        scene.getStylesheets().add("fxml/map.css");

        primaryStage.setScene( scene );
        primaryStage.show();
    }
}
