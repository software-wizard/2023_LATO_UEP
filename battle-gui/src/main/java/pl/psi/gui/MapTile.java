package pl.psi.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

class MapTile extends StackPane
{

    private final Rectangle rect;
    private final Label label;

    MapTile( final String aName ) throws FileNotFoundException {
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
//        label.setText( aName );
    }

    void setBackground( final Color aColor )
    {
        rect.setFill( aColor );
    }

    void setBorderColor(final Color aColor){
        rect.setStroke(aColor);
        rect.setStrokeWidth(2.0);
    }

    public void setGraphic(String mapObjectName) throws FileNotFoundException {
        ImageView imageView = new ImageView(loadImage(mapObjectName));
        imageView.setFitHeight(rect.getHeight());
        imageView.setFitWidth(rect.getWidth());
        label.setGraphic(imageView);
    }

    private Image loadImage(String mapObjectName) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("battle-gui/src/main/resources/MapObjects/" + mapObjectName + ".png");
        return new Image(inputStream);
    }
}
