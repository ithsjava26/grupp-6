package common;

public interface Hero extends Fighter{
    int getGold();
    void addGold(double amount);

    boolean spendGold(double amount);

    void heal(int amount);
    Inventory getInventory();

}
