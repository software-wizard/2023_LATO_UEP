package pl.psi.gui;

import javafx.geometry.Pos;
import pl.psi.MapObjectIf;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.io.FileNotFoundException;
import java.util.Optional;

public class MainBattleController implements PropertyChangeListener {
    private final GameEngine gameEngine;
    @FXML
    private GridPane gridMap;
    @FXML
    private Button passButton;

    @FXML
    private Button spellButton;



    public MainBattleController( final Hero aHero1, final Hero aHero2 )
    {
        gameEngine = new GameEngine( aHero1, aHero2 );
    }
    @FXML
    private void initialize() throws FileNotFoundException {
        refreshGui();
        initializeSpellBook();
        gameEngine.addObserver(this);
    }

    private void refreshGui() throws FileNotFoundException {
        gridMap.getChildren()
            .clear();
        gridMap.setAlignment(Pos.CENTER);
        for( int x = 0; x < 15; x++ )
        {
            for( int y = 0; y < 10; y++ )
            {
                Point currentPoint = new Point(x, y);
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

//                if(!gameEngine.isControllable()) {
//                    if (gameEngine.isTurnQueueEmpty()) {
//                        gameEngine.endOfTurn();
//                    } else {
//                        gameEngine.pass();
//                    }
//                }
                // dać ewentualnie do gameEngine

                if (gameEngine.isCurrentMapObject(currentPoint)) {
                    mapTile.setBorderColor(Color.GREENYELLOW);
                }

                if (gameEngine.canMove(currentPoint)) {
                    mapTile.setBackground(Color.GREY);
                    mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                        gameEngine.move(currentPoint);
                    });
                }

                if(gameEngine.canPerformAction() && gameEngine.isControllable()) {
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
                }
                gridMap.add(mapTile, x, y);
            }
        }

        //repair skipping first object from queue
        //wywalic z kontrolera, do kolejki np
        if(gameEngine.canPerformAction() && !gameEngine.isControllable()){
            Point randPoint = gameEngine.getMapObjectPosition(gameEngine.getRandomMapObject());

            if (gameEngine.canAttack(randPoint)){
                gameEngine.attack(randPoint);
            }
            if (gameEngine.canHeal(randPoint)){
                gameEngine.heal(randPoint);
            }
        }
    }

    private void initializeSpellBook(){
        spellButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Parent root;
                try{
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/spell-book-gui2.fxml")));
                    Stage stage = new Stage();
                    stage.setTitle("Spell Book");
                    stage.setScene(new Scene(root, 250, 450));
                    stage.show();
                }catch (final IOException aE){
                    aE.printStackTrace();
                }
            }
        });
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
