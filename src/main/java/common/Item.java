package common;

public interface Item {
    String name();

    ItemType type();

    int level();

    default int attackBonus() {
        return 0;
    } // instead of bonus

    default int defenseBonus() {
        return 0;
    } // instead of bonus

    default int healAmount() {
        return 0;
    }
}