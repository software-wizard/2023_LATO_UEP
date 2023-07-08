// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco.gui;

import java.io.IOException;
import java.util.List;

import pl.psi.battle.Hero;
import pl.psi.battle.creatures.Creature;
import pl.psi.battle.creatures.NecropolisFactory;
import pl.psi.battle.warmachines.WarMachine;
import pl.psi.eco.WarMachines.WarMachineStatistic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.battle.warmachines.WarMachineFactory;

public class Start extends Application
{

    public Start()
    {

    }

    static void main( final String[] args )
    {
        launch( args );
    }

    @Override
    public void start( final Stage primaryStage )
    {
        Scene scene = null;
        try
        {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation( Start.class.getClassLoader()
                .getResource( "fxml/main-battle.fxml" ) );
            loader.setController( new MainBattleController( createP1(), createP2() ) );
            scene = new Scene( loader.load() );
            primaryStage.setScene( scene );
            primaryStage.setX( 5 );
            primaryStage.setY( 5 );
            primaryStage.show();
        }
        catch( final IOException aE )
        {
            aE.printStackTrace();
        }
    }

    private Hero createP2()
    {
        Creature creature1 = new NecropolisFactory().create( true, 2, 10);
        WarMachine warMachine1 = new WarMachineFactory().create(WarMachineStatistic.BALLISTA, 1, 2);
        WarMachine warMachine2 = new WarMachineFactory().create(WarMachineStatistic.FIRST_AID_TENT, 0, 1);

        return new Hero(List.of(creature1), List.of(warMachine1, warMachine2));
    }

    private Hero createP1()
    {
        Creature creature1 = new NecropolisFactory().create( false, 2, 15);
        Creature creature2 = new NecropolisFactory().create( true, 3, 3 );
        WarMachine warMachine1 = new WarMachineFactory().create(WarMachineStatistic.CATAPULT, 1, 2);
        WarMachine warMachine2 = new WarMachineFactory().create(WarMachineStatistic.FIRST_AID_TENT, 1, 1);

        return new Hero(List.of(creature1, creature2), List.of(warMachine1, warMachine2));
    }

}
