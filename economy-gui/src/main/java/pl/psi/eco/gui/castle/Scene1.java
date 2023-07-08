// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco.gui.castle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.eco.creatures.EconomyCreature;
import pl.psi.eco.hero.EconomyHero;
import pl.psi.eco.hero.HeroEquipment;
import pl.psi.eco.hero.HeroStatistics;
import pl.psi.eco.mapElements.Castle;
import pl.psi.eco.player.PlayerResources;

import java.io.IOException;
import java.util.ArrayList;

public class Scene1 extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        //TODO jak już uda się przekazać instację EconomyHero przez kliknięcie w gui mapy w zamek, decyzja o wczytaniu pliku fxml powinna zostać podjęta na podstawie frakcji EconomyHero
        //TODO no i zamiast tworzyć poniższe elementy tylko je przekazać

        Castle playerCastle = new Castle(Castle.FractionType.NECROPOLIS, System.out::println);
        PlayerResources resources = PlayerResources.builder()
                .wood(100)
                .ore(50)
                .gold(50000)
                .mercury(10)
                .sulfur(20)
                .crystal(30)
                .gems(5)
                .build();
        ArrayList<EconomyCreature> army = new ArrayList<EconomyCreature>();
        EconomyHero hero = new EconomyHero(HeroStatistics.builder().build(), Castle.FractionType.NECROPOLIS, HeroEquipment.builder().build(), army);

        String filePath = "";
        if(hero.getFraction().equals(Castle.FractionType.NECROPOLIS)){
            filePath = "Scene1Necropolis.fxml";
        }else if (hero.getFraction().equals(Castle.FractionType.RAMPART)) {
            filePath = "Scene1Rampart.fxml";
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Scene1.class.getResource(filePath));
        Scene scene = new Scene(fxmlLoader.load());

        Scene1Controller controller = fxmlLoader.getController();
        controller.setHero(hero);
        controller.setPlayerCastle(playerCastle);
        controller.setResources(resources);

        stage.setTitle("Heroes III");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}