package combat;

import common.Hero;
import common.Inventory;

public class Player implements Hero {
    public static final int MAX_HEALTH = 100;
    public static final int START_ATTACK = 10;
    public static final int START_GOLD = 50;

    private final String name;
    private final int maxHealth;
    private int health;
    private final int normalAttack;
    private int gold;
    private final Inventory inventory;

    public Player(String name, int maxHealth, int health, int normalAttack,
                  int gold, Inventory inventory) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = Math.max(0, Math.min(health, maxHealth));
        this.normalAttack = normalAttack;
        this.gold = Math.max(0, gold);
        this.inventory = inventory;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getGold() {
        return gold;
    }

    @Override
    public void addGold(int amount) {
        if (amount <= 0) {
            return;
        }
        if (gold > Integer.MAX_VALUE - amount) {
            gold = Integer.MAX_VALUE;
        } else
        gold += amount;
    }

    @Override
    public boolean spendGold(int amount) {
        if (amount < 0 || amount > gold) {
            return false;
        }
        gold -= amount;
        return true;
    }

    @Override
    public void heal(int amount) {
        if (amount <= 0) {
            return;
        }
        if (amount >= maxHealth - health) {
            health = maxHealth;
        } else
            health += amount;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
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
    public int getAttack() {
        return normalAttack;
    }

    @Override
    public void takeDamage(int amount) {
        if (amount <= 0) {
            return;
        }
        health = Math.max(0, health - amount);
    }
}
