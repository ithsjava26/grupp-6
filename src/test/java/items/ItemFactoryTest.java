package items;

import common.Item;
import common.ItemType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class ItemFactoryTest {

    @Test
    void createsCorrectClassForEachType() {
        assertInstanceOf(Weapon.class, ItemFactory.create(ItemType.WEAPON, 1));
        assertInstanceOf(Armor.class, ItemFactory.create(ItemType.ARMOR, 1));
        assertInstanceOf(Potion.class, ItemFactory.create(ItemType.POTION, 1));
    }

    @Test
    void weaponOnlyGivesAttack() {
        Item weapon = ItemFactory.create(ItemType.WEAPON, 2);

        assertTrue(weapon.attackBonus() > 0);
        assertEquals(0, weapon.defenseBonus());
        assertEquals(0, weapon.healAmount());
    }

    @ParameterizedTest
    @EnumSource(ItemType.class)
    void sameTypeAndLevelGivesEqualItems(ItemType type) {
        Item first = ItemFactory.create(type, 3);
        Item second = ItemFactory.create(type, 3);

        assertEquals(first, second);
    }

    @ParameterizedTest
    @EnumSource(ItemType.class)
    void everyLevelUpToMaxCanBeCreated(ItemType type) {
        for (int level = 1; level <= ItemFactory.MAX_LEVEL; level++) {
            Item item = ItemFactory.create(type, level);

            assertEquals(type, item.type());
            assertEquals(level, item.level());
        }
    }

    @ParameterizedTest
    @EnumSource(ItemType.class)
    void higherLevelIsAlwaysStronger(ItemType type) {
        for (int level = 2; level <= ItemFactory.MAX_LEVEL; level++) {
            int previous = power(ItemFactory.create(type, level - 1));
            int current = power(ItemFactory.create(type, level));

            assertTrue(current > previous,
                    type + " level " + level + " should be stronger than level " + (level - 1));
        }
    }

    @ParameterizedTest
    @EnumSource(ItemType.class)
    void levelAboveMaxGivesMaxLevelItem(ItemType type) {
        Item item = ItemFactory.create(type, ItemFactory.MAX_LEVEL + 3);

        assertEquals(ItemFactory.create(type, ItemFactory.MAX_LEVEL), item);
    }

    @Test
    void levelBelowOneThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> ItemFactory.create(ItemType.WEAPON, 0));
    }

    @Test
    void nullTypeThrows() {
        assertThrows(NullPointerException.class,
                () -> ItemFactory.create(null, 1));
    }

    private static int power(Item item) {
        return item.attackBonus() + item.defenseBonus() + item.healAmount();
    }
}