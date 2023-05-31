package pl.psi.gui;

import javafx.geometry.Pos;
import pl.psi.MapObjectIf;
import pl.psi.GameEngine;
import pl.psi.Hero;
import pl.psi.Point;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class MainBattleController implements PropertyChangeListener {
    private final GameEngine gameEngine;
    @FXML
    private GridPane gridMap;
    @FXML
    private Button passButton;

    public MainBattleController(final Hero aHero1, final Hero aHero2) {
        gameEngine = new GameEngine(aHero1, aHero2);
    }

    @FXML
    private void initialize() throws FileNotFoundException {
        refreshGui();
        gameEngine.addObserver(this);
    }

    private void refreshGui() throws FileNotFoundException {
        gridMap.getChildren()
                .clear();
        gridMap.setAlignment(Pos.CENTER);
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 10; y++) {
                Point currentPoint = new Point(x, y);
                List<Point> points = new ArrayList<Point>();
                points.add(currentPoint);
                Optional<MapObjectIf> gameObject = gameEngine.getMapObject(currentPoint);
                final MapTile mapTile = new MapTile("");

                gameObject.ifPresent((mapObject) -> {
                    try {
                        if(gameEngine.hero1.getMapObjectIfs().contains(mapObject)){
                            mapTile.setBackground(Color.rgb(243, 198, 85));
                            mapTile.setGraphic(mapObject.getName());
                            mapTile.setHpLabel(mapObject.getCurrentHp());
                            mapTile.setAmountLabel(mapObject.getAmount());
                        } else {
                            mapTile.setBackground(Color.rgb(166, 206, 200));
                            mapTile.setMirrorGraphic(mapObject.getName());
                            mapTile.setHpLabel(mapObject.getCurrentHp());
                            mapTile.setAmountLabel(mapObject.getAmount());
                        }
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });

                if (gameEngine.isCurrentMapObject(currentPoint)) {
                    mapTile.setBorderColor(Color.GREENYELLOW);
                }
                if (gameEngine.canMove(currentPoint)) {
                    mapTile.setBackground(Color.GREY);
                    mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                        gameEngine.move(currentPoint);
                    });
                }

                if (gameEngine.canAttack(currentPoint)) {
                    mapTile.setBorderColor(Color.RED);
                    mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                        gameEngine.attack(currentPoint);
                    });
                }

                if (gameEngine.canHeal(currentPoint)) {
                    mapTile.setBorderColor(Color.BLUEVIOLET);
                    mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                        gameEngine.heal(currentPoint);
                    });
                }

                gridMap.add(mapTile, x, y);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            refreshGui();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
