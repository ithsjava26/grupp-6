package common;
//Everything the "world" needs from the combat module:
// creating heroes and enemies, and running a battle.
public interface FighterInfo {

    Hero createNewHero(String name, Inventory inventory);

    Hero loadHero (String name, int health, int gold, Inventory inventory);

    Enemy createEnemy(int roomNumber, Difficulty difficulty);

    boolean fight (Hero hero, Enemy enemy); //True = Hero won, False = Enemy won

}
