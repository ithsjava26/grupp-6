package world;

import combat.CombatCoordinator;
import common.Difficulty;
import common.Hero;
import common.Inventory;
import common.ItemType;
import items.ItemFactory;
import items.Shop;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SaveDataTest {

    @TempDir
    Path tempDir;

    private final CombatCoordinator fighter = new CombatCoordinator();
    private final Shop shop = new Shop();
    private final SaveManager saveManager = new SaveManager();

    @Test
    void heroIsRestoredAfterSaveAndLoad() throws IOException {
        Inventory inventory = shop.createInventory();
        inventory.add(ItemFactory.create(ItemType.WEAPON, 2));
        inventory.add(ItemFactory.create(ItemType.POTION, 1));
        Hero hero = fighter.loadHero("Anna", 80, 120, inventory);

        Path file = tempDir.resolve("save.txt");
        saveManager.save(file, SaveData.from(hero, Difficulty.HARD, 3));
        SaveData loaded = saveManager.load(file);
        Hero restored = loaded.createHero(fighter, shop);

        assertEquals("Anna", restored.getName());
        assertEquals(80, restored.getHealth());
        assertEquals(120, restored.getGold());
        assertEquals(Difficulty.HARD, loaded.difficulty());
        assertEquals(3, loaded.room());
        assertEquals(hero.getInventory().getItems(), restored.getInventory().getItems());
    }
}