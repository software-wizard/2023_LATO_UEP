// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco.gui.castle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class Scene3Controller {

//    private EconomyHero hero;
//
//    public void setEconomyHero(EconomyHero economyHero) {
//        this.hero = economyHero;
//    }

    @FXML
    private Button buyButton;

    @FXML
    private TextField unit1count;

    @FXML
    private TextField unit2count;

    @FXML
    private TextField unit3count;

    @FXML
    private TextField unit4count;

    @FXML
    private TextField unit5count;

    @FXML
    private TextField unit6count;

    @FXML
    private TextField unit7count;



    @FXML
    void buyUnits(ActionEvent event) {
        ArrayList<TextField> textFieldList = new ArrayList<>();
        textFieldList.add(unit1count);
        textFieldList.add(unit2count);
        textFieldList.add(unit3count);
        textFieldList.add(unit4count);
        textFieldList.add(unit5count);
        textFieldList.add(unit6count);
        textFieldList.add(unit7count);

        int i = 1;
        for(TextField text: textFieldList){
            String recruitAmount = text.getCharacters().toString();
            if(Integer.parseInt(recruitAmount)>0){
                Scene1Controller.buyCreatures(i, Integer.parseInt(recruitAmount));
                Scene1Controller.refreshUnitAmounts();
            }
            i++;
        }
    }

}
