package combat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AttackStrategyTest {
    Monster attacker;
    Monster defender;

    @BeforeEach
    void setUp() {
        attacker = new Monster("Attacker", 100, 10, false, 0, new BaseAttack(), 50);
        defender = new Monster("Defender", 100, 1, false, 0, new BaseAttack(), 100);
    }

    @Test
    @DisplayName("Base attack deals the full attack value")
    void baseAttackDealsFullAttackValue() {
        int result = new BaseAttack().calculateDamage(attacker, defender);

        assertEquals(10, result);
    }

    @Test
    @DisplayName("Heavy attack deals 150% of the attack value")
    void heavyAttachDeals150PercentOfTheAttackValue() {
        int result = new HeavyAttack().calculateDamage(attacker, defender);

        assertEquals(15, result);
    }

    @Test
    @DisplayName("Special attack deals double damage against a weak oponent")
    void specialAttackDoublesDamageAgainstAWeakDefender() {
        defender.takeDamage(80);
        int result = new SpecialAttack().calculateDamage(attacker, defender);

        assertEquals(20, result);
    }

    @Test
    @DisplayName("Special deals normal damage against a target with 21% of health")
    void specialAttackIsNormalAgainstHealthyDefender() {
        defender.takeDamage(79);
        int result = new SpecialAttack().calculateDamage(attacker, defender);

        assertEquals(10, result);
    }


}