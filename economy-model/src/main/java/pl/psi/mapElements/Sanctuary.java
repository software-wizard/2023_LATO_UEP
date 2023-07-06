package pl.psi.mapElements;

import pl.psi.hero.EconomyHero;
import pl.psi.player.Player;

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
    public void apply(EconomyHero aEconomyHero, Player aPlayer) {

    }

    @Override
    public boolean shouldBeRemoveAfterAction() {
        return false;
    }

    @Override
    public void endOfTurn() {

    }
}
