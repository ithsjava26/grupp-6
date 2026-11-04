package combat;

public class GameGuide {

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
        return "";
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
