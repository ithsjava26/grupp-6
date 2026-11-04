package combat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AttackStrategyTest {
    Player hero = new Player("Hero", 100,
            100, 10, 0, null);

    Monster monsterwithHealth(int health) {
        return new Monster("Monster", 100,
                10, false, 0, new MonsterAttack(), health);
    }

    @Test
    @DisplayName("Basic attack has multiplier 1.0")
    void basicAttackHasMultiplierOne() {
        assertEquals(1.0,
                new BaseAttack().getDamageMultiplier(hero, monsterwithHealth(100)));
    }

    @Test
    @DisplayName("HeavyAttack has multiplier 1.5")
    void heavyAttackHasMultiplierOnePointFive() {
        assertEquals(1.5,
                new HeavyAttack().getDamageMultiplier(hero, monsterwithHealth(100)));
    }

    @Test
    @DisplayName("Finisher deals double damage with enemy less than 20% health")
    void finisherDealsDoubleDamageAtLowHealth() {
        assertEquals(2.0,
                new SpecialAttack().getDamageMultiplier(hero, monsterwithHealth(20)));
        assertEquals(2.0,
                new SpecialAttack().getDamageMultiplier(hero, monsterwithHealth(10)));
    }

    @Test
    @DisplayName("Finisher deals normal damage with enmy above 20% health")
    void finisherDealsNormalDamageAboveLimit() {
        assertEquals(1.0,
                new SpecialAttack().getDamageMultiplier(hero, monsterwithHealth(100)));
        assertEquals(1.0,
                new SpecialAttack().getDamageMultiplier(hero, monsterwithHealth(50)));
    }

}