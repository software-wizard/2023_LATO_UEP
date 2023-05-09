package pl.psi.mapElements;

import com.google.common.collect.HashBiMap;
import pl.psi.hero.Hero;

import java.util.ArrayList;

// Learning Stone: gives visiting heroes +1000 experience upon their first visit.
public class LearningStone implements MapElement {

    private ArrayList<Hero> visitedHeroes = new ArrayList<Hero>();

    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public void apply(Hero aHero) {
        if (!visitedHeroes.contains(aHero)) {
            aHero.getHeroStatistics().setExperience(aHero.getHeroStatistics().getExperience()+1000);
            visitedHeroes.add(aHero);
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
