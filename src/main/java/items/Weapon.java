package items;

import common.Item;
import common.ItemType;

public record Weapon(String name, int level, int attackBonus) implements Item {

    public Weapon {
        ItemRules.requireValidBasics(name, level);
        if (attackBonus < 0) {
            throw new IllegalArgumentException("Attack bonus cannot be negative");
        }
    }

    @Override
    public ItemType type() {
        return ItemType.WEAPON;
    }
}