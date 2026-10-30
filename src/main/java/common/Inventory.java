package common;

import java.util.List;
import java.util.Optional;

public interface Inventory {
    void add(Item item);

    boolean remove(Item item);

    List<Item> getItems();

    // All items of the given type
    List<Item> getItemsOfType(ItemType type);

    // The weapon with the highest attack bonus, or empty if there is none
    Optional<Item> getEquippedWeapon();

    // The armor with the highest defense bonus, or empty if there is none
    Optional<Item> getEquippedArmor();
}
