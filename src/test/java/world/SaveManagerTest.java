package world;

import common.Difficulty;
import common.ItemType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SaveManagerTest {

    @TempDir
    Path tempDir;

    private final SaveManager saveManager = new SaveManager();

    @Test
    void savedGameCanBeLoadedAgain() throws IOException {
        Path file = tempDir.resolve("save.txt");
        SaveData original = new SaveData("Anna", 80, 120, Difficulty.NORMAL, 3,
                List.of(new SaveData.SavedItem(ItemType.WEAPON, 2),
                        new SaveData.SavedItem(ItemType.POTION, 1)));

        saveManager.save(file, original);
        SaveData loaded = saveManager.load(file);

        assertEquals(original, loaded);
    }

    @Test
    void loadingFileWithMissingFieldThrowsIOException() throws IOException {
        Path file = tempDir.resolve("broken.txt");
        Files.write(file, List.of("name=Anna", "health=80"));

        assertThrows(IOException.class, () -> saveManager.load(file));
    }

    @Test
    void loadingFileWithInvalidNumberThrowsIOException() throws IOException {
        Path file = tempDir.resolve("broken.txt");
        Files.write(file, List.of(
                "name=Anna", "health=abc", "gold=10", "difficulty=EASY", "room=1"));

        assertThrows(IOException.class, () -> saveManager.load(file));
    }
}