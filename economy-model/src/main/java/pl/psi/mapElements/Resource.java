package pl.psi.mapElements;

import pl.psi.Hero;

public class Resource implements MapElement {
    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public void apply(Hero aHero) {
        aHero.setGold(aHero.getGold()+1);
    }
}
