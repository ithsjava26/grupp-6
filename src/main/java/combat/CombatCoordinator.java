package combat;

import common.*;

import java.util.List;
/*
Person C will call this class to create heroes and enemies and to run a fight
(it does not handle the work it sends the work to your own classes in your package!)

Person A Combat and Character

- write a Hero and Enemy class
- make enemies stronger in later rooms
- write the fight loop (Who attacks first and more)

 */

public class CombatCoordinator implements FighterInfo {
    private final AttackStrategy baseAttack = new BaseAttack();
    private final AttackStrategy heavyAttack = new HeavyAttack();
    private final AttackStrategy specialAttack = new SpecialAttack();


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
        List<Monster> monsters = group.getMonsters();

        int number = 0;
        for (Monster monster : monsters) {
            number++;
            IO.println("\n===Enemy " + number + "of " + monsters.size() + " ===");
            if (!fightOne(hero, monster)) {
                return false; //Hero lost
            }
        }
        IO.println("\nYou defeated all " + monsters.size() + "monsters!!");
        return true;//Hero won
    }

    private boolean fightOne(Hero hero, Enemy enemy) {
        while (hero.isAlive() && enemy.isAlive()) {
            heroTurn(hero, enemy);
            if (enemy.isAlive()) {
                enemyTurn(hero, enemy);
            }
            if (hero.isAlive() && enemy.isAlive()) {
                IO.println("Next Round!");
            }
        }
        if (hero.isAlive()) {
            IO.println("You defeated " + enemy.getName() + "!!");
            return true;
        } else {
            IO.println("You were defeated by " + enemy.getName() + "...");
            return false;
        }
    }

    private void heroTurn(Hero hero, Enemy enemy) {
        boolean turnUsed = false;

        while (!turnUsed) {
            IO.println("===your turn=== " +
                    "\n1. " + baseAttack.getName() +
                    "\n2. " + heavyAttack.getName() +
                    "\n3. " + specialAttack.getName());
            String choice = IO.readln("Choose your action: ");
            if (choice == null) {
                choice = "1";
            }
            switch (choice.trim()) {
                case "1" -> turnUsed = true;
                case "2" -> turnUsed = true;
                case "3" -> turnUsed = true;//Write attack method

            }

        }
    }

    private void attack(Fighter attacker, Fighter defender, AttackStrategy strategy) {
        int damage = DamageCalculator.calculate(strategy, attacker, defender);
        defender.takeDamage(damage);

        String text = strategy.getMessage(attacker) + " " + defender.getName()
                + " takes " + damage + "damage";
        int defense = DamageCalculator.getDefenseBonus(defender);
        if (defense > 0) {
            text = text + "your armor blocked " + defense + "";
        }
        IO.println("!");
    }

    //TODO: HeroTurn
    //TODO: Hero Options (switch)
    //TODO: Enemy turn
    //TODO: Enemy getAttack possibility
    //TODO: END FIGHT IF ONE OF THEM IS DEAD
}
