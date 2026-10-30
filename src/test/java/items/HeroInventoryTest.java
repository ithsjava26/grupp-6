package items;

import common.Item;
import common.ItemType;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class HeroInventoryTest {

    private final HeroInventory inventory = new HeroInventory();

    private final Item dagger = ItemFactory.create(ItemType.WEAPON, 1);
    private final Item strongWeapon = ItemFactory.create(ItemType.WEAPON, 3);
    private final Item weakArmor = ItemFactory.create(ItemType.ARMOR, 1);
    private final Item strongArmor = ItemFactory.create(ItemType.ARMOR, 4);
    private final Item potion = ItemFactory.create(ItemType.POTION, 1);

    @Test
    void newInventoryIsEmpty() {
        assertTrue(inventory.getItems().isEmpty());
    }

    @Test
    void addedItemCanBeFound() {
        inventory.add(dagger);

        assertEquals(List.of(dagger), inventory.getItems());
    }

    @Test
    void addingNullThrows() {
        assertThrows(NullPointerException.class, () -> inventory.add(null));
    }

    @Test
    void removeTakesAwayOnlyOneOfTwoEqualItems() {
        inventory.add(potion);
        inventory.add(potion);

        assertTrue(inventory.remove(potion));
        assertEquals(1, inventory.getItems().size());
    }

    @Test
    void removeReturnsFalseWhenItemIsMissing() {
        assertFalse(inventory.remove(dagger));
    }

    @Test
    void itemListCannotBeChangedFromOutside() {
        inventory.add(dagger);

        assertThrows(UnsupportedOperationException.class,
                () -> inventory.getItems().clear());
        assertEquals(1, inventory.getItems().size());
    }

    @Test
    void getItemsOfTypeReturnsOnlyThatType() {
        inventory.add(dagger);
        inventory.add(potion);
        inventory.add(weakArmor);
        inventory.add(potion);

        assertEquals(List.of(potion, potion), inventory.getItemsOfType(ItemType.POTION));
    }

    @Test
    void getItemsOfClassReturnsTypedList() {
        inventory.add(dagger);
        inventory.add(potion);

        List<Potion> potions = inventory.getItemsOfClass(Potion.class);

        assertEquals(1, potions.size());
        assertEquals(15, potions.get(0).healAmount());
    }

    @Test
    void noWeaponMeansNothingEquipped() {
        inventory.add(potion);

        assertEquals(Optional.empty(), inventory.getEquippedWeapon());
        assertEquals(Optional.empty(), inventory.getEquippedArmor());
    }

    @Test
    void strongestWeaponIsEquipped() {
        inventory.add(dagger);
        inventory.add(strongWeapon);

        assertEquals(Optional.of(strongWeapon), inventory.getEquippedWeapon());
    }

    @Test
    void strongestArmorIsEquipped() {
        inventory.add(strongArmor);
        inventory.add(weakArmor);

        assertEquals(Optional.of(strongArmor), inventory.getEquippedArmor());
    }

    @Test
    void removingEquippedWeaponEquipsNextBest() {
        inventory.add(dagger);
        inventory.add(strongWeapon);

        inventory.remove(strongWeapon);

        assertEquals(Optional.of(dagger), inventory.getEquippedWeapon());
    }
}