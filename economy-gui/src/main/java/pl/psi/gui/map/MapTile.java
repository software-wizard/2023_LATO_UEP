package pl.psi.gui.map;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MapTile extends StackPane  {

    private final Rectangle rect;
    private final Label label;

    MapTile( final String aName )
    {
        rect = new Rectangle( 30, 30 );
        rect.setFill( Color.WHITE );
        getChildren().add( rect );
        label = new Label( aName );
        getChildren().add( label );
    }

    void setName( final String aName )
    {
        label.setText( aName );
    }

    void setBackground( final Color aColor )
    {
        rect.setFill( aColor );
    }
}
