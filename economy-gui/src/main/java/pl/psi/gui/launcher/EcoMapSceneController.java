package pl.psi.gui.launcher;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.psi.Point;
import pl.psi.ResourceType;
import pl.psi.artifacts.ArtifactFactory;
import pl.psi.converter.EcoBattleConverter;
import pl.psi.gui.inventory.EcoEqSceneController;
import pl.psi.gui.map.MapTile;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.HeroEquipment;
import pl.psi.EconomyEngine;
import pl.psi.map.MapElementAdapter;
import pl.psi.mapElements.MapElement;
import pl.psi.mapElements.Mine;
import pl.psi.mapElements.Resource;
import pl.psi.mapElements.StaticElement;
import pl.psi.player.Player;
import pl.psi.player.PlayerResources;


public class EcoMapSceneController {
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
    private GridPane gridMap;
    public void loadMapGUI(EconomyEngine economyEngine) {
        this.economyEngine = economyEngine;
    }

    public void refreshGui() {
        displayCurrentPlayerName(economyEngine.getCurrentPlayer().getName());
        displayName(economyEngine.getCurrentPlayer().getHeroName());
        displayResources(economyEngine.getCurrentPlayer().getResources());
        displayCurrentDay(economyEngine.getCurrentDay());


        //displayMap(economyEngine.getCurrentPlayer().getMap());
    }

    public void displayMap(Map<Point, MapElement> map) {
        gridMap.getChildren().clear();
        //...
    }
    public void displayName(String heroName) {
        heroNameLabel.setText("Hero name: " + heroName);
    }

    public void displayCurrentPlayerName(String playerName) {
        currentPlayerLabel.setText("Current player name: " + playerName);
    }

    public void displayAllPlayersWithProperties(List<Player> players) {
        for (Player player : players) {
            System.out.println(player.getName() + " " + player.getTown() + " " + player.getHeroName() + " " + player.getBonus());
        }
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

    public void switchToLauncher(ActionEvent event) throws IOException {
        root = FXMLLoader.load((getClass().getClassLoader().getResource("fxml/ecoLauncherScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new  Scene(root);
        stage.setScene( scene );
        stage.setX( 5 );
        stage.setY( 5 );
        stage.show();
    }

    public void endTurn(ActionEvent event) throws IOException{
        economyEngine.endTurn();
        refreshGui();
    }

    public void displayCurrentDay(int day) {
        currentDayLabel.setText("Current day: " + day);
    }

    public void goToBattle(ActionEvent event) throws IOException {
        EcoBattleConverter converter = new EcoBattleConverter();
        converter.startBattle(economyEngine.getCurrentPlayer().getEconomyHero(),economyEngine.getCurrentPlayer().getEconomyHero());
    }

    public void goToMap(ActionEvent event) throws IOException {
        EcoBattleConverter converter = new EcoBattleConverter();
        converter.startBattle(economyEngine.getCurrentPlayer().getEconomyHero(),economyEngine.getCurrentPlayer().getEconomyHero());
    }

    public void openEq(ActionEvent event) throws IOException {
        ArtifactFactory artifactFactory = new ArtifactFactory();
        HeroEquipment aHeroEq = new HeroEquipment();
        aHeroEq.addItemToBackpack(artifactFactory.create("helmet","skull helmet"));
        aHeroEq.addItemToBackpack(artifactFactory.create("cape","vampire's cowl"));
        aHeroEq.addItemToBackpack(artifactFactory.create("necklace","pedant of courage"));
        aHeroEq.addItemToBackpack(artifactFactory.create("rightHand","sword of hellfire"));
        aHeroEq.addItemToBackpack(artifactFactory.create("leftHand","sentinel's shield"));

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
