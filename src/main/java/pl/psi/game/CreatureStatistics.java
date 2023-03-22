// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.game;

import com.google.common.collect.Range;
import lombok.Builder;
import lombok.Value;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Value
@Builder
public class CreatureStatistics {
    private final Range< Integer > damage;
    private final int maxHp;
    private final int attack;
    private final int armor;
}
