package combat;

import common.Enemy;

public class Monster implements Enemy {

    private final String name;
    private final int maxHealth;
    private int health;
    private final int attack;
    private final boolean boss;
    private final int reward;
    private final AttackStrategy strategy; //Write a class for Attackstrategy

    public Monster(String name, int maxHealth, int attack, boolean boss, int reward,
                   AttackStrategy strategy, int health) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = health;
        this.attack = attack;
        this.boss = boss;
        this.reward = reward;
        this.strategy = strategy;
    }

    @Override
    public String getname() {
        return "";
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public boolean isBoss() {
        return boss;
    }

    @Override
    public int getReward() {
        return reward;
    }

    @Override
    public int getAttack() {
        return 0;
    }

    @Override
    public void takeDamage(int amount) {
        if (amount <= 0) {
            return;
        }
        health = health - amount;
    }

    public AttackStrategy getStrategy() {
        return strategy;
    }
}
