package pl.psi.mapElements;

import pl.psi.hero.Hero;

public interface MapElement {

//    void getIcon();

    boolean isInteractive();

    void apply(Hero aHero);

}
