package pl.psi;

public interface AttackerIF {
    void attack(MapObjectIf defender) throws Exception;
    boolean canAttackFromDistance();
    public boolean canAttack();
}
