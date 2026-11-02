package combat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {
    AttackStrategy attack = new BaseAttack();

    Monster monster() {
        return new Monster("Goblin", 100, 7,
                false, 10, attack, 100);
    }

    @Test
    @DisplayName("Constructor sets values")
    void constructorSetsValues() {
        Monster monster = monster();
        assertEquals("Goblin", monster.getName());
        assertEquals(100, monster.getHealth());
        assertEquals(100, monster.getMaxHealth());
        assertEquals(7, monster.getAttack());
        assertEquals(10, monster.getReward());
    }

    @Test
    @DisplayName("Attack damage ignores zero and below")
    void attackDamageIgnoresZeroAndBelow() {
        Monster monster = monster();
        monster.takeDamage(0);
        monster.takeDamage(-50);
        assertEquals(100, monster.getHealth());
    }

    @Test
    @DisplayName("Health never goes below 0")
    void healthNeverGoesBelowZero() {
        Monster monster = monster();
        monster.takeDamage(500);
        assertEquals(0, monster.getHealth());
    }

    @Test
    @DisplayName("Monster is Dead with health at zero and alive with health above")
    void monsterIsDeadWithHealthAtZeroAndAliveWithHealthAbove() {
        Monster monster = monster();
        assertTrue(monster.isAlive());
        monster.takeDamage(500);
        assertFalse(monster.isAlive());
    }

}