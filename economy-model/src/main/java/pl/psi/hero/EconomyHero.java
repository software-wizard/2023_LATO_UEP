package pl.psi.hero;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import pl.psi.creatures.EconomyCreature;
import pl.psi.creatures.Skill;

public class EconomyHero
{

    private final Fraction fraction;
    private final List< EconomyCreature > creatureList;
    private int gold;
    public int level;
    public int manaMax;
    @Getter
    private List<Skill> skillList;

    public EconomyHero( final Fraction aFraction, final int aGold )
    {
        fraction = aFraction;
        gold = aGold;
        creatureList = new ArrayList<>();
    }

    void addCreature( final EconomyCreature aCreature )
    {
        if( creatureList.size() >= 7 )
        {
            throw new IllegalStateException( "Hero has not empty slot for creature" );
        }
        creatureList.add( aCreature );
    }

    public int getGold()
    {
        return gold;
    }

    public void addGold( final int aAmount )
    {
        gold += aAmount;
    }

    public List< EconomyCreature > getCreatures()
    {
        return List.copyOf( creatureList );
    }

    void substractGold( final int aAmount )
    {
        if( aAmount > gold )
        {
            throw new IllegalStateException( "Hero has not enought money" );
        }
        gold -= aAmount;
    }

    public List<Skill> getSkills() {
        return skillList;
    }

    public enum Fraction
    {
        NECROPOLIS;
    }
}
