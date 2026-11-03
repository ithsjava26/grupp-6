package items;

import common.Hero;
import common.Inventory;

class FakeHero implements Hero {
    private final Inventory inventory;
    private int gold;

    FakeHero(int gold, Inventory inventory) {
        this.gold = gold;
        this.inventory = inventory;
    }

    @Override
    public int getGold() {
        return gold;
    }

    @Override
    public void addGold(int amount) {
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
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void heal(int amount) {
    }

    @Override
    public String getName() {
        return "Test Hero";
    }

    @Override
    public int getHealth() {
        return 100;
    }

    @Override
    public int getMaxHealth() {
        return 100;
    }

    @Override
    public int getAttack() {
        return 10;
    }

    @Override
    public void takeDamage(int amount) {
    }
}