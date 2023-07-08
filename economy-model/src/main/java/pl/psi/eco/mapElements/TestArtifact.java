
// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco.mapElements;

import lombok.Getter;
import pl.psi.eco.artifacts.Artifact;
import pl.psi.eco.artifacts.ArtifactStatistics;

@Getter
public class TestArtifact extends Artifact {

    public TestArtifact(String aType, String aName){
        super(aType,aName, ArtifactStatistics.builder().build());
    }

}
