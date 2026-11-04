package world;

import common.Difficulty;
import common.ItemType;
import java.util.List;
import common.Hero;
import common.FighterInfo;
import common.Inventory;
import common.ShopInfo;

public record SaveData(String name, int health, int gold, Difficulty difficulty,
                       int room, List<SavedItem> items) {

    public record SavedItem(ItemType type, int level) {
    }

    public SaveData {
        items = List.copyOf(items);
    }

    public static SaveData from(Hero hero, Difficulty difficulty, int room) {
        List<SavedItem> items = hero.getInventory().getItems().stream()
                .map(item -> new SavedItem(item.type(), item.level()))
                .toList();
        return new SaveData(hero.getName(), hero.getHealth(), hero.getGold(),
                difficulty, room, items);
    }

    public Hero createHero(FighterInfo fighter, ShopInfo shop) {
        Inventory inventory = shop.createInventory();
        for (SavedItem saved : items) {
            inventory.add(shop.createItem(saved.type(), saved.level()));
        }
        return fighter.loadHero(name, health, gold, inventory);
    }
}
