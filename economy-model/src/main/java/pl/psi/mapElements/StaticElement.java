package pl.psi.mapElements;

import pl.psi.hero.Hero;

/*
Class for bariers like mountains or rivers.
 */
public class StaticElement implements MapElement {

    @Override
    public boolean isInteractive() {
        return false;
    }

    @Override
    public void apply(Hero aHero) {

    }
}
