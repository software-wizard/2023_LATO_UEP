package pl.psi.gui.map;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pl.psi.EconomyEngine;
import pl.psi.MapObjectIf;
import pl.psi.Point;
import pl.psi.hero.EconomyHero;
import pl.psi.mapElements.MapElement;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.util.Optional;

public class MapController implements PropertyChangeListener {

    private final EconomyEngine economyEngine;
    @FXML
    private GridPane gridMap;

    public MapController(EconomyEngine aEngine) {
        this.economyEngine = aEngine;
    }

    @FXML
    private void initialize()
    {
        refreshGui();
        economyEngine.addObserver(this);
    }

    private void refreshGui() {

        gridMap.getChildren()
                .clear();
        for( int x = 0; x < 60; x++ )
        {
            for( int y = 0; y < 30; y++ )
            {
                Point currentPoint = new Point( x, y );
                final MapTile mapTile = new MapTile( "" );

                // Draw map
                Optional<MapElement> mapElement = Optional.ofNullable(economyEngine.getMapElement(currentPoint));
                Optional<EconomyHero> economyHero = Optional.ofNullable(economyEngine.getEconomyHero(currentPoint));

                mapTile.setBackground(Color.GREEN);
                mapElement.ifPresent((mapElementToGUI -> {
                    if (!mapElementToGUI.isInteractive()) {
                        mapTile.setBackground(Color.GRAY);
                    } else {
                        mapTile.setBackground(Color.GREEN);
                    }
                }));

                // Draw heroes
                economyHero.ifPresent(economyHeroToGUI -> {
                    mapTile.setName("Hero");
                });

                if (economyEngine.canMove(currentPoint, economyEngine.getCurrentPlayer().getEconomyHero())) {
                    mapTile.setBackground(Color.BLUEVIOLET);
                    mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                        economyEngine.move(currentPoint, economyEngine.getCurrentPlayer().getEconomyHero());
                        // TODO observer does not work?
                        refreshGui();
                    });
                }

                gridMap.add( mapTile, x, y );
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        refreshGui();
    }
}
