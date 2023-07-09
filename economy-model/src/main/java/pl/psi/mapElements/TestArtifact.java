
package pl.psi.mapElements;

import lombok.Getter;
import pl.psi.artifacts.Artifact;
import pl.psi.artifacts.ArtifactStatistics;

@Getter
public class TestArtifact extends Artifact {

    public TestArtifact(String aType, String aName){
        super(aType,aName, ArtifactStatistics.builder().build());
    }

}
