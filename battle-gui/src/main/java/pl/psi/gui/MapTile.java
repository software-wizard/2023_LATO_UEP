package pl.psi.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class MapTile extends StackPane
{

    private final Rectangle rect;
    private final Label label;

    MapTile( final String aName )
    {
        rect = new Rectangle( 60, 60 );
        rect.setFill( Color.WHITE );
        rect.setStroke( Color.BLACK );
        getChildren().add( rect );
        label = new Label( aName );
        label.setAlignment(Pos.CENTER);
        label.setWrapText(true);
        label.setMaxWidth(rect.getWidth() - 10);
        label.setMaxHeight(rect.getHeight());

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

    void setBorderColor(final Color aColor){
        rect.setStroke(aColor);
        rect.setStrokeWidth(2.0);
    }
}
