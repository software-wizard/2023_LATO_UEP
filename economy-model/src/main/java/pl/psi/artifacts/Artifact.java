package pl.psi.artifacts;

import com.google.common.collect.HashBiMap;
import lombok.Builder;
import lombok.Getter;
import pl.psi.hero.EconomyHero;
import pl.psi.mapElements.MapElement;

import java.awt.*;

@Builder
@Getter
public class Artifact implements MapElement {
    // TODO
    private final String type ;
    private final String name ;
    private ArtifactStatistics artifactStatistics;


    public Artifact(String aType,String aName, ArtifactStatistics aArtifactStatistics){
        this.type=aType;
        this.name=aName;
        this.artifactStatistics=aArtifactStatistics;
    }

    @Override
    public String getIcon() {
        return null;
    }

    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public void apply(EconomyHero aEconomyHero) {
        aEconomyHero.addArtifactToBackpack(this);
    }

    @Override
    public boolean shouldBeRemoveAfterAction() {
        return true;
    }

    @Override
    public void endOfTurn() {

    }
}
