// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco.mapElements;

import pl.psi.eco.hero.EconomyHero;
import pl.psi.eco.player.Player;

import java.util.ArrayList;

// Magic Well: a hero can restore 100% of mana reserves here once per turn.
public class MagicWell implements MapElement {

    private ArrayList<EconomyHero> currentTurnVisitedEconomyHeroes = new ArrayList<>();

    @Override
    public String getIcon() {
        return "Magic_Well";
    }

    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public void apply(EconomyHero aEconomyHero, Player aPlayer) {
        if (!currentTurnVisitedEconomyHeroes.contains(aEconomyHero)) {
            aEconomyHero.getHeroStatistics().setMana(aEconomyHero.getHeroStatistics().getMaxMana());
            currentTurnVisitedEconomyHeroes.add(aEconomyHero);
        }
    }

    @Override
    public boolean shouldBeRemoveAfterAction() {
        return false;
    }

    @Override
    public void endOfTurn() {
        // reset Heroes who visited MagicWell
        this.currentTurnVisitedEconomyHeroes.clear();
    }
}
