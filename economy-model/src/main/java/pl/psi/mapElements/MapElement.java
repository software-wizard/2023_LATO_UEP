package pl.psi.mapElements;

import com.google.common.collect.HashBiMap;
import pl.psi.hero.Hero;

public interface MapElement {

//    void getIcon();

    boolean isInteractive();

    void apply(Hero aHero, HashBiMap map);
}
