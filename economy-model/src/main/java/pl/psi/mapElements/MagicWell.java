package pl.psi.mapElements;

import pl.psi.hero.EconomyHero;

import java.util.ArrayList;

// Magic Well: a hero can restore 100% of mana reserves here once per turn.
public class MagicWell implements MapElement {

    private ArrayList<EconomyHero> currentTurnVisitedEconomyHeroes = new ArrayList<>();

    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public void apply(EconomyHero aEconomyHero) {
        if (!currentTurnVisitedEconomyHeroes.contains(aEconomyHero)) {
            aEconomyHero.getEconomyHeroStatistics().setMana(aEconomyHero.getEconomyHeroStatistics().getMaxMana());
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
