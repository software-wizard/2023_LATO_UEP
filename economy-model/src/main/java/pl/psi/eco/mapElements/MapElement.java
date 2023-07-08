// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco.mapElements;

import pl.psi.eco.hero.EconomyHero;
import pl.psi.eco.player.Player;

public interface MapElement {

    String getIcon();

    boolean isInteractive();
    boolean shouldBeRemoveAfterAction();

    void apply(EconomyHero aEconomyHero, Player aPlayer);
    void endOfTurn();
}
