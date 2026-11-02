package combat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player(int health, int gold) {
        return new Player("Test", 100, health, 10, gold, null);
    }

    @Test
    @DisplayName("start constants have the excpected Value")
    void startConstantsHaveTheExcpectedValue() {
        assertEquals(100, Player.MAX_HEALTH);
        assertEquals(10, Player.START_ATTACK);
        assertEquals(50, Player.START_GOLD);
    }

    @Test
    @DisplayName("getAttack returns normal attack")
    void getAttackReturnsNormalAttack() {
        assertEquals(10, player(100, 0).getAttack());
    }

    @Test
    @DisplayName("Health above max health is cut to max")
    void healthAboveMaxIsCutToMax() {
        assertEquals(100, player(500, 0).getHealth());
    }

    @Test
    @DisplayName("Health below 0 becomes 0")
    void healthBelowZeroBecomesZero() {
        assertEquals(0, player(-10, 0).getHealth());
    }

    @Test
    @DisplayName("Health never goes below 0")
    void healthNeverGoesBelowZero() {
        Player player = player(10, 0);
        player.takeDamage(50);
        assertEquals(0, player.getHealth());
    }

    @Test
    @DisplayName("Player is Dead with health 0 and alive with health")
    void playerIsDeadWithHealthZeroAndAliveWithHealth() {
        Player player = player(10, 0);
        assertTrue(player.isAlive());
        player.takeDamage(10);
        assertFalse(player.isAlive());
    }

    @Test
    @DisplayName("Gold below 0 becomes 0")
    void goldBelowZeroBecomesZero() {
        assertEquals(0, player(50, -25).getGold());
    }

}