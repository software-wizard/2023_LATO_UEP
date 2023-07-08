// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco.map;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import pl.psi.eco.Point;
import pl.psi.eco.ResourceType;
import pl.psi.eco.creatures.EconomyCreature;
import pl.psi.eco.creatures.EconomyNecropolisFactory;
import pl.psi.eco.hero.EconomyHero;
import pl.psi.eco.hero.HeroStatistics;
import pl.psi.eco.mapElements.Castle;
import pl.psi.eco.mapElements.LearningStone;
import pl.psi.eco.mapElements.MagicWell;
import pl.psi.eco.mapElements.MapElement;
import pl.psi.eco.mapElements.Mine;
import pl.psi.eco.mapElements.Resource;
import pl.psi.eco.mapElements.StaticElement;
import pl.psi.eco.player.Player;
import pl.psi.eco.player.PlayerResources;

public class GameReader
{

    private final Consumer< String > fireEventMethod;
    private LinkedList< Player > Players;

    public GameReader( LinkedList< Player > aPlayers, Consumer< String > aFireEventMethod )
    {
        this.Players = aPlayers;
        fireEventMethod = aFireEventMethod;
    }

    public LinkedList< Player > buildPlayers()
    {

        try
        {
            EconomyCreature c1 = new EconomyNecropolisFactory().create( false, 1, 10 );

            for( int i = 0; i < Players.size(); i++ )
            {

                PlayerResources playerResources = PlayerResources.builder()
                    .wood( 0 )
                    .ore( 0 )
                    .gold( 5000 )
                    .crystal( 0 )
                    .gems( 0 )
                    .mercury( 1 )
                    .sulfur( 1 )
                    .build();

                EconomyHero economyHero = EconomyHero.builder()
                    .heroStatistics( HeroStatistics.builder()
                        .moveRange( 7 )
                        .experience( 1 )
                        .level( 1 )
                        .mana( 1 )
                        .maxMana( 1 )
                        .build() )
                    .fraction( Castle.FractionType.NECROPOLIS )
                    .heroArmy( new ArrayList<>() )
                    .build();

                economyHero.getHeroArmy()
                    .add( c1 );

                Player aPlayer = Player.builder()
                    .name( Players.get( i )
                        .getName() )
                    .resources( playerResources )
                    .economyHero( economyHero )
                    .town( Players.get( i )
                        .getTown() )
                    .heroName( Players.get( i )
                        .getHeroName() )
                    .bonus( Players.get( i )
                        .getBonus() )
                    .color( Color.yellow )
                    .build();

                Players.set( i, aPlayer );

            }

        }
        catch( Exception e )
        {

            e.printStackTrace();
        }

        return Players;
    }

    public BiMap< pl.psi.eco.Point, MapElement > createMapElements()
    {
        BiMap< pl.psi.eco.Point, MapElement > aMapElements = HashBiMap.create();

        for( int i = 0; i < Players.size(); i++ )
        {
            Random rand = new Random();
            aMapElements.put( new pl.psi.eco.Point( rand.nextInt( 24 ), rand.nextInt( 24 ) ), Players.get( i )
                .getEconomyHero() );
        }

        Castle castle = new Castle( Castle.FractionType.NECROPOLIS, fireEventMethod );
        aMapElements.put( new pl.psi.eco.Point( 2, 2 ), castle );

        Random rand = new Random();
        List< String > elements =
            Arrays.asList( "LearningStone", "MagicWell", "Mine", "Resource", "StaticElement", "Resource",
                "StaticElement", "StaticElement", "StaticElement", "StaticElement", "StaticElement" );

        for( int i = 0; i < 45; i++ )
        {

            pl.psi.eco.Point currentPoint = null;
            boolean isEmpty = false;
            while( !isEmpty )
            {
                int x = rand.nextInt( 25 );
                int y = rand.nextInt( 25 );
                if( aMapElements.get( new pl.psi.eco.Point( x, y ) ) == null )
                {
                    isEmpty = true;
                }
                currentPoint = new Point( x, y );
            }

            String randomElement = elements.get( rand.nextInt( elements.size() ) );
            ResourceType[] values = ResourceType.values();
            int length = values.length;
            int randIndex;
            switch( randomElement )
            {
                case "LearningStone":
                    aMapElements.put( currentPoint, new LearningStone() );
                    break;
                case "MagicWell":
                    aMapElements.put( currentPoint, new MagicWell() );
                    break;
                case "Mine":
                    randIndex = new Random().nextInt( length );

                    aMapElements.put( currentPoint, new Mine( values[ randIndex ] ) );
                    break;
                case "Resource":
                    randIndex = new Random().nextInt( length );
                    int resourceAmount;

                    if( values[ randIndex ].equals( ResourceType.GOLD ) )
                    {
                        resourceAmount = 1000;
                    }
                    else
                    {
                        resourceAmount = 2;
                    }
                    aMapElements.put( currentPoint, new Resource( values[ randIndex ], resourceAmount ) );
                    break;
                case "StaticElement":
                    aMapElements.put( currentPoint, new StaticElement() );
                    break;
            }
        }

        return aMapElements;
    }

}
