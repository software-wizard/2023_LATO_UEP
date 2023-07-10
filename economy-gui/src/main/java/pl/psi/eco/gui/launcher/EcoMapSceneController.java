// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco.gui.launcher;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import pl.psi.eco.Board;
import pl.psi.eco.converter.EcoBattleConverter;
import pl.psi.eco.EconomyEngine;
import pl.psi.eco.gui.inventory.EcoEqSceneController;
import pl.psi.eco.gui.map.MapController;
import pl.psi.eco.hero.EconomyHero;
import pl.psi.eco.hero.HeroEquipment;
import pl.psi.eco.player.PlayerResources;


public class EcoMapSceneController implements PropertyChangeListener {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private Stage stageInventory;
    private Scene sceneInventory;
    private Parent rootInventory;

    private EconomyEngine economyEngine;

    @FXML
    Label currentPlayerLabel;
    @FXML
    Label currentDayLabel;
    @FXML
    Label heroNameLabel;
    @FXML
    Label goldLabel;
    @FXML
    Label woodLabel;
    @FXML
    Label oreLabel;
    @FXML
    Label crystalLabel;
    @FXML
    Label sulfurLabel;
    @FXML
    Label mercuryLabel;
    @FXML
    Label gemsLabel;


    @FXML
    AnchorPane mapPane;


    public void loadEconomyEngine(EconomyEngine economyEngine) {
        this.economyEngine = economyEngine;
        this.economyEngine.getBoard().addObserver(this);
    }

    public void refreshGui() throws IOException {
        displayCurrentPlayerName(economyEngine.getCurrentPlayer().getName());
        displayName(economyEngine.getCurrentPlayer().getHeroName());
        displayResources(economyEngine.getCurrentPlayer().getResources());
        displayCurrentDay(economyEngine.getCurrentDay());

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/map.fxml"));
        MapController mapController = new MapController(economyEngine);
        this.economyEngine.getBoard().addObserver(mapController);
        loader.setController( mapController );
        Scene scene = new Scene( loader.load());

        // set size to node casting to Pane
        Node paneProbably = scene.getRoot().getChildrenUnmodifiable().stream().filter(BorderPane.class::isInstance).map(BorderPane.class::cast).findFirst().get().getCenter();


        mapPane.getChildren().add(paneProbably);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Event observer - EcoMapSceneController");
        if (evt.getPropertyName().equals(Board.HERO_MOVED)) {
            try {
                refreshGui();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void displayName(String heroName) {
        heroNameLabel.setText("Hero name: " + heroName);
    }

    public void displayCurrentPlayerName(String playerName) {
        currentPlayerLabel.setText("Current player name: " + playerName);
    }

    public void displayResources(PlayerResources playerResources) {
        goldLabel.setText("Gold: "+ playerResources.getGold());
        woodLabel.setText("Wood: "+ playerResources.getWood());
        oreLabel.setText("Ore: "+ playerResources.getOre());
        crystalLabel.setText("Crystal: "+ playerResources.getCrystal());
        sulfurLabel.setText("Sulfur: "+ playerResources.getSulfur());
        mercuryLabel.setText("Mercury: "+ playerResources.getMercury());
        gemsLabel.setText("Gems: "+ playerResources.getGems());
    }

    public void goToBattle(ActionEvent event) throws IOException {

        try {
            EcoBattleConverter converter = new EcoBattleConverter();
            converter.startBattle(economyEngine.getCurrentPlayer().getEconomyHero(), economyEngine.getCurrentPlayer().getEconomyHero());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToLauncher(ActionEvent event) throws IOException {
        root = FXMLLoader.load((getClass().getClassLoader().getResource("fxml/ecoLauncherScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new  Scene(root);

        Screen screen = Screen.getPrimary();
        stage.setX(screen.getVisualBounds().getMinX());
        stage.setY(screen.getVisualBounds().getMinY());
        stage.setWidth(screen.getVisualBounds().getWidth());
        stage.setHeight(screen.getVisualBounds().getHeight());

        stage.setScene( scene );
        stage.show();
    }

    public void endTurn(ActionEvent event) throws IOException{
        economyEngine.endTurn();
        refreshGui();
    }

    public void displayCurrentDay(int day) {
        currentDayLabel.setText("Current day: " + day);
    }

    public void openEq(ActionEvent event) throws IOException {

        EconomyHero ecoHero = economyEngine.getEcoHero();
        HeroEquipment aHeroEq = ecoHero.getHeroEquipment();

        FXMLLoader loaderInventory = new FXMLLoader(getClass().getClassLoader().getResource("fxml/eq.fxml"));
        rootInventory = loaderInventory.load();

        try {
            EcoEqSceneController ecoEqSceneController = loaderInventory.getController();
            ecoEqSceneController.refreshEq(aHeroEq,economyEngine);
        } catch (Exception e) {
            e.printStackTrace();
        }

        stageInventory = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneInventory = new  Scene(rootInventory);
        stageInventory.setScene( sceneInventory );
        stageInventory.setX( 5 );
        stageInventory.setY( 5 );
        stageInventory.show();

    }
}
