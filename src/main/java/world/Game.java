package world;

import common.Difficulty;
import common.FighterInfo;
import common.Hero;
import common.Inventory;
import common.ShopInfo;
import common.GameLog;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

/*
The main logic. It shows the menu and controls the flow of the game, start to end.
Can get the combat and items modules through the interface in common.

Person C
-> Write the menu loop
-> Write the game flow (choose difficulty, fight and more)
-> Handle saving and loading from a file
 */

public class Game {
    private final FighterInfo fighter;
    private final ShopInfo shop;
    private final ConsoleUI ui = new ConsoleUI();
    private final GameLog log = new GameLog();
    private final EventPublisher events = new EventPublisher();
    private static final Path LOG_FILE = Path.of("game.log");
    private static final Path SAVE_FILE = Path.of("savegame.txt");
    private final SaveManager saveManager = new SaveManager();

    public Game(FighterInfo fighter, ShopInfo shop) {
        this.fighter = fighter;
        this.shop = shop;
        events.addListener(log);
        events.addListener(new FileLogListener(LOG_FILE));
    }

    public void run() {
        IO.println("Below the Surface is starting...");

        boolean running = true;
        while (running) {
            ui.showMainMenu();
            String input = IO.readln("Choose: ");
            if (input == null) {
                break;
            }
            String choice = input.trim();

            switch (choice) {
                case "1" -> newGame();
                case "2" -> loadGame();
                case "3" -> {
                    IO.println("Goodbye!");
                    running = false;
                }
                default -> IO.println("Not a valid choice. Type 1, 2 or 3.");
            }
        }
    }

    private void newGame() {
        String name = ui.askForName();
        Difficulty difficulty = ui.askForDifficulty();

        IO.println("Welcome, " + name + "! Difficulty: " + difficulty);

        try {
            Inventory inventory = shop.createInventory();
            Hero hero = fighter.createNewHero(name, inventory);
            ui.showStatus(hero);

            playGame(hero, difficulty, 1);
        } catch (UnsupportedOperationException e) {
            IO.println("Waiting for modules, change later");
        }
    }

    private void playGame(Hero hero, Difficulty difficulty, int startRoom) {
        Dungeon dungeon = new Dungeon(fighter, ui, events);

        for (int room = startRoom; room <= Dungeon.TOTAL_ROOMS; room++) {
            boolean heroWon = dungeon.playRoom(hero, difficulty, room);
            if (!heroWon) {
                return;
            }

            if (room < Dungeon.TOTAL_ROOMS && !roomMenu(hero, difficulty, room + 1)) {
                return;
            }
        }

        events.onEvent(hero.getName() + " cleared the dungeon");
        ui.showVictory();
    }

    // Returns true if the player wants to continue to the next room,
    // false if the player quits to the main menu
    private boolean roomMenu(Hero hero, Difficulty difficulty, int nextRoom) {
        while (true) {
            ui.showRoomMenu(nextRoom);
            String input = IO.readln("Choose: ");
            if (input == null) {
                return false;
            }

            switch (input.trim()) {
                case "1" -> {
                    return true;
                }
                case "2" -> IO.println("Shop is coming soon.");
                case "3" -> ui.showEquipment(hero);
                case "4" -> ui.usePotion(hero);
                case "5" -> saveGame(hero, difficulty, nextRoom);
                case "6" -> {
                    return false;
                }
                default -> IO.println("Not a valid choice. Type 1-6.");
            }
        }
    }

    private void saveGame(Hero hero, Difficulty difficulty, int nextRoom) {
        SaveData data = SaveData.from(hero, difficulty, nextRoom);

        try {
            saveManager.save(SAVE_FILE, data);
            IO.println("Game saved.");
            events.onEvent(hero.getName() + " saved the game");
        } catch (IOException e) {
            IO.println("Could not save the game: " + e.getMessage());
        }
    }

    private void loadGame() {
        if (!Files.exists(SAVE_FILE)) {
            IO.println("No saved game found.");
            return;
        }

        SaveData data;
        try {
            data = saveManager.load(SAVE_FILE);
        } catch (IOException e) {
            IO.println("Could not load the game: " + e.getMessage());
            return;
        }

        try {
            Hero hero = data.createHero(fighter, shop);

            events.onEvent(hero.getName() + " loaded the game");
            IO.println("Game loaded.");
            ui.showStatus(hero);

            playGame(hero, data.difficulty(), data.room());
        } catch (UnsupportedOperationException e) {
            IO.println("Waiting for modules, change later");
        }
    }
}