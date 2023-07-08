// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.psi.eco.hero.EconomyHero;
import pl.psi.eco.mapElements.MapElement;
import pl.psi.eco.player.Player;
import pl.psi.eco.map.GameReader;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EconomyEngine {

    @Getter
    private final Board board;
    private final EcoTurnQueue turnQueue;
    private EconomyEngine economyEngine;
    private final LinkedList<Player> Players;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);


    public EconomyEngine(LinkedList<Player> aPlayers) {
        GameReader gameReader = new GameReader(aPlayers, this::fireEvent);
        this.Players = gameReader.buildPlayers();

        this.board = new Board(gameReader.createMapElements(), Players);
        this.turnQueue = new EcoTurnQueue(Players);
        turnQueue.addObserver(board);
        // board.addObserver(MapController);

    }

    public EconomyHero getEcoHero() {
        return turnQueue.getCurrentPlayer().getEconomyHero();
    }

    public MapElement getMapElement(Point aPoint) {
        return board.getMapElements().get(aPoint);
    }

    public EconomyHero getEconomyHero(Point aPoint) {
        return board.getMapHero().get(aPoint);
    }

    public boolean canMove(final Point aPoint, final EconomyHero aChoosenEconomyHero) {
        return board.canMove(aChoosenEconomyHero, aPoint);
    }

    public void move(final Point aPoint, final EconomyHero aChoosenEconomyHero) {
        board.move(aChoosenEconomyHero, aPoint);
        fireEvent("");
    }

    private void fireEvent(String aType) {
        observerSupport.firePropertyChange(new PropertyChangeEvent(this, aType, null, null));
    }

    public void addObserver(final PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aObserver);
        turnQueue.addObserver(aObserver);
        board.addObserver(aObserver);
    }

    public Player getCurrentPlayer() {
        return turnQueue.getCurrentPlayer();
    }

    public void endTurn() {
        turnQueue.nextTurn();
    }

    public int getCurrentDay() {
        return turnQueue.getDay();
    }

    public int getMapSize() {
        return this.board.getMapSize();
    }

}
