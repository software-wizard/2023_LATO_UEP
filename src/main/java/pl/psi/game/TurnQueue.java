// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.game;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class TurnQueue
{

    private final List< Creature > allCreature;
    private Queue< Creature > queue = new LinkedList<>();
    private Creature activeCreature;
    private Collection< PropertyChangeListener > observers = new HashSet<>();

    public TurnQueue( List< Creature > aAllCreature )
    {
        allCreature = aAllCreature.stream()
            .sorted( ( c1, c2 ) -> c2.getMoveRange() - c1.getMoveRange() )
            .collect( Collectors.toList() );
        observers.addAll( allCreature );
        nextCreature();
    }

    public void nextCreature()
    {
        if( queue.isEmpty() )
        {
            initializeQueue( allCreature );
            endOfTurn();
        }
        activeCreature = queue.poll();
    }

    private void endOfTurn()
    {
        observers.forEach( p -> p.propertyChange( new PropertyChangeEvent( this, "END_OF_TURN", 0, 0 ) ) );
    }

    private void initializeQueue( Collection< Creature > aCreatures )
    {
        queue.addAll( aCreatures );
    }

    public Creature getActiveCreature()
    {
        return activeCreature;
    }
}
