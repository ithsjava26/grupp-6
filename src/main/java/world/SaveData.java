package world;

import common.Difficulty;
import common.ItemType;

import java.util.List;

public record SaveData(String name, int health, int gold, Difficulty difficulty,
                       int room, List<SavedItem> items) {

    public record SavedItem(ItemType type, int level) {
    }

    public SaveData {
        items = List.copyOf(items);
    }
}
