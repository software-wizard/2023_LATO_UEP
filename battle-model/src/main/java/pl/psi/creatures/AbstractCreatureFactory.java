package pl.psi.creatures;

public abstract class AbstractCreatureFactory {
    public abstract Creature create( final boolean aIsUpgraded, final int aTier, final int aAmount );
    //public AbstractCreatureFactory getInstance(/*enum od frakcji*/);
}
