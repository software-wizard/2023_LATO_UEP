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

// Learning Stone: gives visiting heroes +1000 experience upon their first visit.
public class LearningStone implements MapElement {

    private ArrayList<EconomyHero> visitedEconomyHeroes = new ArrayList<EconomyHero>();

    @Override
    public String getIcon() {
        return "Learning_Stone";
    }

    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public void apply(EconomyHero aEconomyHero, Player aPlayer) {
        if (!visitedEconomyHeroes.contains(aEconomyHero)) {
            aEconomyHero.getHeroStatistics().setExperience(aEconomyHero.getHeroStatistics().getExperience()+1000);
            visitedEconomyHeroes.add(aEconomyHero);
        }

    }

    @Override
    public boolean shouldBeRemoveAfterAction() {
        return false;
    }

    @Override
    public void endOfTurn() {

    }
}
