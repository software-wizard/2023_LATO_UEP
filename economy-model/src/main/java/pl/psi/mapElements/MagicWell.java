package pl.psi.mapElements;

import com.google.common.collect.HashBiMap;
import pl.psi.hero.Hero;

import java.util.ArrayList;

// Magic Well: a hero can restore 100% of mana reserves here once per turn.
public class MagicWell implements MapElement {

    private ArrayList<Hero> currentTurnVisitedHeroes = new ArrayList<>();

    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public void apply(Hero aHero) {
        if (!currentTurnVisitedHeroes.contains(aHero)) {
            aHero.getHeroStatistics().setMana(aHero.getHeroStatistics().getMaxMana());
            currentTurnVisitedHeroes.add(aHero);
        }
    }

    @Override
    public boolean shouldBeRemoveAfterAction() {
        return false;
    }

    @Override
    public void endOfTurn() {
        // reset Heroes who visited MagicWell
        this.currentTurnVisitedHeroes.clear();
    }
}
