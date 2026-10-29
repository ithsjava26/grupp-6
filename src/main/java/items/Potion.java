package items;

import common.Item;
import common.ItemType;

public record Potion(String name, int level, int healAmount) implements Item {

    public Potion {
        if (healAmount < 1) {
            throw new IllegalArgumentException("Heal amount must be more than 0");
        }
    }

    @Override
    public ItemType type() {
        return ItemType.POTION;
    }
}