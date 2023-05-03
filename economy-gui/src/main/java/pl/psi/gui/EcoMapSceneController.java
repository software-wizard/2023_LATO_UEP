package pl.psi.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pl.psi.mapElements.Resource;


public class EcoMapSceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label heroNameLabel;

    //placeholder na hero
    public void displayName(String heroName) {
        heroNameLabel.setText("Hero name: " + heroName);
    }

    public void displayResources(Resource[] resource) {
        //String reusourceName;
        //heroNameLabel.setText(resource.ResourceType[0] + "Hero name: " + heroName);
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
