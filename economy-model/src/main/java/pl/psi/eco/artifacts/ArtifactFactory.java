// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco.artifacts;

import javafx.scene.image.Image;

public class ArtifactFactory {
    public Artifact create( final String aType, final String aName)
    {
        if( aType.equals("helmet") )
        {
            if (aName.equals("skull helmet")){
                return new Artifact(aType,aName, ArtifactStatistics.builder()
                        .image(new Image(getClass().getResource("/Artifact_Skull_Helmet.png").toString()) )
                        .price(3000)
                        .knowledge(2)
                        .build());
            }
            else if (aName.equals("helm of chaos")){
                return new Artifact(aType,aName, ArtifactStatistics.builder()
                        .image(new Image(getClass().getResource("/Artifact_Helm_of_Chaos.png").toString()) )
                        .price(4000)
                        .knowledge(3)
                        .build());
            }
        }
        else if (aType.equals("cape"))
        {
            if (aName.equals("dragon wing tabard")){
                return new Artifact(aType,aName, ArtifactStatistics.builder()
                        .image(new Image(getClass().getResource("/Artifact_Dragon_Wing_Tabard.png").toString()) )
                        .price(4000)
                        .spellPower(2)
                        .knowledge(2)
                        .build());
            }
            else if (aName.equals("vampire's cowl")){
                return new Artifact(aType,aName, ArtifactStatistics.builder()
                        .image(new Image(getClass().getResource("/Artifact_Vampire's_Cowl.png").toString()) )
                        .price(4000)
                        .necromancy(0.1)
                        .build());
            }
        }
        else if (aType.equals("necklace"))
        {
            if (aName.equals("amulet of the undertaker")){
                return new Artifact(aType,aName, ArtifactStatistics.builder()
                        .image(new Image(getClass().getResource("/Artifact_Amulet_of_the_Undertaker.png").toString()) )
                        .price(2000)
                        .necromancy(0.05)
                        .build());
            }
            else if (aName.equals("pedant of courage")){
                return new Artifact(aType,aName, ArtifactStatistics.builder()
                        .image(new Image(getClass().getResource("/Artifact_Pendant_of_Courage.png").toString()) )
                        .price(7000)
                        .luck(3)
                        .morale(3)
                        .build());
            }
        }
        else if (aType.equals("rightHand"))
        {
            if (aName.equals("sword of hellfire")){
                return new Artifact(aType,aName, ArtifactStatistics.builder()
                        .image(new Image(getClass().getResource("/Artifact_Sword_of_Hellfire.png").toString()) )
                        .price(6000)
                        .attack(6)
                        .build());
            }
            else if (aName.equals("centaur's axe")){
                return new Artifact(aType,aName, ArtifactStatistics.builder()
                        .image(new Image(getClass().getResource("/Artifact_Centaur's_Axe.png").toString()) )
                        .price(2000)
                        .attack(2)
                        .build());
            }
        }
        else if (aType.equals("leftHand"))
        {
            if (aName.equals("shield of the dwarven lords")){
                return new Artifact(aType,aName, ArtifactStatistics.builder()
                        .image(new Image(getClass().getResource("/Artifact_Shield_of_the_Dwarven_Lords.png").toString()) )
                        .price(2000)
                        .defense(2)
                        .build());
            }
            else if (aName.equals("sentinel's shield")){
                return new Artifact(aType,aName, ArtifactStatistics.builder()
                        .image(new Image(getClass().getResource("/Artifact_Sentinel's_Shield.png").toString()) )
                        .price(10000)
                        .defense(12)
                        .attack(-3)
                        .build());
            }
        }
        else if (aType.equals("torso"))
        {
            if (aName.equals("dragon scale armor")){
                return new Artifact(aType,aName, ArtifactStatistics.builder()
                        .image(new Image(getClass().getResource("/Artifact_Dragon_Scale_Armor.png").toString()) )
                        .price(8000)
                        .attack(4)
                        .defense(4)
                        .build());
            }
            else if (aName.equals("rib cage")){
                return new Artifact(aType,aName, ArtifactStatistics.builder()
                        .image(new Image(getClass().getResource("/Artifact_Rib_Cage.png").toString()) )
                        .price(3000)
                        .spellPower(2)
                        .build());
            }
        }
        else if (aType.equals("ring"))
        {
            if (aName.equals("still eye of the dragon")){
                return new Artifact(aType,aName, ArtifactStatistics.builder()
                        .image(new Image(getClass().getResource("/Artifact_Still_Eye_of_the_Dragon.png").toString()) )
                        .price(2000)
                        .morale(1)
                        .luck(1)
                        .build());
            }
            else if (aName.equals("ring of conjuring")){
                return new Artifact(aType,aName, ArtifactStatistics.builder()
                        .image(new Image(getClass().getResource("/Artifact_Ring_of_Conjuring.png").toString()) )
                        .price(1000)
                        .spellDuration(2)
                        .build());
            }
        }
        else if (aType.equals("feet"))
        {
            if (aName.equals("dead man's boots")){
                return new Artifact(aType,aName, ArtifactStatistics.builder()
                        .image(new Image(getClass().getResource("/Artifact_Dead_Man's_Boots.png").toString()) )
                        .price(6000)
                        .necromancy(0.15)
                        .build());
            }
            else if (aName.equals("boots of polarity")){
                return new Artifact(aType,aName, ArtifactStatistics.builder()
                        .image(new Image(getClass().getResource("/Artifact_Boots_of_Polarity.png").toString()) )
                        .price(6000)
                        .magicResistance(0.15)
                        .build());
            }
        }
        else if (aType.equals("miscellaneous"))
        {
            if (aName.equals("bow of elven cherrywood")){
                return new Artifact(aType,aName, ArtifactStatistics.builder()
                        .image(new Image(getClass().getResource("/Artifact_Bow_of_Elven_Cherrywood.png").toString()) )
                        .price(2000)
                        .archery(0.05)
                        .build());
            }
            else if (aName.equals("clover of fortune")){
                return new Artifact(aType,aName, ArtifactStatistics.builder()
                        .image(new Image(getClass().getResource("/Artifact_Clover_of_Fortune.png").toString()) )
                        .price(1000)
                        .luck(1)
                        .build());
            }
        }
        throw new IllegalArgumentException( "Cannot recognize artifact by type and name or not." );
    }
}
