package pl.psi.mapElements;

import pl.psi.hero.EconomyHero;
import pl.psi.player.Player;

public interface MapElement {

    String getIcon();

    boolean isInteractive();
    boolean shouldBeRemoveAfterAction();

    void apply(EconomyHero aEconomyHero, Player aPlayer);
    void endOfTurn();
}
