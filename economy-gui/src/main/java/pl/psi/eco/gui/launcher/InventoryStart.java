//package pl.psi.gui;
//import javafx.application.Application;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.layout.*;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//import pl.psi.hero.Hero;
//import pl.psi.hero.HeroEquipment;
//import pl.psi.hero.HeroStatistics;
//import pl.psi.artifacts.Artifact;
//import pl.psi.mapElements.MapElement;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//
//public class InventoryStart extends Application{
//    private Hero hero;
//
//    // Define a constructor that takes a Hero parameter
//    public InventoryStart(Hero hero) {
//        this.hero = hero;
//    }
//    // Konstruktor bezargumentowy
//    public InventoryStart() {
//        super();
//    }
//    @Override
//    public void start(Stage primaryStage) {
//
//        ArrayList<Artifact> heroBackpack = hero.getHeroEquipment().getHeroBackpack();
//        HashMap<String, Artifact> heroInventory = hero.getHeroEquipment().getHeroInventory();
//
//
//        List<String> types = Arrays.asList("helmet", "cape","necklace","rightHand","leftHand","torso","ring","feet","miscellaneous","ballista","ammoCart","firstAidTent","catapult","spellBook");
//
//        // create the upper part with 14 square cells and text descriptions
//        GridPane upperGrid = new GridPane();
//        upperGrid.setHgap(10);
//
//        // add the "Inventory" text above the first row
//        Text inventoryText = new Text("Inventory");
//        upperGrid.add(inventoryText, 0, 0, 14, 1);
//
//        for (int i = 0; i < 14; i++) {
//            StackPane cell = new StackPane();
//            cell.setPrefSize(50, 50);
//            cell.setStyle("-fx-border-color: black;");
//            Text textInventory;
//            if (heroInventory.get(types.get(i))==null){
//                textInventory = new Text(types.get(i));
//            }
//            else {
//                textInventory = new Text(heroInventory.get(types.get(i)).getType());
//            }
//            cell.getChildren().addAll(new Rectangle(50, 50, Color.TRANSPARENT), textInventory);
//            upperGrid.add(cell, i, 1);
//        }
//
//        // create the lower part with 14 square cells and two text descriptions
//        GridPane lowerGrid = new GridPane();
//        lowerGrid.setHgap(10);
//        lowerGrid.setVgap(10);
//        Text backpackText = new Text("Backpack");
//        lowerGrid.add(backpackText, 0, 0, 14, 1);
//        for (int i = 0; i < 14; i++) {
//            StackPane cell = new StackPane();
//            cell.setPrefSize(50, 50);
//            cell.setStyle("-fx-border-color: black;");
//            lowerGrid.add(cell, i % 14, i / 14 + 1);
//        }
//
//        // add a second row to the lower part with the same number of cells
//        lowerGrid.addRow(2);
//        for (int i = 0; i < 14; i++) {
//            StackPane cell = new StackPane();
//            cell.setPrefSize(50, 50);
//            cell.setStyle("-fx-border-color: black;");
//            lowerGrid.add(cell, i % 14, i / 14 + 2);
//        }
//
//        // combine the upper and lower parts into a single layout
//        GridPane root = new GridPane();
//        root.setVgap(10);
//        root.add(upperGrid, 0, 0);
//        root.add(lowerGrid, 0, 1);
//
//        // create a scene and set it as the primary stage's scene
//        Scene scene = new Scene(root);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        Hero hero = new Hero(HeroStatistics.builder().build(), HeroEquipment.builder().heroBackpack(new ArrayList<>()).heroInventory(new HashMap<>()).build());
//        hero.addArtifactToBackpack(new Artifact("sd", "sd"));
//
//        // Create an instance of InventoryStart and pass the Hero object to its constructor
//        InventoryStart app = new InventoryStart(hero);
//
//        // Launch the application
//        launch(args);
//    }
//
//}
