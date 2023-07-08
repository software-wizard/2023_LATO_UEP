// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco.mapElements;

import pl.psi.eco.hero.EconomyHero;
import pl.psi.eco.player.Player;

// Sanctuary: a visiting hero cannot be attacked by enemies (but can interact with allied heroes).
public class Sanctuary implements MapElement {
    // TODO

    @Override
    public String getIcon() {
        return null;
    }

    @Override
    public boolean isInteractive() {
        return true;
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
