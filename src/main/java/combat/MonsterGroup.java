package combat;

import common.Enemy;

import java.util.List;

public class MonsterGroup implements Enemy {
    private final List<Monster> monsters;

    public MonsterGroup(List<Monster> monsters) {
        if (monsters == null || monsters.isEmpty()) {
            throw new IllegalArgumentException("A group needs atleast one Monster");
        }
        this.monsters = List.copyOf(monsters); //own copy so nothing outside of this can change it
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    @Override
    public String getName() {
        return "A Pack of " + monsters.size() + "monsters appear!";
    }

    @Override
    public boolean isBoss() {
        return false;
    }

    @Override
    public int getReward() {
        int total = 0;
        for (Monster monster : monsters) {
            total += monster.getReward();
        }
        return total;
    }

    @Override
    public int getHealth() {
        int total = 0;
        for (Monster monster : monsters) {
            total += monster.getHealth();
        }
        return total;
    }

    @Override
    public int getMaxHealth() {
        int total = 0;
        for (Monster monster : monsters) {
            total += monster.getMaxHealth();
        }
        return total;
    }

    @Override
    public int getAttack() {
        Monster currentMonster = getCurrentMonster();
        if (currentMonster == null) {
            return 0;//If everything is dead return 0 attack!
        }
        return currentMonster.getAttack();
    }

    private Monster getCurrentMonster() {
        for (Monster monster : monsters) {
            if (monster.isAlive()) {
                return monster;
            }
        }
        return null;
    }

    @Override
    public void takeDamage(int amount) {
        Monster currentMonster = getCurrentMonster();
        if (currentMonster != null) {
            currentMonster.takeDamage(amount);
        }
    }
}
