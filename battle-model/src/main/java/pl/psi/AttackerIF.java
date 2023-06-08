package pl.psi;

public interface AttackerIF extends ActionPerformerIf {
    void attack(MapObjectIf defender) throws Exception;
    boolean canAttackFromDistance();
}
