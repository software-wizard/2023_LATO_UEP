// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco;

import lombok.Getter;
import pl.psi.eco.player.Player;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.Queue;

public class EcoTurnQueue {

    public static final String END_OF_TURN = "END_OF_TURN";
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);
    @Getter
    private int day = 1;
    private final Queue<Player> playersQueue;
    private final LinkedList<Player> players;
    @Getter
    private Player currentPlayer;

    public EcoTurnQueue(final LinkedList<Player> aPlayers) {
        players = aPlayers;
        playersQueue = new LinkedList<>();
        initQueue();
        nextTurn();
    }

    private void initQueue() {
        this.playersQueue.addAll(players);
    }

    public void nextTurn() {
        if (playersQueue.isEmpty()) {
            nextDay();
        }
        currentPlayer = playersQueue.poll();
    }

    private void nextDay() {
        day++;
        observerSupport.firePropertyChange(END_OF_TURN, day - 1, day);
        initQueue();
    }

    void addObserver(PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aObserver);
    }

}
