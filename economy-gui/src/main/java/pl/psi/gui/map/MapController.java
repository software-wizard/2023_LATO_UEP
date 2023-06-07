package pl.psi.gui.map;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pl.psi.EconomyEngine;
import pl.psi.Point;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MapController implements PropertyChangeListener {

//    private final EconomyEngine economyEngine;
    @FXML
    private GridPane gridMap;

//    public MapController(EconomyEngine aEngine) {
//        this.economyEngine = aEngine;
//    }

    @FXML
    private void initialize()
    {
        refreshGui();
//        economyEngine.addObserver(this);
    }

    private void refreshGui() {

        gridMap.getChildren()
                .clear();
        for( int x = 0; x < 5; x++ )
        {
            for( int y = 0; y < 0; y++ )
            {
//                Point currentPoint = new Point( x, y);
                final MapTile mapTile = new MapTile( "" );
                mapTile.setBackground(Color.RED);
                gridMap.add( mapTile, x, y );
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        refreshGui();
    }
}
