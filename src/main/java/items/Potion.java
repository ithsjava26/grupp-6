package items;

import common.Item;
import common.ItemType;

public record Potion(String name, int level, int healAmount) implements Item {

    public Potion {
        ItemRules.requireValidBasics(name, level);
        if (healAmount < 1) {
            throw new IllegalArgumentException("Heal amount must be at least 1");
        }
    }

    @Override
    public ItemType type() {
        return ItemType.POTION;
    }
}