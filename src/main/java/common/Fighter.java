package common;
//Used by Enemy and player
public interface Fighter {
    String getname();
    int getHealth();
    int getMaxHealth();
    int getAttack();
    void takeDamage(int amount);

    default boolean isAlive() {
        return getHealth() >0;
    }
}
