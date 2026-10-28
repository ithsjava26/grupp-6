package common;

public interface Hero extends Fighter{
    int getGold();

    void addGold(int amount);

    boolean spendGold(int amount);

    void heal(int amount);
    Inventory getInventory();

}
