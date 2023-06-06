package pl.psi.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
//import pl.psi.gui.Scene1Controller;

public class Scene2Controller {
    @FXML
    private Button purchaseButton;

    @FXML
    private TextField recruitAmountField;

    @FXML
    private ImageView recruitingUnit1;

    @FXML
    private ImageView recruitingUnit2;

    @FXML
    private ImageView recruitingUnit3;

    @FXML
    private ImageView recruitingUnit4;

    @FXML
    private ImageView recruitingUnit5;

    @FXML
    private ImageView recruitingUnit6;

    @FXML
    private ImageView recruitingUnit7;

    @FXML
    private Text unitCost;

    @FXML
    void exitWindow(MouseEvent event) {
    }

    @FXML
    void recruitCreatures(MouseEvent event) {
        String recruitAmount = recruitAmountField.getCharacters().toString();
        Scene1Controller.buyCreatures(Scene1Controller.getCurrentBuyingUnit(), Integer.parseInt(recruitAmount));
    }


    public void setUnitCost(String s){
        this.unitCost.setText(s);
    }

    public void setVisible(int i){
        switch (i) {
            case 1 -> recruitingUnit1.setVisible(true);
            case 2 -> recruitingUnit2.setVisible(true);
            case 3 -> recruitingUnit3.setVisible(true);
            case 4 -> recruitingUnit4.setVisible(true);
            case 5 -> recruitingUnit5.setVisible(true);
            case 6 -> recruitingUnit6.setVisible(true);
            case 7 -> recruitingUnit7.setVisible(true);
        }

    }

}

