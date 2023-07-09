package pl.psi.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;
import lombok.Getter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

class MapTile extends StackPane
{
    private final Rectangle rect;
    private final Label label;
    private Label hpLabel;
    private Label amountLabel;
    private Label backgroundLabel;
    private Label backgroundStroke;
    private Rectangle hpRect;
    private Rectangle amountRect;
    private Rectangle strokeRect;
    @Getter
    private ImageView imageView;

    MapTile( final String aName ) throws FileNotFoundException {
        rect = new Rectangle( 60, 60 );
        rect.setOpacity(0.2);
        strokeRect = new Rectangle(58, 58);
        strokeRect.setFill(Color.TRANSPARENT);

        backgroundLabel = new Label();
        backgroundLabel.setAlignment(Pos.CENTER);
        backgroundLabel.setWrapText(true);
        backgroundLabel.setMaxWidth(rect.getWidth() - 10);
        backgroundLabel.setMaxHeight(rect.getHeight());
        getChildren().add(backgroundLabel);

        backgroundStroke = new Label();
        backgroundStroke.setAlignment(Pos.CENTER);
        backgroundStroke.setWrapText(true);
        backgroundStroke.setMaxWidth(60);
        backgroundStroke.setMaxHeight(60);
        BorderStroke stroke = new BorderStroke(Color.YELLOWGREEN, BorderStrokeStyle.SOLID, null, BorderStroke.THIN);
        Border border = new Border(stroke);
        backgroundStroke.setBorder(border);
        getChildren().add(backgroundStroke);

        getChildren().add( rect );
        getChildren().add(strokeRect);

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
        rect.setFill(aColor);
        strokeRect.setFill( aColor );
    }

    void setMoveBackground( final Color aColor )
    {
        rect.setFill(aColor);
        rect.setOpacity(0.27);
        strokeRect.setFill( aColor );
        strokeRect.setOpacity(0.27);
    }

    void setBorderColor(final Color aColor){
        strokeRect.setStroke(aColor);
        strokeRect.setStrokeWidth(2.0);
    }

    void setGraphicBorder() {
        rect.setFill(Color.ORANGE);
        rect.setOpacity(0.35);

        BorderStroke stroke = new BorderStroke(Color.LIGHTGOLDENRODYELLOW, BorderStrokeStyle.SOLID, null, BorderStroke.THIN);
        Border border = new Border(stroke);
        backgroundStroke.setBorder(border);
    }

    public void setGraphic(String mapObjectName) throws FileNotFoundException {
        imageView = new ImageView(loadImage(mapObjectName));
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

    public void setBackgroundGraphic() throws FileNotFoundException {
        ImageView imageView = new ImageView(loadBackgroundImage());
        imageView.setFitHeight(rect.getHeight());
        imageView.setFitWidth(rect.getWidth());
        backgroundLabel.setGraphic(imageView);
    }

    private Image loadImage(String mapObjectName) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("battle-gui/src/main/resources/MapObjects/" + mapObjectName + ".png");
        return new Image(inputStream);
    }

    private Image loadBackgroundImage() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("battle-gui/src/main/resources/MapTiles/tgrb000.png");
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

    public void setMirrorHpLabel(Integer mapObjectHp){
        hpLabel = new Label();
        hpLabel.setTextFill(Color.WHITE);
        hpLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 8px;");
        hpLabel.setAlignment(Pos.BOTTOM_CENTER);
        hpLabel.setPrefWidth(30);
        hpLabel.setPrefHeight(10);

        hpRect = new Rectangle(30, 10);
        hpRect.setFill(Color.CORNFLOWERBLUE);
        hpRect.setOpacity(0.8);

        StackPane.setAlignment(hpRect, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(hpLabel, Pos.BOTTOM_LEFT);
        getChildren().addAll(hpRect, hpLabel);

        String hpText = Integer.toString(mapObjectHp);
        hpLabel.setText(hpText);
    }

    public void setMirrorAmountLabel(Integer mapObjectAmount){
        amountLabel = new Label();
        amountLabel.setTextFill(Color.WHITE);
        amountLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 8px;");
        amountLabel.setAlignment(Pos.TOP_CENTER);
        amountLabel.setPrefWidth(30);
        amountLabel.setPrefHeight(10);

        amountRect = new Rectangle(30, 10);
        amountRect.setFill(Color.CORAL);
        amountRect.setOpacity(0.8);

        StackPane.setAlignment(amountRect, Pos.TOP_LEFT);
        StackPane.setAlignment(amountLabel, Pos.TOP_LEFT);
        getChildren().addAll(amountRect, amountLabel);

        String amountText = Integer.toString(mapObjectAmount);
        amountLabel.setText(amountText);
    }
}