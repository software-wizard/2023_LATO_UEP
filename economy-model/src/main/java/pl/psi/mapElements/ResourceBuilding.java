package pl.psi.mapElements;

import com.google.common.collect.HashBiMap;
import pl.psi.hero.Hero;

public class ResourceBuilding implements MapElement {

    @Override
    public boolean isInteractive() {
        return false;
    }

    @Override
    public void apply(Hero aHero, HashBiMap map) {
    }
}
