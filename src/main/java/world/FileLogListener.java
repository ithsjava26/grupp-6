package world;

import common.GameEventListener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

// A listener that writes every game event to a log file
public class FileLogListener implements GameEventListener {
    private final Path file;

    public FileLogListener(Path file) {
        this.file = file;
    }

    @Override
    public void onEvent(String message) {
        String line = LocalDateTime.now() + " - " + message + System.lineSeparator();
        try {
            Files.writeString(file, line,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            IO.println("Could not write to log file: " + e.getMessage());
        }
    }
}