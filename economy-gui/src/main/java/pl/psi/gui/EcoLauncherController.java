package pl.psi.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class EcoLauncherController implements Initializable
{

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ChoiceBox<String> playerTownChoiceBox;
    @FXML
    private ChoiceBox<String> playerHeroChoiceBox;
    @FXML
    private ChoiceBox<String> playerBonusChoiceBox;
    @FXML
    private ChoiceBox<String> computerTownChoiceBox;
    @FXML
    private ChoiceBox<String> computerHeroChoiceBox;
    @FXML
    private ChoiceBox<String> computerBonusChoiceBox;

    private String[] towns = {"Necropolis", "Rampart", "Tower", "Bydgoszcz"};

    private String[] heroes = {"Christian", "Edric", "Bonus BGC", "Valeska"};

    private String[] bonuses = {"bonus1", "bonus2", "bonus3", "bonus4"};

    public void switchToMap(ActionEvent event) throws IOException {
        //tu bedzie jakas metoda do przeslania wybranych frakcji(miast), herosów i bonusow do silnika gry
        //runGame.getChosenParameters(playerBonusChoiceBox,.....);

        root = FXMLLoader.load((getClass().getClassLoader().getResource("fxml/ecoMap.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new  Scene(root);
        stage.setScene( scene );
        stage.setX( 5 );
        stage.setY( 5 );
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //pobieranie tych danych bedzie z jakiegos Jsona lub innego pliku z bohaterami, frakcjami i bonusami
        playerTownChoiceBox.getItems().addAll(towns);
        playerHeroChoiceBox.getItems().addAll(heroes);
        playerBonusChoiceBox.getItems().addAll(bonuses);

        computerTownChoiceBox.getItems().addAll(towns);
        computerHeroChoiceBox.getItems().addAll(heroes);
        computerBonusChoiceBox.getItems().addAll(bonuses);
    }
}
