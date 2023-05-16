package pl.psi.creatures;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Random;

public class DefenseSkillTest {
    private static final Range<Integer> NOT_IMPORTANT_DMG = Range.closed(0, 0);

    @Mock
    private Random randomMock;

    @Test

    public void ShouldReduceDamageBy2_5Percent()
    {
        // Arrange
        var randomMock = new System.Random();
        var attacker = new Creature.Builder()
                .Statistic(new CreatureStats.Builder()
                        .MaxHp(100)
                        .Damage(new Range<int>(10, 10))
                        .Attack(50)
                        .Armor(0)
                        .Build())
                .Build();
        var defender = new Creature.Builder()
                .Statistic(new CreatureStats.Builder()
                        .MaxHp(100)
                        .Damage(new Range<int>(10, 10))
                        .Attack(50)
                        .Armor(0)
                        .Build())
                .Build();
        var defenseSkill = new DefenseSkill(randomMock);

        // Act
        defenseSkill.apply(defender);

        //attacker.attack(defender);

        // Assert
        var expectedDamageReduction = (int)(10 * 0.025); // Oczekiwane zmniejszenie obrażeń: 10 * 0.025 = 0.25, zaokrąglone do 0
        //Assert.AreEqual(10 - expectedDamageReduction, defender.CurrentHp);
}
