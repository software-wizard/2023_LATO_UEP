package pl.psi.warmachines;

public class FirstAidTent extends WarMachine{
    @Override
    public boolean canHeal() {
        return true;
    }

    @Override
    public boolean canAttack() {
        return false;
    }
}
