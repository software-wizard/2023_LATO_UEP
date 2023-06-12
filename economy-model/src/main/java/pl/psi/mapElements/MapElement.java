package pl.psi.mapElements;

import pl.psi.hero.EconomyHero;

public interface MapElement {

//    void getIcon();

    boolean isInteractive();
    boolean shouldBeRemoveAfterAction();

    void apply(EconomyHero aEconomyHero);
    void endOfTurn();
}
