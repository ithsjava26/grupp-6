package world;

import common.*;

import java.util.List;

public class ConsoleUI {

    public void showStatus(Hero hero) {
        IO.println(hero.getName() + " - HP: " + hero.getHealth() + "/" + hero.getMaxHealth() +
                " - Gold: " + hero.getGold());
    }

    public void showRoom(int room, int totalRooms) {
        IO.println();
        IO.println("--- Room " + room + " of " + totalRooms + " ---");
    }

    public void showEnemy(Enemy enemy) {
        String title = enemy.isBoss() ? "BOSS " : "";
        IO.println(title + enemy.getName());
        IO.println("HP: " + enemy.getHealth() + "/" + enemy.getMaxHealth() +
                " - Attack: " + enemy.getAttack());
    }

    public void showGameOver() {
        IO.println();
        IO.println("You have fallen. GAME OVER.");
    }

    public void showVictory() {
        IO.println();
        IO.println("You cleared the dungeon. VICTORY!");
    }

    public void showMainMenu() {
        IO.println();
        IO.println("=== BELOW THE SURFACE ===");
        IO.println("1. New game");
        IO.println("2. Load game");
        IO.println("3. Quit");
    }

    public void showRoomMenu(int nextRoom) {
        IO.println();
        IO.println("=== BEFORE ROOM " + nextRoom + " ===");
        IO.println("1. Continue to room " + nextRoom);
        IO.println("2. Shop");
        IO.println("3. Show equipment");
        IO.println("4. Use potion");
        IO.println("5. Save game");
        IO.println("6. Quit to main menu");
    }

    // Shows the weapon and armor the hero is currently using
    public void showEquipment(Hero hero) {
        Inventory inventory = hero.getInventory();

        IO.println("--- Equipment ---");

        inventory.getEquippedWeapon().ifPresentOrElse(
                weapon -> IO.println("Weapon: " + weapon.name()
                        + " (level " + weapon.level()
                        + ", attack +" + weapon.attackBonus() + ")"),
                () -> IO.println("Weapon: none"));

        inventory.getEquippedArmor().ifPresentOrElse(
                armor -> IO.println("Armor: " + armor.name()
                        + " (level " + armor.level()
                        + ", defense +" + armor.defenseBonus() + ")"),
                () -> IO.println("Armor: none"));
    }

    // Uses the first potion in the inventory to heal the hero
    public void usePotion(Hero hero) {
        Inventory inventory = hero.getInventory();
        List<Item> potions = inventory.getItemsOfType(ItemType.POTION);

        if (potions.isEmpty()) {
            IO.println("You have no potions.");
            return;
        }

        Item potion = potions.get(0);
        int hpBefore = hero.getHealth();

        hero.heal(potion.healAmount());
        inventory.remove(potion);

        IO.println("You used " + potion.name() + ". HP: "
                + hpBefore + " -> " + hero.getHealth() + "/" + hero.getMaxHealth());
    }

    public String askForName() {
        while (true) {
            String name = IO.readln("What is your hero's name? ").trim();
            if (!name.isEmpty()) {
                return name;
            }
            IO.println("The name can't be empty.");
        }
    }

    public Difficulty askForDifficulty() {
        while (true) {
            IO.println("Choose difficulty:");
            IO.println("1. Easy");
            IO.println("2. Normal");
            IO.println("3. Hard");
            String choice = IO.readln("Choose: ").trim();

            switch (choice) {
                case "1" -> { return Difficulty.EASY; }
                case "2" -> { return Difficulty.NORMAL; }
                case "3" -> { return Difficulty.HARD; }
                default -> IO.println("Not a valid choice. Type 1, 2 or 3.");
            }
        }
    }
}


