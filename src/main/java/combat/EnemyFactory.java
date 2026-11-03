package combat;

import common.Difficulty;
import common.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class EnemyFactory {
    public static final int BOSS_ROOM = 5;
    private static final Random RANDOM = new Random();

    private record Layout(String name, int health, int attack, int reward, AttackStrategy strategy) {
    }

    private static final Layout[] SMALL_MONSTER = {
            new Layout("Goblin", 15, 2, 1, new MonsterAttack()),
            new Layout("Night crawler", 30, 10, 6, new MonsterAttack()),
            new Layout("Skelleton", 3, 6, 2, new MonsterAttack()),
            new Layout("Ironhide", 40, 9, 15, new MonsterAttack()),
            new Layout("Shadowreaper", 10, 13, 10, new MonsterAttack())
    };

    private static final Layout BOSS = new Layout("Warden", 80, 15, 60, new MonsterSpecialBossAttack());

    private EnemyFactory() {
    }

    public static Enemy createEnemy(int roomNumber, Difficulty difficulty) {
        if (roomNumber < 1) {
            throw new IllegalArgumentException("First room needs to start at room one!");
        }
        Objects.requireNonNull(difficulty, "Difficulty cannot be null!");

        if (roomNumber >= BOSS_ROOM) {
            return create(BOSS, difficulty, true);
        }

        int count = randomMonsterCount(roomNumber);
        List<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Layout layout = SMALL_MONSTER[RANDOM.nextInt(SMALL_MONSTER.length)];
            monsters.add(create(layout, difficulty, false));
        }
        return new MonsterGroup(monsters);
    }

    static int minMonster(int roomNumber) {
        return roomNumber + 1;
    }

    static int maxMonster(int roomNumber) {
        return roomNumber * 2;
    }

    private static int randomMonsterCount(int roomNumber) {
        int min = minMonster(roomNumber);
        int max = maxMonster(roomNumber);
        return min + RANDOM.nextInt(max - min + 1);
    }

    private static Monster create(Layout layout, Difficulty difficulty, boolean isBoss) {
        int health = scale(layout.health(), difficulty);
        int attack = scale(layout.attack(), difficulty);
        int reward = scale(layout.reward(), difficulty);

        return new Monster(layout.name(), health, attack, isBoss, reward, layout.strategy(), health);
    }

    static int scale(int baseValue, Difficulty difficulty) {
        double difficultFactor = switch (difficulty) {
            case EASY -> 0.8;
            case NORMAL -> 1.0;
            case HARD -> 1.2;
        };
        return (int) Math.round(difficultFactor * baseValue);
    }


}
