package world;

import common.Hero;

public class ConsoleUI {

    public void showStatus(Hero hero) {
        IO.println(hero.getName() + " - HP: " + hero.getHealth() + "/" + hero.getMaxHealth() +
                " - Gold: " + hero.getGold());
    }
}

