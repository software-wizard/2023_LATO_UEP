package pl.psi.mapElements;

import com.google.common.collect.HashBiMap;
import pl.psi.hero.Hero;

public interface MapElement {

//    void getIcon();

    boolean isInteractive();
    boolean shouldBeRemoveAfterAction();

    void apply(Hero aHero);
    void endOfTurn();
}
