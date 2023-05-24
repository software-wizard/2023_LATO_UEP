package pl.psi.gui.launcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pl.psi.gui.inventory.EcoEqSceneController;
import pl.psi.hero.HeroEquipment;
import pl.psi.mapElements.Artifact;
import pl.psi.player.Player;


public class EcoMapSceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private Stage stageInventory;
    private Scene sceneInventory;
    private Parent rootInventory;

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

    //placeholder na heroName, trzeba zmienic na pobieranie z klasy Player (jesli sie da)
    public void displayName(String heroName) {
        heroNameLabel.setText("Hero name: " + heroName);
    }

    public void displayAllPlayersWithProperties(List<Player> players) {
        for (Player player : players) {
            System.out.println(player.getName() + " " + player.getTown() + " " + player.getHeroName() + " " + player.getBonus());
        }
    }

    public void displayResources(List<Player> players) {
        goldLabel.setText("Gold: "+ players.get(0).getResources().getGold());
        woodLabel.setText("Wood: "+ players.get(0).getResources().getWood());
        oreLabel.setText("Ore: "+ players.get(0).getResources().getOre());
        crystalLabel.setText("Crystal: "+ players.get(0).getResources().getCrystal());
        sulfurLabel.setText("Sulfur: "+ players.get(0).getResources().getSulfur());
        mercuryLabel.setText("Mercury: "+ players.get(0).getResources().getMercury());
        gemsLabel.setText("Gems: "+ players.get(0).getResources().getGems());
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

    public void openEq(ActionEvent event) throws IOException {

        HeroEquipment aHeroEq = new HeroEquipment();
        aHeroEq.addItemToBackpack(new Artifact("helmet","skull helmet"));
        aHeroEq.addItemToBackpack(new Artifact("torso","big torso"));
        aHeroEq.addItemToBackpack(new Artifact("torso","wood torso"));
        aHeroEq.addItemToBackpack(new Artifact("feet","sandały"));

        FXMLLoader loaderInventory = new FXMLLoader(getClass().getClassLoader().getResource("fxml/eq.fxml"));
        rootInventory = loaderInventory.load();

        try {
            EcoEqSceneController ecoEqSceneController = loaderInventory.getController();
            ecoEqSceneController.refreshEq(aHeroEq);
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
