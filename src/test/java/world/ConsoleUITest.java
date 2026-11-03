package world;

import combat.CombatCoordinator;
import common.Hero;
import common.Inventory;
import common.Item;
import common.ItemType;
import items.ItemFactory;
import items.Shop;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleUITest {

    private final CombatCoordinator fighter = new CombatCoordinator();
    private final Shop shop = new Shop();
    private final ConsoleUI ui = new ConsoleUI();

    @Test
    void usePotionHealsHeroAndRemovesPotion() {
        Inventory inventory = shop.createInventory();
        Item potion = ItemFactory.create(ItemType.POTION, 1);
        inventory.add(potion);
        Hero hero = fighter.loadHero("Anna", 80, 0, inventory);

        ui.usePotion(hero);

        int expectedHealth = Math.min(hero.getMaxHealth(), 80 + potion.healAmount());
        assertEquals(expectedHealth, hero.getHealth());
        assertEquals(0, inventory.getItemsOfType(ItemType.POTION).size());
    }

    @Test
    void usePotionWithoutPotionChangesNothing() {
        Inventory inventory = shop.createInventory();
        Hero hero = fighter.loadHero("Anna", 80, 0, inventory);

        ui.usePotion(hero);

        assertEquals(80, hero.getHealth());
    }

    @Test
    void usePotionOnlyUsesOnePotion() {
        Inventory inventory = shop.createInventory();
        inventory.add(ItemFactory.create(ItemType.POTION, 1));
        inventory.add(ItemFactory.create(ItemType.POTION, 1));
        Hero hero = fighter.loadHero("Anna", 50, 0, inventory);

        ui.usePotion(hero);

        assertEquals(1, inventory.getItemsOfType(ItemType.POTION).size());
    }
}