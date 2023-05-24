package pl.psi.gui.inventory;

import com.google.common.collect.HashBiMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.psi.EconomyEngine;
import pl.psi.gui.launcher.EcoMapSceneController;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EcoEqSceneController implements Initializable {
    private Stage stageMap;
    private Scene sceneMap;
    private Parent rootMap;
    @FXML
    TextField textField_slot1;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textField_slot1.setText("S");
    }

    public void displayNames(){
        textField_slot1.setText("sdfs");
    }

    public void switchToMap(ActionEvent event) throws IOException {
        FXMLLoader loaderMap = new FXMLLoader(getClass().getClassLoader().getResource("fxml/ecoMapScene.fxml"));
        rootMap = loaderMap.load();

        stageMap = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneMap = new  Scene(rootMap);
        stageMap.setScene( sceneMap );
        stageMap.setX( 5 );
        stageMap.setY( 5 );
        stageMap.show();
    }



    @FXML
    private void putOnSlot1(ActionEvent event) throws IOException {
        // Implement the action for the "Put on" button in slot 1
        displayNames();
    }

    @FXML
    private void putOnSlot2(ActionEvent event) throws IOException {
        // Implement the action for the "Put on" button in slot 2
    }

    @FXML
    private void putOnSlot3(ActionEvent event) throws IOException {
        // Implement the action for the "Put on" button in slot 3
    }
    @FXML
    private void putOnSlot4(ActionEvent event) throws IOException {}

    @FXML
    private void putOnSlot5(ActionEvent event) throws IOException {}
    @FXML
    private void putOnSlot6(ActionEvent event) throws IOException {}
    @FXML
    private void putOnSlot7(ActionEvent event) throws IOException {}
    @FXML
    private void putOnSlot8(ActionEvent event) throws IOException {}
    @FXML
    private void putOnSlot9(ActionEvent event) throws IOException {}
    @FXML
    private void putOnSlot10(ActionEvent event) throws IOException {}
    @FXML
    private void putOnSlot11(ActionEvent event) throws IOException{}
    @FXML
    private void putOnSlot12(ActionEvent event) throws IOException {}
    @FXML
    private void putOnSlot13(ActionEvent event) throws IOException {}
    @FXML
    private void putOnSlot14(ActionEvent event) throws IOException {}
    @FXML
    private void putOnSlot15(ActionEvent event) throws IOException {}
    @FXML
    private void putOnSlot16(ActionEvent event) throws IOException {}
    @FXML
    private void putOnSlot17(ActionEvent event) throws IOException {}
    @FXML
    private void putOnSlot18(ActionEvent event) throws IOException {}
    @FXML
    private void putOnSlot19(ActionEvent event) throws IOException {}
    @FXML
    private void putOnSlot20(ActionEvent event) throws IOException {}
    @FXML
    private void putOnSlot21(ActionEvent event) throws IOException {}
    @FXML
    private void putOnSlot22(ActionEvent event) throws IOException {}
    // Define more dummy methods for the other buttons
    @FXML
    private void removeCape(ActionEvent event) throws IOException {}
    @FXML
    private void removeNecklace(ActionEvent event) throws IOException {}
    @FXML
    private void removeRightHand(ActionEvent event) throws IOException {}
    @FXML
    private void removeLeftHand(ActionEvent event) throws IOException {}
    @FXML
    private void removeTorso(ActionEvent event) throws IOException {}
    @FXML
    private void removeRing(ActionEvent event) throws IOException {}
    @FXML
    private void removeFeet(ActionEvent event) throws IOException {}
    @FXML
    private void removeMiscellaneous(ActionEvent event) throws IOException {}
    @FXML
    private void removeBallista(ActionEvent event) throws IOException {}
    @FXML
    private void removeAmmoCart(ActionEvent event) throws IOException {}
    @FXML
    private void removeFirstAidTent(ActionEvent event) throws IOException {}
    @FXML
    private void removeCatapult(ActionEvent event) throws IOException {}
    @FXML
    private void removeSpellbook(ActionEvent event) throws IOException {}
    @FXML
    private void removeHelmet(ActionEvent event) throws IOException {
        // Implement the action for the "Take off" button for the helmet
    }

}
