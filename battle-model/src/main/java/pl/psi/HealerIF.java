package pl.psi;

public interface HealerIF extends ActionPerformerIf{
    void heal(MapObjectIf ally) throws Exception;

    public boolean canHeal();
}
