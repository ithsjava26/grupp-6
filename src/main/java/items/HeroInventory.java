package items;

import common.Inventory;
import common.Item;
import common.ItemType;

import java.util.*;
import java.util.function.ToIntFunction;

public final class HeroInventory implements Inventory {

    private final List<Item> items = new ArrayList<>();

    @Override
    public void add(Item item) {
        Objects.requireNonNull(item, "Item cannot be null");
        items.add(item);
    }

    @Override
    public boolean remove(Item item) {
        return items.remove(item);
    }

    @Override
    public List<Item> getItems() {
        return List.copyOf(items);
    }

    @Override
    public List<Item> getItemsOfType(ItemType type) {
        Objects.requireNonNull(type, "Item type cannot be null");
        return items.stream()
                .filter(item -> item.type() == type)
                .toList();
    }

    public <T extends Item> List<T> getItemsOfClass(Class<T> itemClass) {
        Objects.requireNonNull(itemClass, "Item class cannot be null");
        return items.stream()
                .filter(itemClass::isInstance)
                .map(itemClass::cast)
                .toList();
    }

    @Override
    public Optional<Item> getEquippedWeapon() {
        return findBest(ItemType.WEAPON, Item::attackBonus);
    }

    @Override
    public Optional<Item> getEquippedArmor() {
        return findBest(ItemType.ARMOR, Item::defenseBonus);
    }

    private Optional<Item> findBest(ItemType type, ToIntFunction<Item> bonus) {
        return items.stream()
                .filter(item -> item.type() == type)
                .max(Comparator.comparingInt(bonus));
    }
}