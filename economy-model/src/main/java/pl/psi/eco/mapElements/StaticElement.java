// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco.mapElements;

import pl.psi.eco.hero.EconomyHero;
import pl.psi.eco.player.Player;

/*
Class for bariers like mountains or rivers.
 */
public class StaticElement implements MapElement {

    @Override
    public String getIcon() {
        return null;
    }

    @Override
    public boolean isInteractive() {
        return false;
    }

    @Override
    public void apply(EconomyHero aEconomyHero, Player aPlayer) {

    }

    @Override
    public boolean shouldBeRemoveAfterAction() {
        return false;
    }

    @Override
    public void endOfTurn() {

    }
}
