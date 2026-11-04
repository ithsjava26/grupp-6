package combat;

public class GameGuide {
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
                case "1" -> IO.println(gameText());
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
                "\t---GAME GUIDE--- \n" + BAR + "\nWhat do you want to know?" +
                "\n1. 🎮 Game " +
                "\n2. 👾 Monster " +
                "\n3. ⚔️ Attacks " +
                "\n4. 🛡️ Equipment " +
                "\n0. 🔙 Back\n";
    }

    static String gameText() {
        return "";
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
