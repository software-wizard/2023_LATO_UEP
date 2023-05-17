package WarMachines.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


class MapTile extends StackPane
{

    private final Rectangle rect;
    //private final Label label;
    private final Text text;

    MapTile( final String aName )
    {
//        rect = new Rectangle( 60, 60 );
//        rect.setFill( Color.WHITE );
//        rect.setStroke( Color.BLACK );
//        getChildren().add( rect );
//        label = new Label( aName );
//        label.setAlignment(Pos.CENTER);
//        label.setWrapText(true);
//        label.setMaxWidth(rect.getWidth() - 10);
//        label.setMaxHeight(rect.getHeight());
//
//        getChildren().add( label );

        rect = new Rectangle(60, 60);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);
        getChildren().add(rect);

        text = new Text(aName);
        text.setWrappingWidth(rect.getWidth());
        text.setTextAlignment(TextAlignment.CENTER);

        getChildren().add(text);
    }

    void setName( final String aName )
    {
        //label.setText( aName );
        text.setText(aName);
    }

    void setBackground( final Color aColor )
    {
        rect.setFill( aColor );
    }
}
