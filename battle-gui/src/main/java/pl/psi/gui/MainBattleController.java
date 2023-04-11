package pl.psi.gui;

import pl.psi.GameEngine;
import pl.psi.Hero;
import pl.psi.Point;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class MainBattleController {
    private final GameEngine gameEngine;
    @FXML
    private GridPane gridMap;
    @FXML
    private Button passButton;

    public MainBattleController(final Hero aHero1, final Hero aHero2) {
        gameEngine = new GameEngine(aHero1, aHero2);
    }

    @FXML
    private void initialize() {
        refreshGui();
    }

    private void refreshGui() {
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 10; y++) {
                final MapTile mapTile = new MapTile("");
                gridMap.add(mapTile, x, y);
            }
        }
    }
}
