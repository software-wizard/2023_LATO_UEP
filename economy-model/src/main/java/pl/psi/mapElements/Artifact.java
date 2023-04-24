package pl.psi.mapElements;

import com.google.common.collect.HashBiMap;
import pl.psi.hero.Hero;

public class Artifact implements MapElement {
    // TODO

    public enum ArtifactName {

    }

    private final Artifact.ArtifactName name;

    public Artifact(Artifact.ArtifactName aName) {
        name = aName;
    }

    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public void apply(Hero aHero, HashBiMap map) {

        map.inverse().remove(this);
    }
}
