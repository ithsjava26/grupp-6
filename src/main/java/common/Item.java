package common;

public interface Item {
    String name();

    ItemType type();

    int level();

    default int attackBonus() {
        return 0;
    }

    default int defenseBonus() {
        return 0;
    }

    default int healAmount() {
        return 0;
    }
}