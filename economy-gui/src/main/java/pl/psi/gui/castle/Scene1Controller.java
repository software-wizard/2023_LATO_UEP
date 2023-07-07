package pl.psi.gui.castle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pl.psi.buildings.RecruitmentBuilding;
import pl.psi.creatures.EconomyCreature;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.HeroEquipment;
import pl.psi.hero.HeroStatistics;
import pl.psi.mapElements.Castle;
import pl.psi.player.PlayerResources;


import java.util.ArrayList;


public class Scene1Controller {

    //    private EconomyHero hero;
//
//    public void setEconomyHero(EconomyHero economyHero) {
//        this.hero = economyHero;
//    }

    private static EconomyHero hero;
    private static Castle playerCastle;
    private static PlayerResources resources;

    public void setHero(EconomyHero hero) {
        this.hero = hero;
    }

    public void setPlayerCastle(Castle playerCastle) {
        this.playerCastle = playerCastle;
    }

    public void setResources(PlayerResources resources) {
        this.resources = resources;
    }

    @FXML
    private Text goldCount;

    @FXML
    private Text unit1Count;

    @FXML
    private Text unit2Count;

    @FXML
    private Text unit3Count;

    @FXML
    private Text unit4Count;

    @FXML
    private Text unit5Count;

    @FXML
    private Text unit6Count;

    @FXML
    private Text unit7Count;


    @FXML
    private ImageView image;

    @FXML
    private ImageView image2;

    private static ArrayList<Text> unitCounts;
    public void addUnitCountTextsToList() {
        unitCounts = new ArrayList<>();
        unitCounts.add(unit1Count);
        unitCounts.add(unit2Count);
        unitCounts.add(unit3Count);
        unitCounts.add(unit4Count);
        unitCounts.add(unit5Count);
        unitCounts.add(unit6Count);
        unitCounts.add(unit7Count);
    }

//    static Castle playerCastle = new Castle(Castle.FractionType.NECROPOLIS);
//    static PlayerResources resources = PlayerResources.builder()
//            .wood(100)
//            .ore(50)
//            .gold(50000)
//            .mercury(10)
//            .sulfur(20)
//            .crystal(30)
//            .gems(5)
//            .build();
//    static ArrayList<EconomyCreature> army = new ArrayList<EconomyCreature>();
//    static EconomyHero hero = new EconomyHero(HeroStatistics.builder().build(), Castle.FractionType.NECROPOLIS, HeroEquipment.builder().build(), army);

    static int currentBuyingUnit;

    public static int getCurrentBuyingUnit() {
        return currentBuyingUnit;
    }

    public void setCurrentBuyingUnit(int currentBuyingUnit) {
        Scene1Controller.currentBuyingUnit = currentBuyingUnit;
    }

    public static void buyCreatures(int currentBuyingUnit, int amount){
        hero.addCreaturesToArmy((RecruitmentBuilding) playerCastle.getBuildingsOwned().get(currentBuyingUnit), amount, resources);
        for (int i = 0; i<hero.getHeroArmy().size(); i++){
            if(hero.getHeroArmy().get(i).getName() != null){
                System.out.println(hero.getHeroArmy().get(i).getName()+" "+hero.getHeroArmy().get(i).getAmount());
            }
        }
    }

    @FXML
    void EnterRecruitment1(MouseEvent event) {
        RecruitmentBuilding building = (RecruitmentBuilding) playerCastle.getBuildingsOwned().get(1);
        String goldCost = String.valueOf(building.getCreaturesToRecruit().getGoldCost());
        showRecruitingScene(goldCost, 1, hero.fraction);
        setCurrentBuyingUnit(1);
        addUnitCountTextsToList();
        refreshUnitAmounts();
        refreshResourceAmounts();
    }


    @FXML
    void EnterRecruitment2(MouseEvent event)  {
        RecruitmentBuilding building = (RecruitmentBuilding) playerCastle.getBuildingsOwned().get(2);
        String goldCost = String.valueOf(building.getCreaturesToRecruit().getGoldCost());
        showRecruitingScene(goldCost, 2, hero.fraction);
        setCurrentBuyingUnit(2);
        refreshUnitAmounts();
        refreshResourceAmounts();
    }

    @FXML
    void EnterRecruitment3(MouseEvent event) {
        RecruitmentBuilding building = (RecruitmentBuilding) playerCastle.getBuildingsOwned().get(3);
        String goldCost = String.valueOf(building.getCreaturesToRecruit().getGoldCost());
        showRecruitingScene(goldCost, 3, hero.fraction);
        setCurrentBuyingUnit(3);
        refreshUnitAmounts();
        refreshResourceAmounts();
    }

    @FXML
    void EnterRecruitment4(MouseEvent event) {
        RecruitmentBuilding building = (RecruitmentBuilding) playerCastle.getBuildingsOwned().get(4);
        String goldCost = String.valueOf(building.getCreaturesToRecruit().getGoldCost());
        showRecruitingScene(goldCost, 4, hero.fraction);
        setCurrentBuyingUnit(4);
        refreshUnitAmounts();
        refreshResourceAmounts();
    }

    @FXML
    void EnterRecruitment5(MouseEvent event) {
        RecruitmentBuilding building = (RecruitmentBuilding) playerCastle.getBuildingsOwned().get(5);
        String goldCost = String.valueOf(building.getCreaturesToRecruit().getGoldCost());
        showRecruitingScene(goldCost, 5, hero.fraction);
        setCurrentBuyingUnit(5);
        refreshUnitAmounts();
        refreshResourceAmounts();
    }

    @FXML
    void EnterRecruitment6(MouseEvent event) {
        RecruitmentBuilding building = (RecruitmentBuilding) playerCastle.getBuildingsOwned().get(6);
        String goldCost = String.valueOf(building.getCreaturesToRecruit().getGoldCost());
        showRecruitingScene(goldCost, 6, hero.fraction);
        setCurrentBuyingUnit(6);
        refreshUnitAmounts();
        refreshResourceAmounts();
    }

    @FXML
    void EnterRecruitment7(MouseEvent event) {
        RecruitmentBuilding building = (RecruitmentBuilding) playerCastle.getBuildingsOwned().get(7);
        String goldCost = String.valueOf(building.getCreaturesToRecruit().getGoldCost());
        showRecruitingScene(goldCost, 7, hero.fraction);
        setCurrentBuyingUnit(7);
        refreshResourceAmounts();
    }

    void showRecruitingScene(String goldCost, int creatureNumber, Castle.FractionType type){
        try{
            String filePath = "";
            if(type.equals(Castle.FractionType.NECROPOLIS)){
                filePath = "Scene2Necropolis.fxml";
            }else if (type.equals(Castle.FractionType.RAMPART)) {
                filePath = "Scene2Rampart.fxml";
            }
            FXMLLoader fxmlLoader = new FXMLLoader(Scene1.class.getResource(filePath));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Recruiting");
            stage.setScene(scene);
            stage.show();
            Scene2Controller controller = fxmlLoader.getController();
            controller.setUnitCost(goldCost);
            controller.setVisible(creatureNumber);
          //  controller.setRefreshScene1Function(this::refreshUnitAmounts);

        }catch(Exception e){
            System.out.println("exception");
        }
    }

    public static void refreshUnitAmounts(){
        int x;
        if(!hero.getHeroArmy().isEmpty()){
            for (int i = 0; i < hero.getHeroArmy().size(); i++) {
                x = hero.getHeroArmy().get(i).getTier();
                unitCounts.get(x-1).setText(String.valueOf(hero.getHeroArmy().get(i).getAmount()));
            }
        }
    }

    public void refreshResourceAmounts(){
        goldCount.setText(String.valueOf(resources.getGold()));
    }


    public void EnterFort(MouseEvent mouseEvent) {
        showUnitsScene(hero.fraction);
        refreshUnitAmounts();
    }

    void showUnitsScene(Castle.FractionType type){

        try{
            String filePath = "";
            if(type.equals(Castle.FractionType.NECROPOLIS)){
                filePath = "Scene3Necropolis.fxml";
            }else if (type.equals(Castle.FractionType.RAMPART)) {
                filePath = "Scene3Rampart.fxml";
            }
            FXMLLoader fxmlLoader = new FXMLLoader(Scene1.class.getResource(filePath));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Recruiting");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            System.out.println("exception");
        }
    }

//    public void EnterHall(MouseEvent mouseEvent) {
//    }

}
