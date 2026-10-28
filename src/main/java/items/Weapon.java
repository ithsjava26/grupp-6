package items;

import common.Item;
import common.ItemType;

public record Weapon(String name, int level, int attackBonus) implements Item {

    public Weapon {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (level < 1) {
            throw new IllegalArgumentException("Level must be at least 1");
        }
        if (attackBonus < 0) {
            throw new IllegalArgumentException("Attack bonus cannot be negative");
        }
    }

    @Override
    public ItemType type() {
        return ItemType.WEAPON;
    }
}