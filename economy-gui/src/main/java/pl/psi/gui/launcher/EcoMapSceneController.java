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
import javafx.stage.Stage;
import pl.psi.mapElements.Resource;
import pl.psi.player.Player;
import pl.psi.player.PlayerResources;


public class EcoMapSceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

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
}
