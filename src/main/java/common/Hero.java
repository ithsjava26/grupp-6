package common;

public interface Hero extends Fighter{
    int getGold();

    void addGold(int amount); // Changed double to int

    boolean spendGold(int amount); // Changed double to int

    void heal(int amount);
    Inventory getInventory();

}
