package items;

import common.Item;
import common.ItemType;

import java.util.Objects;

public final class ItemFactory {

    public static final int MAX_LEVEL = 5;

    private record Template(String name, int bonus) {
    }

    private static final Template[] WEAPONS = {
            new Template("Dagger", 2),
            new Template("Morning Star", 5),
            new Template("Jagged Axe", 8),
            new Template("Abyssal Blade", 12),
            new Template("Magic Sword", 18)
    };

    private static final Template[] ARMORS = {
            new Template("Leather Vest", 1),
            new Template("Chainmail", 3),
            new Template("Plate Armor", 5),
            new Template("Elven Plate", 8),
            new Template("Obsidian Cuirass", 12)
    };

    private static final Template[] POTIONS = {
            new Template("Small Potion", 15),
            new Template("Potion", 30),
            new Template("Large Potion", 50),
            new Template("Greater Potion", 80),
            new Template("Elven Elixir", 120)
    };

    private ItemFactory() {
    }

    public static Item create(ItemType type, int level) {
        Objects.requireNonNull(type, "Item type cannot be null");
        if (level < 1) {
            throw new IllegalArgumentException("Level must be at least 1");
        }

        int tier = Math.min(level, MAX_LEVEL);
        int i = tier - 1;

        return switch (type) {
            case WEAPON -> new Weapon(WEAPONS[i].name(), tier, WEAPONS[i].bonus());
            case ARMOR -> new Armor(ARMORS[i].name(), tier, ARMORS[i].bonus());
            case POTION -> new Potion(POTIONS[i].name(), tier, POTIONS[i].bonus());
        };
    }
}