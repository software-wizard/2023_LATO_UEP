// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco.gui.map;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;
import lombok.SneakyThrows;
import pl.psi.eco.Board;
import pl.psi.eco.EconomyEngine;
import pl.psi.eco.Point;
import pl.psi.eco.converter.EcoBattleConverter;
import pl.psi.eco.hero.EconomyHero;
import pl.psi.eco.mapElements.MapElement;
import pl.psi.eco.gui.castle.Scene1;

public class MapController implements PropertyChangeListener
{

    private final EconomyEngine economyEngine;
    @FXML
    private GridPane gridMap;

    public MapController( EconomyEngine aEngine )
    {
        this.economyEngine = aEngine;
    }

    @FXML
    private void initialize()
    {
        refreshGui();
        economyEngine.addObserver( this );
    }

    private void refreshGui()
    {

        gridMap.getChildren()
            .clear();

        for( int x = 0; x < economyEngine.getMapSize(); x++ )
        {
            for( int y = 0; y < economyEngine.getMapSize(); y++ )
            {
                Point currentPoint = new Point( x, y );
                final MapTile mapTile = new MapTile( "" );

                // Draw map
                Optional<MapElement> mapElement =
                    Optional.ofNullable( economyEngine.getMapElement( currentPoint ) );
                Optional<EconomyHero> economyHero =
                    Optional.ofNullable( economyEngine.getEconomyHero( currentPoint ) );

                mapTile.setBackground( Color.GREEN );
                mapElement.ifPresent( (mapElementToGUI -> {
                    if( !mapElementToGUI.isInteractive() )
                    {
                        mapTile.setBackground( Color.GRAY );
                    }
                    else
                    {
                        mapTile.setBackground( Color.GREEN );

                        try
                        {
                            if( mapElementToGUI.getIcon() != null )
                            {
                                mapTile.setGraphic( mapElementToGUI.getIcon() );
                            }
                        }
                        catch( FileNotFoundException e )
                        {
                            throw new RuntimeException( e );
                        }

                    }
                }) );

                // Draw heroes
                economyHero.ifPresent( economyHeroToGUI -> {
                    try
                    {
                        mapTile.setHeroGraphic( "Hero_Christian" );
                    }
                    catch( FileNotFoundException e )
                    {
                        throw new RuntimeException( e );
                    }
                } );

                if( economyEngine.canMove( currentPoint, economyEngine.getCurrentPlayer()
                    .getEconomyHero() ) )
                {
                    mapTile.setBackground( Color.BLUEVIOLET );
                    mapTile.addEventHandler( MouseEvent.MOUSE_CLICKED, ( e ) -> {
                        economyEngine.move( currentPoint, economyEngine.getCurrentPlayer()
                            .getEconomyHero() );

                    } );
                }

                gridMap.add( mapTile, x, y );
            }
        }
    }

    @Override
    public void propertyChange( PropertyChangeEvent evt )
    {
        refreshGui();
        if( evt.getPropertyName()
            .equals( "OPEN_CASTLE" ) )
        {
            openCastle();
        }

        if( evt.getPropertyName()
            .equals( Board.START_BATTLE ) )
        {
            startBattle( (Pair< EconomyHero, EconomyHero >)evt.getNewValue() );
        }

    }

    private void startBattle( Pair< EconomyHero, EconomyHero > aNewValue )
    {
        EcoBattleConverter converter = new EcoBattleConverter();
        converter.startBattle( aNewValue.getKey(), aNewValue.getValue() );
    }

    @SneakyThrows
    private void openCastle()
    {
        // CastleLauncher.main(new String[0]);
        new Scene1().start( new Stage() );
    }
}
