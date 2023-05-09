
package pl.psi.mapElements;

import lombok.Getter;
import pl.psi.hero.Hero;

@Getter
public class TestArtifact implements MapElement {
    private final String type ;
    private final String name ;

    public TestArtifact(String aType,String aName){
        this.type=aType;
        this.name=aName;
    }

    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public void apply(Hero aHero) {
        aHero.addArtifactToBackpack(this);
    }

    @Override
    public boolean shouldBeRemoveAfterAction() {
        return true;
    }

    @Override
    public void endOfTurn() {

    }
}
