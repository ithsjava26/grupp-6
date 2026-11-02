package world;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import common.Difficulty;
import common.ItemType;
import java.util.HashMap;
import java.util.Map;

public class SaveManager {

    public void save(Path file, SaveData data) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("name=" + data.name());
        lines.add("health=" + data.health());
        lines.add("gold=" + data.gold());
        lines.add("difficulty=" + data.difficulty());
        lines.add("room=" + data.room());
        for (SaveData.SavedItem item : data.items()) {
            lines.add("item=" + item.type() + "," + item.level());
        }
        Files.write(file, lines);
    }

    public SaveData load(Path file) throws IOException {
        List<String> lines = Files.readAllLines(file);
        try {
            return parse(lines);
        } catch (IllegalArgumentException e) {
            throw new IOException("Invalid save file: " + e.getMessage(), e);
        }
    }

    private SaveData parse(List<String> lines) {
        Map<String, String> values = new HashMap<>();
        List<SaveData.SavedItem> items = new ArrayList<>();

        for (String line : lines) {
            int split = line.indexOf('=');
            if (split < 0) {
                continue;
            }
            String key = line.substring(0, split);
            String value = line.substring(split + 1);
            if (key.equals("item")) {
                items.add(parseItem(value));
            } else {
                values.put(key, value);
            }
        }

        return new SaveData(
                require(values, "name"),
                Integer.parseInt(require(values, "health")),
                Integer.parseInt(require(values, "gold")),
                Difficulty.valueOf(require(values, "difficulty")),
                Integer.parseInt(require(values, "room")),
                items);
    }

    private SaveData.SavedItem parseItem(String value) {
        String[] parts = value.split(",");
        if (parts.length != 2) {
            throw new IllegalArgumentException("bad item: " + value);
        }
        return new SaveData.SavedItem(ItemType.valueOf(parts[0]), Integer.parseInt(parts[1]));
    }

    private String require(Map<String, String> values, String key) {
        String value = values.get(key);
        if (value == null) {
            throw new IllegalArgumentException("missing " + key);
        }
        return value;
    }
}