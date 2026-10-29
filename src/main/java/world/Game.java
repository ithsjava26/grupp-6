package world;

import common.Difficulty;
import common.FighterInfo;
import common.Hero;
import common.Inventory;
import common.ShopInfo;
import common.GameLog;

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

    public Game(FighterInfo fighter, ShopInfo shop) {
        this.fighter = fighter;
        this.shop = shop;
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
                case "2" -> IO.println("Load game is coming soon.");
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

            Dungeon dungeon = new Dungeon(fighter, ui, log);
            dungeon.run(hero, difficulty);
        } catch (UnsupportedOperationException e) {
            IO.println("Waiting for modules, change later");
        }
    }
}