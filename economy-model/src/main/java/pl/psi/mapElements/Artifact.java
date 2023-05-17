package pl.psi.mapElements;

import com.google.common.collect.HashBiMap;
import lombok.Getter;
import pl.psi.hero.EconomyHero;
@Getter
public class Artifact implements MapElement {
    // TODO
    private final String type ;
    private final String name ;
    public Artifact(String aType,String aName){
        this.type=aType;
        this.name=aName;
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
