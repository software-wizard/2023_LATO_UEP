// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.battle;

import pl.psi.eco.MapObjectIf;

public interface HealerIF extends ActionPerformerIf{
    void heal(MapObjectIf ally) throws Exception;
}
