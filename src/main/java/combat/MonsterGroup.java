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
        return "A pack of " + monsters.size() + " monsters!";
    }

    @Override
    public boolean isBoss() {
        return false;
    }

    @Override
    public int getReward() {
        return monsters.stream()
                .mapToInt(Monster::getReward)
                .sum();
    }

    @Override
    public int getHealth() {
        return monsters.stream()
                .mapToInt(Monster::getHealth)
                .sum();
    }

    @Override
    public int getMaxHealth() {
        return monsters.stream()
                .mapToInt(Monster::getMaxHealth)
                .sum();
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
        return monsters.stream()
                .filter(Monster::isAlive)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void takeDamage(int amount) {
        Monster currentMonster = getCurrentMonster();
        if (currentMonster != null) {
            currentMonster.takeDamage(amount);
        }
    }
}
