// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco.artifacts;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javafx.scene.image.Image;

@Getter
@Setter
@Builder
public class ArtifactStatistics {
    private Image image;
    private int price;
    private int knowledge;
    private int spellPower;
    private int spellDuration;
    private double magicResistance;
    private double necromancy;
    private int attack;
    private int defense;
    private int morale;
    private int luck;
    private double archery;

}