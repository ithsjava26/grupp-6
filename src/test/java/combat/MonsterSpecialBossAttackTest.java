package combat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MonsterSpecialBossAttackTest {
    MonsterSpecialBossAttack bossAttack = new MonsterSpecialBossAttack();
    Player hero = new Player("Hero", 100, 100, 10, 0, null);
    Monster boss = new Monster("Boss", 80, 15, true, 0, bossAttack, 100);

    @Test
    @DisplayName("Boss deals normal or double damage")
    void bossDealsNormalOrDouleDamage() {
        for (int i = 0; i < 200; i++) {
            double multiplier = bossAttack.getDamageMultiplier(boss, hero);
            assertTrue(multiplier == 1.0 || multiplier == 2.0);
        }
    }

    @Test
    @DisplayName("Boss attack message is never empty")
    void bossAttackMessageIsNeverEmpty() {
        for (int i = 0; i < 200; i++) {
            bossAttack.getDamageMultiplier(boss, hero);
            assertTrue(!bossAttack.getMessage(boss, hero).isBlank());
        }
    }
}