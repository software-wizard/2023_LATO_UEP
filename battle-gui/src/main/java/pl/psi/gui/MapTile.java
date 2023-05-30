package pl.psi.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

class MapTile extends StackPane
{
    private final Rectangle rect;
    private final Label label;
    private Label amountLabel;
    private Rectangle amountRect;

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
    public void setMirrorGraphic(String mapObjectName) throws FileNotFoundException {
        Image image = loadImage(mapObjectName);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(rect.getHeight());
        imageView.setFitWidth(rect.getWidth());

        Affine mirrorTransform = new Affine();
        mirrorTransform.appendScale(-1, 1); //OX symmetry
        mirrorTransform.appendTranslation(-rect.getWidth(), 0);

        ImageView mirrorImageView = new ImageView(image);
        mirrorImageView.setFitHeight(rect.getHeight());
        mirrorImageView.setFitWidth(rect.getWidth());
        mirrorImageView.getTransforms().add(mirrorTransform);

        label.setGraphic(mirrorImageView);
    }

    private Image loadImage(String mapObjectName) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("battle-gui/src/main/resources/MapObjects/" + mapObjectName + ".png");
        return new Image(inputStream);
    }

    public void setHpLabel(Integer mapObjectHp){
        hpLabel = new Label();
        hpLabel.setTextFill(Color.WHITE);
        hpLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 8px;");
        hpLabel.setAlignment(Pos.BOTTOM_CENTER);
        hpLabel.setPrefWidth(30);
        hpLabel.setPrefHeight(10);

        hpRect = new Rectangle(30, 10);
        hpRect.setFill(Color.PURPLE);
        hpRect.setOpacity(0.8);

        StackPane.setAlignment(hpRect, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(hpLabel, Pos.BOTTOM_RIGHT);
        getChildren().addAll(hpRect, hpLabel);

        String hpText = Integer.toString(mapObjectHp);
        hpLabel.setText(hpText);
    }

    public void setAmountLabel(Integer mapObjectAmount){
        amountLabel = new Label();
        amountLabel.setTextFill(Color.WHITE);
        amountLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 8px;");
        amountLabel.setAlignment(Pos.TOP_CENTER);
        amountLabel.setPrefWidth(30);
        amountLabel.setPrefHeight(10);

        amountRect = new Rectangle(30, 10);
        amountRect.setFill(Color.LIGHTSEAGREEN);
        amountRect.setOpacity(0.8);

        StackPane.setAlignment(amountRect, Pos.TOP_RIGHT);
        StackPane.setAlignment(amountLabel, Pos.TOP_RIGHT);
        getChildren().addAll(amountRect, amountLabel);

        String amountText = Integer.toString(mapObjectAmount);
        amountLabel.setText(amountText);
    }
}
