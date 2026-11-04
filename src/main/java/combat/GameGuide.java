package combat;

import common.Item;
import common.ItemType;
import items.ItemFactory;

public class GameGuide {

    static void main() {
        show();
    }

    private static final String BAR = "=============================================";

    //GAME GUIDE, Game, Monster, Attacks, Equipment

    private GameGuide() {
    }

    public static void show() {
        boolean running = true;
        while (running) {
            IO.println(menuText());
            String choice = IO.readln("👉 Choose your option: ");
            if (choice == null) {
                choice = "0";
            }

            switch (choice.trim()) {
                case "1" -> {
                    IO.println(gameText());
                    IO.println(BAR);
                    IO.readln("🔙 If you want to go back press enter");
                }
                case "2" -> {
                    IO.println(monsterText());
                    IO.println(BAR);
                    IO.readln("🔙 If you want to go back press enter");
                }
                case "3" -> {
                    IO.println(attackText());
                    IO.println(BAR);
                    IO.readln("🔙 If you want to go back press enter");
                }
                case "4" -> {
                    IO.println(equipmentText());
                    IO.println(BAR);
                    IO.readln("🔙 If you want to go back press enter");
                }
                case "0" -> running = false;
                default -> IO.println("❌ Please choose a number between 1-4!\n");
            }
        }
    }

    static String menuText() {
        return "\n" + BAR + "\n \tBELOW THE SURFACE \n " +
                "\t--- GAME GUIDE --- \n" + BAR + "\nWhat do you want to know?" +
                "\n1. 🎮 Game " +
                "\n2. 👾 Monster " +
                "\n3. ⚔️ Player " +
                "\n4. 🛡️ Equipment " +
                "\n0. 🔙 Back\n";
    }

    static String gameText() {
        return "\t ---THE GAME--- \n"
                + "📜 The Lore\n "
                + "Deep below the surface lies a prision that was never meant to be found. \n"
                + "For centuries, the Warden dragged souls into the darkness of his prision, \n"
                + "guardet by monsters that were once prisoners themselfes. \n"
                + "Village by village, people were silenced, their bodies left behind without a soul. \n"
                + "No one knows how to stop it, except you! "
                + "Go into the dark, break the curse and end the Warden's hunger,\n"
                + "before it caputes the soul of the once you love!"
                + "\n🏰 The dungeon\n"
                + "There are " + EnemyFactory.BOSS_ROOM + "rooms. "
                + "The Boss is waiting for you in room " + EnemyFactory.BOSS_ROOM
                + ".\n⚔️ Combat "
                + "You and the enemy take turns and you always start.\n" +
                "In your turn you pick an attack\n" +
                "💰 If you win you get Gold from the monsters.\n" +
                "💀 If your HP reach 0 it's game over for you!\n" +
                "🚪 Between Rooms" +
                "-> go to the next room\n" +
                "-> visit the shop\n" +
                "-> loop at your equipment\n" +
                "-> drink a potion\n" +
                "-> save your game\n" +
                "\n💡 Tip: drink a potion BEFORE the next room. \n" +
                "It is not possible to drink in a fight!\n";
    }

    static String monsterText() {
        return "\t ---MONSTER---\n" +
                "Every room before the boss is full of monsters.\n" +
                "You fight them one by one!\n" +
                "❗Your HP is NOT restored after suceeding a room, so be carefull!\n" +
                "👹 Who you can meet (normal Difficulty)\n" +
                EnemyFactory.monsterInfo() + EnemyFactory.bossInfo() +
                "\n👑 The boss will be waiting for you in " + EnemyFactory.BOSS_ROOM +
                "He is much stronger than the others, so watch out!\n";
    }

    static String attackText() {
        return "\t---PLAYER---\n" +
                "📊 Starting stats of the player:\n" +
                " ❤️ HP:   " + Player.MAX_HEALTH + "\n" +
                " ⚔️ ATK:  " + Player.START_ATTACK + "\n" +
                " 💰 GOLD: " + Player.START_GOLD + "\n" +
                "\n 🎯 Player Attacks:\n" +
                attackLine(" ⚔️ ", new BaseAttack(), 1.0) +
                attackLine(" 💥 ", new HeavyAttack(), 1.5) +
                attackLine(" 🗡️ ", new SpecialAttack(), 2.0) +
                " 💡 Buy better weapons and armor in the shop to get stronger!";
    }

    private static String attackLine(String icon, AttackStrategy attack, double multiplier) {
        double damage = Math.round(Player.START_ATTACK * multiplier);
        return " " + icon + " " + attack.getName() + "| ⚔️ " + damage + " damage \n" +
                attack.getDescription() + "\n";
    }

    static String equipmentText() {
        return "\t ---EQUIPMENT---\n" +
                " 🗡️ Weapons (more damage on every attack)\n" +
                itemLine(ItemType.WEAPON) +
                "\n 🛡️ Armor (blocks damage on every hit)\n" +
                itemLine(ItemType.ARMOR) +
                "\n 🧪 Potion (heal you, only between rooms!)\n" +
                itemLine(ItemType.POTION) +
                "\n 💡 Check shop as often as possible, better knife better life!";
    }

    private static String itemLine(ItemType type) {
        String lines = BAR + "\n";
        for (int level = 1; level <= ItemFactory.MAX_LEVEL; level++) {
            Item item = ItemFactory.create(type, level);
            lines += String.format(" %d. %-16s %s%n", level, item.name(), effect(item, type));
        }
        return lines;
    }

    private static String effect(Item item, ItemType type) {
        return switch (type) {
            case WEAPON -> "adds " + item.attackBonus() + " damage";
            case ARMOR -> "blocks " + item.defenseBonus() + " damage";
            case POTION -> "heals " + item.healAmount() + " HP";
        };
    }


}
