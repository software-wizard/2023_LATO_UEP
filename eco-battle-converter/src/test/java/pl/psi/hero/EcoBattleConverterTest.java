package pl.psi.hero;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import pl.psi.Fraction;
import pl.psi.converter.EcoBattleConverter;
import pl.psi.creatures.Creature;
import pl.psi.creatures.EconomyCreature;
import pl.psi.creatures.EconomyNecropolisFactory;

class EcoBattleConverterTest
{

    @Test
    void shouldConvertCreaturesCorrectly()
    {
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();

        ArrayList<EconomyCreature> aHeroArmy = new ArrayList<>();
        aHeroArmy.add(factory.create( false, 1, 1 ));
        aHeroArmy.add(factory.create( false, 2, 2 ));
        aHeroArmy.add(factory.create( false, 3, 3 ));
        aHeroArmy.add(factory.create( false, 4, 4 ));
        aHeroArmy.add(factory.create( false, 5, 5 ));
        aHeroArmy.add(factory.create( false, 6, 6 ));
        aHeroArmy.add(factory.create( false, 7, 7 ));


        final EconomyHero ecoHero = EconomyHero.builder()
                .economyHeroStatistics(EconomyHeroStatistics.builder()
                        .fraction(Fraction.NECROPOLIS)
                        .build())
                .build();
//        final EconomyHero ecoHero = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 1000 );
//        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
//        ecoHero.addCreature( factory.create( false, 1, 1 ) );
//        ecoHero.addCreature( factory.create( false, 2, 2 ) );
//        ecoHero.addCreature( factory.create( false, 3, 3 ) );
//        ecoHero.addCreature( factory.create( false, 4, 4 ) );
//        ecoHero.addCreature( factory.create( false, 5, 5 ) );
//        ecoHero.addCreature( factory.create( false, 6, 6 ) );
//        ecoHero.addCreature( factory.create( false, 7, 7 ) );

        final List< Creature > convertedCreatures = EcoBattleConverter.convert( ecoHero )
            .getCreatures();

        assertEquals( 7, convertedCreatures.size() );

        assertEquals( "Skeleton", convertedCreatures.get( 0 )
            .getName() );
        assertEquals( 1, convertedCreatures.get( 0 )
            .getAmount() );

        assertEquals( "Walking Dead", convertedCreatures.get( 1 )
            .getName() );
        assertEquals( 2, convertedCreatures.get( 1 )
            .getAmount() );

        assertEquals( "Wight", convertedCreatures.get( 2 )
            .getName() );
        assertEquals( 3, convertedCreatures.get( 2 )
            .getAmount() );

        assertEquals( "Vampire", convertedCreatures.get( 3 )
            .getName() );
        assertEquals( 4, convertedCreatures.get( 3 )
            .getAmount() );

        assertEquals( "Lich", convertedCreatures.get( 4 )
            .getName() );
        assertEquals( 5, convertedCreatures.get( 4 )
            .getAmount() );

        assertEquals( "Black Knight", convertedCreatures.get( 5 )
            .getName() );
        assertEquals( 6, convertedCreatures.get( 5 )
            .getAmount() );

        assertEquals( "Bone Dragon", convertedCreatures.get( 6 )
            .getName() );
        assertEquals( 7, convertedCreatures.get( 6 )
            .getAmount() );
    }

}