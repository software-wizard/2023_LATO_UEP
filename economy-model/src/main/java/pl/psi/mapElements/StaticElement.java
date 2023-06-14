package pl.psi.mapElements;

import pl.psi.hero.EconomyHero;

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
    public void apply(EconomyHero aEconomyHero) {

    }

    @Override
    public boolean shouldBeRemoveAfterAction() {
        return false;
    }

    @Override
    public void endOfTurn() {

    }
}
