// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco.artifacts;

import lombok.Builder;
import lombok.Getter;
import pl.psi.eco.mapElements.MapElement;
import pl.psi.eco.hero.EconomyHero;
import pl.psi.eco.player.Player;

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
    public void apply(EconomyHero aEconomyHero, Player aPlayer) {
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
