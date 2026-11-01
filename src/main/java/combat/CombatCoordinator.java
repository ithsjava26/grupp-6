package combat;

import common.*;
/*
Person C will call this class to create heroes and enemies and to run a fight
(it does not handle the work it sends the work to your own classes in your package!)

Person A Combat and Character

- write a Hero and Enemy class
- make enemies stronger in later rooms
- write the fight loop (Who attacks first and more)

 */

public class CombatCoordinator implements FighterInfo {


    @Override
    public Hero createNewHero(String name, Inventory inventory) {
        return new Player(name, Player.MAX_HEALTH, Player.MAX_HEALTH,
                Player.START_ATTACK, Player.START_GOLD, inventory);
    }

    @Override
    public Hero loadHero(String name, int health, int gold, Inventory inventory) {
        return new Player(name, Player.MAX_HEALTH, health,
                Player.START_ATTACK, gold, inventory);
    }

    @Override
    public Enemy createEnemy(int roomNumber, Difficulty difficulty) {
        return EnemyFactory.createEnemy(roomNumber, difficulty);
    }

    @Override
    public boolean fight(Hero hero, Enemy enemy) {
        if (enemy instanceof MonsterGroup group) {
            return fightGroup(hero, group); //if its a group then fight monster one by one
        }
        return fightOne(hero, enemy);//if its not a group fight only one(Boss)
    }

    private boolean fightGroup(Hero hero, MonsterGroup group) {
        return false;
    }

    private boolean fightOne(Hero hero, Enemy enemy) {
        return false;
    }

    //TODO: HeroTurn
    //TODO: Hero Options (switch)
    //TODO: Enemy turn
    //TODO: Enemy getAttack possibility
    //TODO: END FIGHT IF ONE OF THEM IS DEAD
}
