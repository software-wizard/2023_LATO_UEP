package pl.psi.gui.inventory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pl.psi.hero.HeroEquipment;
import pl.psi.artifacts.Artifact;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class EcoEqSceneController implements Initializable{
    private Stage stageMap;
    private Scene sceneMap;
    private Parent rootMap;

    @FXML
    TextField textField_slot1;
    @FXML
    TextField textField_slot2;
    @FXML
    TextField textField_slot3;
    @FXML
    TextField textField_slot4;
    @FXML
    TextField textField_slot5;
    @FXML
    TextField textField_slot6;
    @FXML
    TextField textField_slot7;
    @FXML
    TextField textField_slot8;
    @FXML
    TextField textField_slot9;
    @FXML
    TextField textField_slot10;
    @FXML
    TextField textField_slot11;
    @FXML
    TextField textField_slot12;
    @FXML
    TextField textField_slot13;
    @FXML
    TextField textField_slot14;
    @FXML
    TextField textField_slot15;
    @FXML
    TextField textField_slot16;
    @FXML
    TextField textField_slot17;
    @FXML
    TextField textField_slot18;
    @FXML
    TextField textField_slot19;
    @FXML
    TextField textField_slot20;
    @FXML
    TextField textField_slot21;
    @FXML
    TextField textField_slot22;
    @FXML
    TextField textField_helmet;
    @FXML
    TextField textField_cape;
    @FXML
    TextField textField_necklace;
    @FXML
    TextField textField_rightHand;
    @FXML
    TextField textField_leftHand;
    @FXML
    TextField textField_torso;
    @FXML
    TextField textField_ring;
    @FXML
    TextField textField_feet;
    @FXML
    TextField textField_miscellaneous;
    @FXML
    TextField textField_ballista;
    @FXML
    TextField textField_ammoCart;
    @FXML
    TextField textField_firstAidTent;
    @FXML
    TextField textField_catapult;
    @FXML
    TextField textField_spellbook;
    @FXML
    ImageView slotImage1;
    @FXML
    ImageView slotImage2;
    @FXML
    ImageView slotImage3;
    @FXML
    ImageView slotImage4;
    @FXML
    ImageView slotImage5;
    @FXML
    ImageView slotImage6;
    @FXML
    ImageView slotImage7;
    @FXML
    ImageView slotImage8;
    @FXML
    ImageView slotImage9;
    @FXML
    ImageView slotImage10;
    @FXML
    ImageView slotImage11;
    @FXML
    ImageView slotImage12;
    @FXML
    ImageView slotImage13;
    @FXML
    ImageView slotImage14;
    @FXML
    ImageView slotImage15;
    @FXML
    ImageView slotImage16;
    @FXML
    ImageView slotImage17;
    @FXML
    ImageView slotImage18;
    @FXML
    ImageView slotImage19;
    @FXML
    ImageView slotImage20;
    @FXML
    ImageView slotImage21;
    @FXML
    ImageView slotImage22;

    @FXML
    ImageView helmetImage;
    @FXML
    ImageView capeImage;
    @FXML
    ImageView necklaceImage;
    @FXML
    ImageView rightHandImage;
    @FXML
    ImageView leftHandImage;
    @FXML
    ImageView torsoImage;
    @FXML
    ImageView ringImage;
    @FXML
    ImageView feetImage;
    @FXML
    ImageView miscellaneousImage;
    @FXML
    ImageView ballistaImage;
    @FXML
    ImageView ammoCartImage;
    @FXML
    ImageView firstAidTentImage;
    @FXML
    ImageView catapultImage;
    @FXML
    ImageView spellbookImage;

    HashMap<String,TextField> eq_slots ;
    HashMap<String,ImageView> eq_slots_images ;
    ArrayList<TextField> backpack_slots;
    ArrayList<ImageView> backpack_slots_images;

    HeroEquipment heroEq;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        eq_slots = new HashMap<String,TextField>();
        eq_slots.put("helmet", textField_helmet);
        eq_slots.put("cape", textField_cape);
        eq_slots.put("necklace", textField_necklace);
        eq_slots.put("rightHand", textField_rightHand);
        eq_slots.put("leftHand", textField_leftHand);
        eq_slots.put("torso", textField_torso);
        eq_slots.put("ring", textField_ring);
        eq_slots.put("feet", textField_feet);
        eq_slots.put("miscellaneous", textField_miscellaneous);
        eq_slots.put("ballista", textField_ballista);
        eq_slots.put("ammoCart", textField_ammoCart);
        eq_slots.put("firstAidTent", textField_firstAidTent);
        eq_slots.put("catapult", textField_catapult);
        eq_slots.put("spellbook", textField_spellbook);
        backpack_slots = new ArrayList<TextField>();
        backpack_slots.add(textField_slot1);
        backpack_slots.add(textField_slot2);
        backpack_slots.add(textField_slot3);
        backpack_slots.add(textField_slot4);
        backpack_slots.add(textField_slot5);
        backpack_slots.add(textField_slot6);
        backpack_slots.add(textField_slot7);
        backpack_slots.add(textField_slot8);
        backpack_slots.add(textField_slot9);
        backpack_slots.add(textField_slot10);
        backpack_slots.add(textField_slot11);
        backpack_slots.add(textField_slot12);
        backpack_slots.add(textField_slot13);
        backpack_slots.add(textField_slot14);
        backpack_slots.add(textField_slot15);
        backpack_slots.add(textField_slot16);
        backpack_slots.add(textField_slot17);
        backpack_slots.add(textField_slot18);
        backpack_slots.add(textField_slot19);
        backpack_slots.add(textField_slot20);
        backpack_slots.add(textField_slot21);
        backpack_slots.add(textField_slot22);
        eq_slots_images = new HashMap<>();
        eq_slots_images.put("helmet", helmetImage);
        eq_slots_images.put("cape", capeImage);
        eq_slots_images.put("necklace", necklaceImage);
        eq_slots_images.put("rightHand", rightHandImage);
        eq_slots_images.put("leftHand", leftHandImage);
        eq_slots_images.put("torso", torsoImage);
        eq_slots_images.put("ring", ringImage);
        eq_slots_images.put("feet", feetImage);
        eq_slots_images.put("miscellaneous", miscellaneousImage);
        eq_slots_images.put("ballista", ballistaImage);
        eq_slots_images.put("ammoCart", ammoCartImage);
        eq_slots_images.put("firstAidTent", firstAidTentImage);
        eq_slots_images.put("catapult", catapultImage);
        eq_slots_images.put("spellbook", spellbookImage);
        backpack_slots_images = new ArrayList<>();
        backpack_slots_images.add(slotImage1);
        backpack_slots_images.add(slotImage2);
        backpack_slots_images.add(slotImage3);
        backpack_slots_images.add(slotImage4);
        backpack_slots_images.add(slotImage5);
        backpack_slots_images.add(slotImage6);
        backpack_slots_images.add(slotImage7);
        backpack_slots_images.add(slotImage8);
        backpack_slots_images.add(slotImage9);
        backpack_slots_images.add(slotImage10);
        backpack_slots_images.add(slotImage11);
        backpack_slots_images.add(slotImage12);
        backpack_slots_images.add(slotImage13);
        backpack_slots_images.add(slotImage14);
        backpack_slots_images.add(slotImage15);
        backpack_slots_images.add(slotImage16);
        backpack_slots_images.add(slotImage17);
        backpack_slots_images.add(slotImage18);
        backpack_slots_images.add(slotImage19);
        backpack_slots_images.add(slotImage20);
        backpack_slots_images.add(slotImage21);
        backpack_slots_images.add(slotImage22);
    }

    public void refreshEq(HeroEquipment aHeroEq){
        heroEq = aHeroEq;
        HashMap<String, Artifact> heroInventory = aHeroEq.getHeroInventory();
        ArrayList<Artifact> heroBackpack = aHeroEq.getHeroBackpack();

        //fill backpack items
        for (int i = 0; i < 22; i++) {
            try {
                Artifact artifact = heroBackpack.get(i);
                backpack_slots.get(i).setText(artifact.getName());
                backpack_slots_images.get(i).setImage(artifact.getArtifactStatistics().getImage());
            } catch (IndexOutOfBoundsException e) {
                backpack_slots.get(i).setText("empty");
                backpack_slots_images.get(i).setImage(new Image(getClass().getResource("/emptyField.png").toString()) );
            }

        }
        //fill eq items
        for (String key : heroInventory.keySet()) {
            try {
                Artifact artifact = heroInventory.get(key);
                eq_slots.get(key).setText(artifact.getName());
                eq_slots_images.get(key).setImage(artifact.getArtifactStatistics().getImage());
            } catch (NullPointerException e) {
                eq_slots.get(key).setText("empty");
                eq_slots_images.get(key).setImage(new Image(getClass().getResource("/emptyField.png").toString()) );
            }
        }
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


    private void putOnArtifact(Integer backpack_slots_ID){
        try {
            ArrayList<Artifact> heroBackpack = heroEq.getHeroBackpack();
            heroEq.moveFromBackpackToInventory(heroBackpack.get(backpack_slots_ID));
            refreshEq(heroEq);
        }catch(IndexOutOfBoundsException e){
            // Handle the exception by showing an alert dialog
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Item to Put On");
            alert.setHeaderText(null);
            alert.setContentText("There is no item to put on.");
            alert.showAndWait();
        }
    }
    private void takeOffArtifact(String artifactType){
        try {
            HashMap<String, Artifact> heroInventory = heroEq.getHeroInventory();
            heroEq.moveFromInventoryToBackpack(heroInventory.get(artifactType));
            refreshEq(heroEq);
        }catch(IndexOutOfBoundsException e){
            // Handle the exception by showing an alert dialog
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Item to Take Off");
            alert.setHeaderText(null);
            alert.setContentText("There is no item to take off.");
            alert.showAndWait();
        }
    }

    @FXML
    private void putOnSlot1(ActionEvent event) throws IOException {putOnArtifact(0);}
    @FXML
    private void putOnSlot2(ActionEvent event) throws IOException {putOnArtifact(1);}
    @FXML
    private void putOnSlot3(ActionEvent event) throws IOException {putOnArtifact(2);}
    @FXML
    private void putOnSlot4(ActionEvent event) throws IOException {putOnArtifact(3);}

    @FXML
    private void putOnSlot5(ActionEvent event) throws IOException {putOnArtifact(4);}
    @FXML
    private void putOnSlot6(ActionEvent event) throws IOException {putOnArtifact(5);}
    @FXML
    private void putOnSlot7(ActionEvent event) throws IOException {putOnArtifact(6);}
    @FXML
    private void putOnSlot8(ActionEvent event) throws IOException {putOnArtifact(7);}
    @FXML
    private void putOnSlot9(ActionEvent event) throws IOException {putOnArtifact(8);}
    @FXML
    private void putOnSlot10(ActionEvent event) throws IOException {putOnArtifact(9);}
    @FXML
    private void putOnSlot11(ActionEvent event) throws IOException{putOnArtifact(10);}
    @FXML
    private void putOnSlot12(ActionEvent event) throws IOException {putOnArtifact(11);}
    @FXML
    private void putOnSlot13(ActionEvent event) throws IOException {putOnArtifact(12);}
    @FXML
    private void putOnSlot14(ActionEvent event) throws IOException {putOnArtifact(13);}
    @FXML
    private void putOnSlot15(ActionEvent event) throws IOException {putOnArtifact(14);}
    @FXML
    private void putOnSlot16(ActionEvent event) throws IOException {putOnArtifact(15);}
    @FXML
    private void putOnSlot17(ActionEvent event) throws IOException {putOnArtifact(16);}
    @FXML
    private void putOnSlot18(ActionEvent event) throws IOException {putOnArtifact(17);}
    @FXML
    private void putOnSlot19(ActionEvent event) throws IOException {putOnArtifact(18);}
    @FXML
    private void putOnSlot20(ActionEvent event) throws IOException {putOnArtifact(19);}
    @FXML
    private void putOnSlot21(ActionEvent event) throws IOException {putOnArtifact(20);}
    @FXML
    private void putOnSlot22(ActionEvent event) throws IOException {putOnArtifact(21);}
    @FXML
    private void removeCape(ActionEvent event) throws IOException {takeOffArtifact("cape");}
    @FXML
    private void removeNecklace(ActionEvent event) throws IOException {takeOffArtifact("necklace");}
    @FXML
    private void removeRightHand(ActionEvent event) throws IOException {takeOffArtifact("rightHand");}
    @FXML
    private void removeLeftHand(ActionEvent event) throws IOException {takeOffArtifact("leftHand");}
    @FXML
    private void removeTorso(ActionEvent event) throws IOException {takeOffArtifact("torso");}
    @FXML
    private void removeRing(ActionEvent event) throws IOException {takeOffArtifact("ring");}
    @FXML
    private void removeFeet(ActionEvent event) throws IOException {takeOffArtifact("feet");}
    @FXML
    private void removeMiscellaneous(ActionEvent event) throws IOException {takeOffArtifact("miscellaneous");}
    @FXML
    private void removeBallista(ActionEvent event) throws IOException {takeOffArtifact("ballista");}
    @FXML
    private void removeAmmoCart(ActionEvent event) throws IOException {takeOffArtifact("ammoCart");}
    @FXML
    private void removeFirstAidTent(ActionEvent event) throws IOException {takeOffArtifact("firstAidTent");}
    @FXML
    private void removeCatapult(ActionEvent event) throws IOException {takeOffArtifact("catapult");}
    @FXML
    private void removeSpellbook(ActionEvent event) throws IOException {takeOffArtifact("spellbook");}
    @FXML
    private void removeHelmet(ActionEvent event) throws IOException {takeOffArtifact("helmet");}

}
