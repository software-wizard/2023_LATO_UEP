package pl.psi.gui.launcher;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import com.google.common.collect.HashBiMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import pl.psi.EconomyEngine;
import pl.psi.hero.EconomyHero;
import pl.psi.player.Player;
import pl.psi.player.PlayerResources;

public class EcoLauncherSceneController implements Initializable
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

    @FXML
    private VBox playerChoiceBoxes;

    LinkedList<Player> players = new LinkedList<>();

    //placeholdery z tablicami z danymi do choiceboxow
    private String[] towns = {"Necropolis", "Tower"};

    private String[] heroes = {"Christian", "Edric"};

    private String[] bonuses = {"bonus1", "bonus2"};

    private EconomyEngine economyEngine;

    // Create a VBox to hold the choice boxes for each player
   public void addPlayers(ActionEvent event) throws Exception {
       // Prompt the user for the number of players
       TextInputDialog numPlayersDialog = new TextInputDialog();
       numPlayersDialog.setTitle("Number of Players");
       numPlayersDialog.setHeaderText(null);
       numPlayersDialog.setContentText("Enter the number of players:");
       Optional<String> numPlayersResult = numPlayersDialog.showAndWait();


       // If the user entered a number, create the specified number of players
       if (numPlayersResult.isPresent()) {
           int numPlayers = Integer.parseInt(numPlayersResult.get());
           for (int i = 1; i <= numPlayers; i++) {
               // Prompt the user for the player's name
               TextInputDialog nameDialog = new TextInputDialog();
               nameDialog.setTitle("Player " + i + " Name");
               nameDialog.setHeaderText(null);
               nameDialog.setContentText("Enter player " + i + " name:");
               Optional<String> nameResult = nameDialog.showAndWait();



               // If the user entered both a name and an age, create a new player and add it to the list
               if (nameResult.isPresent()) {
                   Player player = Player.builder().
                           name(nameResult.get()).
                           resources(PlayerResources.builder()
                                   .wood(30)
                                   .ore(20)
                                   .gold(30000)
                                   .crystal(20)
                                   .gems(20)
                                   .mercury(20)
                                   .sulfur(20)
                                   .build())
                           .build();
                   players.add(player);
               }
           }
       }

       // Create a VBox to hold the choice boxes for each player
       playerChoiceBoxes.setSpacing(10);
       playerChoiceBoxes.setPadding(new Insets(10));

       // Loop through the list of players and create a ChoiceBox for each one
       //TODO ogarnac settery do miast, nazw herosow itd
       for (Player player : players) {
           HBox playerChoiceBox = new HBox();
           playerChoiceBox.setSpacing(10);
           playerChoiceBox.setPadding(new Insets(10));

           ChoiceBox<String> choiceBoxTown = new ChoiceBox<>();
           choiceBoxTown.getItems().addAll(towns);
               choiceBoxTown.setOnAction(e -> {
                   String chosenTown = choiceBoxTown.getValue();
                   players.get(players.indexOf(player)).setTown(chosenTown);
               });

           ChoiceBox<String> choiceBoxHero = new ChoiceBox<>();
           choiceBoxHero.getItems().addAll(heroes);
              choiceBoxHero.setOnAction(e -> {
                String chosenHero = choiceBoxHero.getValue();
                  players.get(players.indexOf(player)).setHeroName(chosenHero);
              });

           ChoiceBox<String> choiceBoxBonus = new ChoiceBox<>();
           choiceBoxBonus.getItems().addAll(bonuses);
          choiceBoxBonus.setOnAction(e -> {
            String chosenBonus = choiceBoxBonus.getValue();
              players.get(players.indexOf(player)).setBonus(chosenBonus);
          });

          player.getResources().setGold(1000);
            player.getResources().setWood(1000);
            player.getResources().setOre(100);
            player.getResources().setMercury(10);
            player.getResources().setSulfur(10);
            player.getResources().setCrystal(10);
            player.getResources().setGems(10);

           playerChoiceBox.getChildren().addAll(choiceBoxTown, choiceBoxHero, choiceBoxBonus);

           Label label = new Label(player.getName() + " options:");
           playerChoiceBoxes.getChildren().addAll(label, playerChoiceBox);
       }
   }
    public void switchToMap(ActionEvent event) throws IOException {

        economyEngine = new EconomyEngine(players, HashBiMap.create());

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/ecoMapScene.fxml"));
        root = loader.load();

        try {
            EcoMapSceneController ecoMapSceneController = loader.getController();
            ecoMapSceneController.loadEconomyEngine(economyEngine);
            ecoMapSceneController.refreshGui();
            //pobieranie danych z economyEngine i ładowanie ich na nowa scene za pierwszym razem
//            ecoMapSceneController.displayCurrentPlayerName(economyEngine.getCurrentPlayer().getName());
//            ecoMapSceneController.displayName(economyEngine.getCurrentPlayer().getHeroName());
//            ecoMapSceneController.displayResources(economyEngine.getCurrentPlayer().getResources());

            //do debugu
            ecoMapSceneController.displayAllPlayersWithProperties(players);
        } catch (Exception e) {
            e.printStackTrace();
        }

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        Screen screen = Screen.getPrimary();
        stage.setX(screen.getVisualBounds().getMinX());
        stage.setY(screen.getVisualBounds().getMinY());
        stage.setWidth(screen.getVisualBounds().getWidth());
        stage.setHeight(screen.getVisualBounds().getHeight());

        stage.setScene( scene );
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playerTownChoiceBox.getItems().addAll(towns);
        playerHeroChoiceBox.getItems().addAll(heroes);
        playerBonusChoiceBox.getItems().addAll(bonuses);

        computerTownChoiceBox.getItems().addAll(towns);
        computerHeroChoiceBox.getItems().addAll(heroes);
        computerBonusChoiceBox.getItems().addAll(bonuses);
    }
}
