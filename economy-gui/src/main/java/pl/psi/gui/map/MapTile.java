package pl.psi.gui.map;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MapTile extends StackPane  {

    private final Rectangle rect;
    private final Label label;
    private final Label label2;

    MapTile( final String aName )
    {
        rect = new Rectangle( 30, 30 );
        rect.setFill( Color.WHITE );
        getChildren().add( rect );
        label = new Label( aName );
        getChildren().add( label );
        label2 = new Label();
        getChildren().add(label2);
    }

    void setName( final String aName )
    {
        label.setText( aName );
    }

    void setBackground( final Color aColor )
    {
        rect.setFill( aColor );
    }

    public void setGraphic(String mapObjectName) throws FileNotFoundException {
        ImageView imageView = new ImageView(loadImage(mapObjectName));
        imageView.setFitHeight(rect.getHeight());
        imageView.setFitWidth(rect.getWidth());
        imageView.setPreserveRatio(true);
        label.setGraphic(imageView);
    }

    public void setHeroGraphic(String mapObjectName) throws FileNotFoundException {
        ImageView imageView = new ImageView(loadImage(mapObjectName));
        imageView.setFitHeight(rect.getHeight());
        imageView.setFitWidth(rect.getWidth());
        imageView.setPreserveRatio(true);
        label2.setGraphic(imageView);
    }

    private Image loadImage(String mapObjectName) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("economy-gui/src/main/resources/pl/map/"+mapObjectName+".png");
        return new Image(inputStream);
    }
}
