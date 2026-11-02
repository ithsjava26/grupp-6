package combat;

import common.Fighter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MonsterAttackTest {
    private MonsterAttack monsterAttack;
    private Fighter attacker;
    private Fighter defender;

    @BeforeEach
    void setUp() {
        monsterAttack = new MonsterAttack();
    }

    @Test
    @DisplayName("Attack deals normal or heavy damage")
    void AttackDealsNormalOrHeavyDamage() {
        double multiplier = monsterAttack.getDamageMultiplier(attacker, defender);

        assertTrue(multiplier == 1.0 || multiplier == 1.5);
    }

}