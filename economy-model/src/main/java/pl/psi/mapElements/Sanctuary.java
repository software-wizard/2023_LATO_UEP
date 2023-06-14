package pl.psi.mapElements;

import pl.psi.hero.EconomyHero;

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
