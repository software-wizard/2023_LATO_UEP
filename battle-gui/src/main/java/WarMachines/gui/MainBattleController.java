package WarMachines.gui;

import WarMachines.WarMachine;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pl.psi.GameEngine;
import pl.psi.Point;
import pl.psi.creatures.Creature;
import pl.psi.creatures.NewHeroPrototype;
import pl.psi.NewGameEnginePrototype;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Optional;

public class MainBattleController implements PropertyChangeListener
{
    private final NewGameEnginePrototype gameEngine;
    @FXML
    private GridPane gridMap;
    @FXML
    private Button passButton;

    public MainBattleController(final NewHeroPrototype aHero1, final NewHeroPrototype aHero2 )
    {
        gameEngine = new NewGameEnginePrototype( aHero1, aHero2 );
    }

    @FXML
    private void initialize()
    {
        refreshGui();
        gameEngine.addObserver(this);
    }

    private void refreshGui()
    {
        gridMap.getChildren()
            .clear();
        for( int x = 0; x < 15; x++ )
        {
            for( int y = 0; y < 10; y++ )
            {
                Point currentPoint = new Point( x, y );
                Optional<WarMachine> warMachine = gameEngine.getWarMachine( currentPoint );
                Optional< Creature > creature = gameEngine.getCreature( currentPoint );
                final MapTile mapTile = new MapTile( "" );
                warMachine.ifPresent( c -> mapTile.setName( c.toString() ) );

                if( gameEngine.isCurrentWarMachine( currentPoint ) )
                {
                    mapTile.setBackground( Color.GREENYELLOW );
                }
//                if( gameEngine.canMove( currentPoint ) )
//                {
//                    mapTile.setBackground( Color.GREY );
//                    mapTile.addEventHandler( MouseEvent.MOUSE_CLICKED, ( e ) -> {
//                        gameEngine.move( currentPoint );
//                    } );
//                }
                if( gameEngine.canAttack( currentPoint ) )
                {
                    mapTile.setBackground( Color.INDIANRED );
                    mapTile.addEventHandler( MouseEvent.MOUSE_CLICKED, ( e ) -> {
                        gameEngine.attack( currentPoint );
                    } );
                }
                gridMap.add( mapTile, x, y );
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        refreshGui();
    }
}
