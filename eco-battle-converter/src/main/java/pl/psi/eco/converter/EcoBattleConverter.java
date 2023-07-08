// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.psi.battle.Hero;
import pl.psi.battle.creatures.Creature;
import pl.psi.battle.creatures.NecropolisFactory;
import pl.psi.eco.gui.MainBattleController;
import pl.psi.eco.hero.EconomyHero;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EcoBattleConverter
{

    public static void startBattle(final EconomyHero aPlayer1, final EconomyHero aPlayer2 )
    {
        Scene scene = null;
        try
        {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation( EcoBattleConverter.class.getClassLoader()
                    .getResource( "fxml/main-battle.fxml" ) );
            loader.setController( new MainBattleController( convert( aPlayer1 ), convert( aPlayer2 ) ) );
            scene = new Scene( loader.load() );
            final Stage aStage = new Stage();
            aStage.setScene( scene );
            aStage.setX( 5 );
            aStage.setY( 5 );
            aStage.show();
        }
        catch( final IOException aE )
        {
            aE.printStackTrace();
        }
    }

    public static Hero convert(final EconomyHero aPlayer1 )
    {
        final List<Creature> creatures = new ArrayList<>();
        final NecropolisFactory factory = new NecropolisFactory();
        aPlayer1.getCreatures()
                .forEach( ecoCreature -> creatures.add( factory.create( ecoCreature.isUpgraded(),
                        ecoCreature.getTier(), ecoCreature.getAmount() ) ) );
        return new Hero( creatures );
    }
}
