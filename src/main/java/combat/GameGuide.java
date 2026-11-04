package combat;

public class GameGuide {

    static void main() {
        guideMenu();
    }
    private static final String BAR = "=============================";

    //GAME GUIDE, Game, Monster, Attacks, Equipment

    private GameGuide() {
    }

    public static void guideMenu() {
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
                    IO.readln("🔙 If you want to go back press enter");
                }
                case "2" -> IO.println(monsterText());
                case "3" -> IO.println(attackText());
                case "4" -> IO.println(equipmentText());
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
                "\n3. ⚔️ Attacks " +
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
        return "";
    }

    static String attackText() {
        return "";
    }

    static String equipmentText() {
        return "";
    }


}
