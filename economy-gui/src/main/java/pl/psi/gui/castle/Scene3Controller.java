package pl.psi.gui.castle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pl.psi.buildings.RecruitmentBuilding;
import pl.psi.creatures.EconomyCreature;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.HeroEquipment;
import pl.psi.hero.HeroStatistics;
import pl.psi.mapElements.Castle;
import pl.psi.player.PlayerResources;

import java.util.ArrayList;

public class Scene3Controller {

//    private EconomyHero hero;
//
//    public void setEconomyHero(EconomyHero economyHero) {
//        this.hero = economyHero;
//    }

    @FXML
    private Text goldNumber;

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
                buyCreatures(i, Integer.parseInt(recruitAmount));
            }
            i++;
        }
        goldNumber.setText(String.valueOf(resources.getGold()));
    }

    public static void buyCreatures(int currentBuyingUnit, int amount){
        hero.addCreaturesToArmy((RecruitmentBuilding) playerCastle.getBuildingsOwned().get(currentBuyingUnit), amount, resources);
        for (int i = 0; i<hero.getHeroArmy().size(); i++){
            if(hero.getHeroArmy().get(i).getName() != null){
                System.out.println(hero.getHeroArmy().get(i).getName()+" "+hero.getHeroArmy().get(i).getAmount());
            }
        }
    }


    static Castle playerCastle = new Castle(Castle.FractionType.RAMPART);
    static PlayerResources resources = PlayerResources.builder()
            .wood(100)
            .ore(50)
            .gold(50000)
            .mercury(10)
            .sulfur(20)
            .crystal(30)
            .gems(5)
            .build();
    static ArrayList<EconomyCreature> army = new ArrayList<EconomyCreature>();
    static EconomyHero hero = new EconomyHero(HeroStatistics.builder().build(), army, HeroEquipment.builder().build());


}
