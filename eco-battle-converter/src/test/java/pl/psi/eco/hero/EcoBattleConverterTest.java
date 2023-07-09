// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco.hero;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import pl.psi.eco.converter.EcoBattleConverter;
import pl.psi.battle.creatures.Creature;
import pl.psi.eco.creatures.EconomyCreature;
import pl.psi.eco.creatures.EconomyNecropolisFactory;
import pl.psi.eco.mapElements.Castle;

class EcoBattleConverterTest {

    @Test
    void shouldConvertCreaturesCorrectly() {
        ArrayList<EconomyCreature> heroArmy = new ArrayList<>();

        final EconomyHero ecoHero = new EconomyHero(HeroStatistics.builder().build(), Castle.FractionType.NECROPOLIS, heroArmy);
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        ecoHero.addCreature(factory.create(false, 1, 1));
        ecoHero.addCreature(factory.create(false, 2, 2));
        ecoHero.addCreature(factory.create(false, 3, 3));
        ecoHero.addCreature(factory.create(false, 4, 4));
        ecoHero.addCreature(factory.create(false, 5, 5));
        ecoHero.addCreature(factory.create(false, 6, 6));
        ecoHero.addCreature(factory.create(false, 7, 7));

        final List<Creature> convertedCreatures = EcoBattleConverter.convert(ecoHero)
                .getCreatures();

        assertEquals(7, convertedCreatures.size());

        assertEquals("Skeleton", convertedCreatures.get(0)
                .getName());
        assertEquals(1, convertedCreatures.get(0)
                .getAmount());

        assertEquals("Walking Dead", convertedCreatures.get(1)
                .getName());
        assertEquals(2, convertedCreatures.get(1)
                .getAmount());

        assertEquals("Wight", convertedCreatures.get(2)
                .getName());
        assertEquals(3, convertedCreatures.get(2)
                .getAmount());

        assertEquals("Vampire", convertedCreatures.get(3)
                .getName());
        assertEquals(4, convertedCreatures.get(3)
                .getAmount());

        assertEquals("Lich", convertedCreatures.get(4)
                .getName());
        assertEquals(5, convertedCreatures.get(4)
                .getAmount());

        assertEquals("Black Knight", convertedCreatures.get(5)
                .getName());
        assertEquals(6, convertedCreatures.get(5)
                .getAmount());

        assertEquals("Bone Dragon", convertedCreatures.get(6)
                .getName());
        assertEquals(7, convertedCreatures.get(6)
                .getAmount());
    }

}