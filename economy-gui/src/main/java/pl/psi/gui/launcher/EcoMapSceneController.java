package pl.psi.gui.launcher;

import java.io.IOException;
import java.util.List;

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
import pl.psi.Hero;
import pl.psi.artifacts.ArtifactFactory;
import pl.psi.gui.inventory.EcoEqSceneController;
import pl.psi.gui.map.MapController;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.HeroEquipment;
import pl.psi.EconomyEngine;
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
    AnchorPane mapPane;


    public void loadEconomyEngine(EconomyEngine economyEngine) {
        this.economyEngine = economyEngine;
    }

    public void refreshGui() throws IOException {
        displayCurrentPlayerName(economyEngine.getCurrentPlayer().getName());
        displayName(economyEngine.getCurrentPlayer().getHeroName());
        displayResources(economyEngine.getCurrentPlayer().getResources());
        displayCurrentDay(economyEngine.getCurrentDay());

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/map.fxml"));
        loader.setController( new MapController(economyEngine) );
        Scene scene = new Scene( loader.load());
//        scene.getStylesheets().add("fxml/map.css");

        Node panePropably = scene.getRoot().getChildrenUnmodifiable().stream().filter(BorderPane.class::isInstance).map(BorderPane.class::cast).findFirst().get().getCenter();
        mapPane.getChildren().add(panePropably);
    }
    //placeholder na heroName, trzeba zmienic na pobieranie z klasy Player (jesli sie da)
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
