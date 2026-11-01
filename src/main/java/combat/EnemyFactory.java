package combat;

import common.Difficulty;
import common.Enemy;

import java.util.Objects;
import java.util.Random;

public class EnemyFactory {
    public static final int BOSS_ROOM = 5;

    private static final double ROOM_DIFFICULLTY = 0.15;
    private static final Random RANDOM = new Random();

    private record Layout(String name, int health, int attack, int reward, AttackStrategy strategy) {
    }

    private static final Layout[] SMALL_MONSTER = {
            new Layout("Goblin", 15, 2, 1, new MonsterAttack()),
            new Layout("Night crawler", 30, 10, 6, new MonsterAttack()),
            new Layout("Skelleton", 3, 6, 2, new MonsterAttack()),
            new Layout("Ironhide", 40, 6, 15, new MonsterAttack()),
            new Layout("Shadowreaper", 10, 9, 10, new MonsterAttack())
    };

    private static final Layout BOSS = new Layout("Warden", 80, 15, 60, new MonsterAttack());

    private EnemyFactory() {
    }

    public static Enemy createEnemy(int roomNumber, Difficulty difficulty) {
        if (roomNumber < 1) {
            throw new IllegalArgumentException("First room needs to start at room one!");
        }
        Objects.requireNonNull(difficulty, "Difficulty cannot be null!");

        boolean isBoss = roomNumber >= BOSS_ROOM;
        Layout layout;
        if (isBoss) {
            layout = BOSS;
        } else {
            layout = SMALL_MONSTER[RANDOM.nextInt(SMALL_MONSTER.length)];
        }
        //TODO: depending on what room more small enemies appear,
        // create Bossattack and heavy attack for monster,
        return new Monster(layout.name, layout.health, layout.attack, isBoss, layout.reward, layout.strategy, layout.health);
    }
}
