package pl.psi.creatures;

import lombok.Builder;

@Builder
public class EconomyCreature
{

    private final CreatureStatistic stats;
    private int amount;
    private final int goldCost;

    public EconomyCreature( final CreatureStatistic aStats, int aAmount, final int aGoldCost )
    {
        stats = aStats;
        amount = aAmount;
        goldCost = aGoldCost;
    }

    public int getAmount()
    {
        return amount;
    }

    public int getGoldCost()
    {
        return goldCost;
    }

    public String getName()
    {
        return stats.getTranslatedName();
    }

    public boolean isUpgraded()
    {
        return stats.isUpgraded();
    }

    public int getTier()
    {
        return stats.getTier();
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
