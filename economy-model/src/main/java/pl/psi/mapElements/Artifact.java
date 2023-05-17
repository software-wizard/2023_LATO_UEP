package pl.psi.mapElements;

import pl.psi.hero.EconomyHero;

public class Artifact implements MapElement {
    // TODO

    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public void apply(EconomyHero aEconomyHero) {
    }

    @Override
    public boolean shouldBeRemoveAfterAction() {
        return true;
    }

    @Override
    public void endOfTurn() {

    }
}
