package items;

import common.Item;
import common.ItemType;

public record Armor(String name, int level, int defenseBonus) implements Item {

    public Armor {
        ItemRules.requireValidBasics(name, level);
        if (defenseBonus < 0) {
            throw new IllegalArgumentException("Defense bonus cannot be negative");
        }
    }

    @Override
    public ItemType type() {
        return ItemType.ARMOR;
    }
}