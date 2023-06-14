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
import pl.psi.mapElements.MapElement;
import pl.psi.mapElements.Mine;
import pl.psi.mapElements.Resource;
import pl.psi.mapElements.StaticElement;
import pl.psi.player.Player;

import java.io.IOException;
import java.util.LinkedList;

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
                                .moveRange(8)
                                .build())
                        .build())
                .build());
        BiMap<Point, MapElement> mapElements = HashBiMap.create();
        mapElements.put(new Point(8, 2), new StaticElement());
        mapElements.put(new Point(8, 3), new StaticElement());
        mapElements.put(new Point(9, 3), new StaticElement());
        mapElements.put(new Point(9, 2), new StaticElement());
        mapElements.put(new Point(5, 5), players.get(0).getEconomyHero());
        mapElements.put(new Point(1, 2), new StaticElement());
        mapElements.put(new Point(3, 2), new Resource(ResourceType.GOLD, 5));
        mapElements.put(new Point(3, 1), new Mine(ResourceType.GOLD));

        EconomyEngine economyEngine = new EconomyEngine(players, mapElements);

        loader.setController( new MapController(economyEngine) );
        scene = new Scene( loader.load());
        scene.getStylesheets().add("fxml/map.css");

        primaryStage.setScene( scene );
        primaryStage.show();
    }
}
