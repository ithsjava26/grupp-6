package combat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MonsterGroupTest {
    Monster rat() {
        return new Monster(
                "Rat",
                10,
                3,
                false,
                4,
                new BaseAttack(),
                10);
    }

    Monster goblin() {
        return new Monster("Goblin",
                20,
                5,
                false,
                6,
                new BaseAttack(),
                20);
    }

    MonsterGroup group() {
        return new MonsterGroup(List.of(rat(), goblin()));
    }

    @Test
    @DisplayName("Health and reward are the sum of all Monsters")
    void healthAndRewardAreTheSumOfAllMonsters() {
        MonsterGroup group = group();

        assertEquals(30, group.getHealth());
        assertEquals(30, group.getMaxHealth());
        assertEquals(10, group.getReward());
    }

    @Test
    @DisplayName("Damage hits the first living Monster")
    void damageHitsTheFirstLivingMonster() {
        MonsterGroup group = group();
        group.takeDamage(4);

        assertEquals(6, group.getMonsters().getFirst().getHealth());
        assertEquals(20, group.getMonsters().getLast().getHealth());
    }

    @Test
    @DisplayName("Group attack is the attack of the current Monster")
    void groupAttackIsTheAttackOfTheCurrentMonster() {
        MonsterGroup group = group();
        int attack = group.getAttack();
        assertEquals(3, attack);

        group.takeDamage(10);
        int attack2 = group.getAttack();
        assertEquals(5, attack2);
    }

    @Test
    @DisplayName("Group is dead when all Monsters are Dead")
    void groupIsDeadWhenAllMonstersAreDead() {
        MonsterGroup group = group();
        group.takeDamage(10);
        group.takeDamage(20);

        assertFalse(group.isAlive());
        assertEquals(0, group.getAttack());
    }

    @Test
    @DisplayName("An empty group throws")
    void anEmptyGroupThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new MonsterGroup(List.of()));
    }

}