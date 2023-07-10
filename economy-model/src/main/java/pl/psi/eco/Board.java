// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.Optional;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import javafx.util.Pair;
import lombok.Getter;
import pl.psi.eco.hero.EconomyHero;
import pl.psi.eco.mapElements.MapElement;
import pl.psi.eco.player.Player;

public class Board implements PropertyChangeListener
{

    public static final String START_BATTLE = "START_BATTLE";
    public static final String HERO_MOVED = "HERO_MOVED";
    @Getter
    private int MapSize = 25;
    private LinkedList<Player> Players;
    private final BiMap< Point, MapElement> map = HashBiMap.create();
    @Getter
    private final BiMap< Point, EconomyHero> mapHero = HashBiMap.create();
    @Getter
    private final BiMap< Point, MapElement > mapElements;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport( this );

    public Board( BiMap< Point, MapElement > aMapElements, LinkedList< Player > aPlayers )
    {
        mapElements = aMapElements;
        this.Players = aPlayers;

        // Set elements like heroes, mountains, gold and so on, on the board.
        for( Point point : aMapElements.keySet() )
        {
            addMapElement( point, aMapElements.get( point ) );
        }
    }

    public void addObserver( PropertyChangeListener aObserver )
    {
        observerSupport.addPropertyChangeListener( aObserver );
    }

    public Player getPlayer( EconomyHero aEconomyHero )
    {
        for( Player player : this.Players )
        {
            if( player.getEconomyHero()
                .equals( aEconomyHero ) )
            {
                return player;
            }
        }
        return null;
    }

    private void addMapElement( Point aPoint, MapElement aMapElement )
    {
        if( aMapElement instanceof EconomyHero )
        {
            mapHero.put( aPoint, (EconomyHero)aMapElement );
        }
        else
        {
            map.put( aPoint, aMapElement );
        }
    }

    Optional< MapElement > getMapElement( final Point aPoint )
    {
        return Optional.ofNullable( map.get( aPoint ) );
    }

    Optional< EconomyHero > getHero( final Point aPoint )
    {
        return Optional.ofNullable( mapHero.get( aPoint ) );
    }

    private void checkBattle(EconomyHero aEconomyHero, Point aPoint) {
        for( int i = -1; i <= 1; i++ )
        {
            for( int j = -1; j <= 1; j++ )
            {
                if( mapHero.containsKey( new Point( aPoint.getX() + i, aPoint.getY() + j ) ) )
                {
                    if( aEconomyHero != mapHero.get( new Point( aPoint.getX() + i, aPoint.getY() + j ) ) )
                    {
                        try
                        {
                            observerSupport.firePropertyChange( START_BATTLE, null,
                                    new Pair<>( aEconomyHero,
                                            mapHero.get( new Point( aPoint.getX() + i, aPoint.getY() + j ) ) ) );
                        }
                        catch( Exception e )
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private void updateMoveRange(EconomyHero aEconomyHero, Point aPoint) {
        aEconomyHero.getHeroStatistics()
                .setMoveRange( aEconomyHero.getHeroStatistics()
                        .getMoveRange()
                        - (int)Math.ceil( aPoint.distance( getHeroPosition( aEconomyHero ).getX(),
                        getHeroPosition( aEconomyHero ).getY() ) ) );
    }

    void move( final EconomyHero aEconomyHero, final Point aPoint )
    {
        System.out.println("Move action");

        if( canMove( aEconomyHero, aPoint ) )
        {
            updateMoveRange(aEconomyHero, aPoint);

            if( map.get( aPoint ) != null )
            {
                if( map.get( aPoint )
                    .shouldBeRemoveAfterAction() )
                {
                    map.inverse()
                        .remove( map.get( aPoint ) );
                    mapElements.inverse()
                        .remove( mapElements.get( aPoint ) );
                }

                map.get( aPoint )
                    .apply( aEconomyHero, getPlayer( aEconomyHero ) );
            }

            checkBattle(aEconomyHero, aPoint);

            mapHero.inverse()
                .remove( aEconomyHero );
            mapHero.put( aPoint, aEconomyHero );
            observerSupport.firePropertyChange( HERO_MOVED, null, null );
        }
    }

    boolean canMove( final EconomyHero aEconomyHero, final Point aPoint )
    {
        if( map.containsKey( aPoint ) )
        {
            if( !map.get( aPoint )
                .isInteractive() )
            {
                return false;
            }
        }

        if( mapHero.containsKey( aPoint ) )
        {
            if( !mapHero.get( aPoint )
                .isInteractive() )
            {
                return false;
            }
        }
        final Point oldPosition = getHeroPosition( aEconomyHero );
        return aPoint.distance( oldPosition.getX(), oldPosition.getY() ) <= aEconomyHero.getHeroStatistics()
            .getMoveRange();
    }

    Point getPosition( MapElement aMapElement )
    {
        return map.inverse()
            .get( aMapElement );
    }

    public Point getHeroPosition( EconomyHero aEconomyHero )
    {
        return mapHero.inverse()
            .get( aEconomyHero );
    }

    @Override
    public void propertyChange( PropertyChangeEvent evt )
    {
        if( evt.getPropertyName()
            .equals( EcoTurnQueue.END_OF_TURN ) )
        {
            for( MapElement mapElement : mapElements.values() )
            {
                mapElement.endOfTurn();
            }
        }
    }
}