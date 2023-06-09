package pl.psi.gui.map;

import javafx.fxml.FXML;
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
        for( int x = 0; x < 100; x++ )
        {
            for( int y = 0; y < 80; y++ )
            {
                Point currentPoint = new Point( x, y );
                final MapTile mapTile = new MapTile( "" );

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

                economyHero.ifPresent(economyHeroToGUI -> {
                    mapTile.setName("Hero");
                });

                gridMap.add( mapTile, x, y );
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        refreshGui();
    }
}
