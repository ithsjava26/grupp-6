package combat;

import common.Difficulty;
import common.Enemy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyFactoryTest {

    @Test
    @DisplayName("Room 1 -4 never creates a Boss")
    void NoMonsterInRoomOneToFour() {
        for (int room = 1; room < EnemyFactory.BOSS_ROOM; room++) {
            for (int i = 0; i < 20; i++) {
                assertFalse(EnemyFactory.createEnemy(room, Difficulty.NORMAL).isBoss());
            }
        }
    }

    @Test
    @DisplayName("Room 5 gives the Boss")
    void RoomFiveIsTheBoss() {
        Enemy enemy = EnemyFactory.createEnemy(EnemyFactory.BOSS_ROOM, Difficulty.NORMAL);

        assertTrue(enemy.isBoss());
    }

    @Test
    @DisplayName("Enemies start with full health")
    void enemiesStartWithFullHealth() {
        Enemy enemy = EnemyFactory.createEnemy(1, Difficulty.NORMAL);

        assertEquals(enemy.getMaxHealth(), enemy.getHealth());
    }

    @Test
    @DisplayName("Room 1 -4 gives the right number of Monsters")
    void roomOneToFourGiveRightNumbersOfMonsters() {
        for (int room = 1; room < EnemyFactory.BOSS_ROOM; room++) {
            for (int i = 0; i < 50; i++) {
                MonsterGroup group = (MonsterGroup) EnemyFactory.createEnemy(room, Difficulty.NORMAL);
                int count = group.getMonsters().size();

                assertTrue(count >= EnemyFactory.minMonster(room));
                assertTrue(count <= EnemyFactory.maxMonster(room));
            }
        }
    }

    @Test
    @DisplayName("Room number below 1 throws")
    void roomNumberBelowOneThrows() {
        assertThrows(IllegalArgumentException.class, () -> EnemyFactory.createEnemy(0, Difficulty.NORMAL));
    }

}