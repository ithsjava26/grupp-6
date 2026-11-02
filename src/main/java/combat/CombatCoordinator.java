package combat;

import common.*;

import java.util.List;
import java.util.stream.IntStream;
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
        printGroup(monsters);

        int number = 0;
        for (Monster monster : monsters) {
            number++;
            IO.println("\n---Enemy " + number + " of " + monsters.size() + " ---");
            if (!fightOne(hero, monster)) {
                return false; //Hero lost
            }
        }

        return true;//Hero won
    }

    private void printGroup(List<Monster> monsters) {
        IO.println("\nEnemies in this Room: ");
        IntStream.range(0, monsters.size())
                .forEach(i -> IO.println(" " + (i + 1) + ". "
                        + "%-20s".formatted(monsters.get(i).getName())
                        + " (HP " + monsters.get(i).getHealth()
                        + ", ATK " + monsters.get(i).getAttack() + ")"));
    }

    private boolean fightOne(Hero hero, Enemy enemy) {
        printStatus(hero, enemy);
        while (hero.isAlive() && enemy.isAlive()) {
            heroTurn(hero, enemy);
            if (enemy.isAlive()) {
                enemyTurn(hero, enemy);
            }
            if (hero.isAlive() && enemy.isAlive()) {
                printStatus(hero, enemy);
            }
        }
        if (hero.isAlive()) {
            IO.println("\nYou defeated " + enemy.getName() + "!");
            return true;
        } else {
            return false;
        }
    }

    private void heroTurn(Hero hero, Enemy enemy) {
        boolean turnUsed = false;

        while (!turnUsed) {
            printHeroMenu(hero);
            String choice = IO.readln("Choose your action: ");
            if (choice == null) {
                choice = "1";
            }
            switch (choice.trim()) {
                case "1" -> {
                    attack(hero, enemy, baseAttack);
                    turnUsed = true;
                }
                case "2" -> {
                    attack(hero, enemy, heavyAttack);
                    turnUsed = true;
                }
                case "3" -> {
                    attack(hero, enemy, specialAttack);
                    turnUsed = true;
                }
                case "4" -> {
                    turnUsed = drinkPotion(hero);
                }
                default -> IO.println("Invalid choice, choose between 1-4!");

            }
        }
    }

    private boolean drinkPotion(Hero hero) {
        List<Item> potions = getPotions(hero);
        if (potions.isEmpty()) {
            IO.println("You have no Potions to use!");
            return false;
        } else if (hero.getHealth() == hero.getMaxHealth()) {
            IO.println("You are already at full health!");
            return false;
        } else {
            Item potion = potions.getFirst();
            int healthBefore = hero.getHealth();
            hero.heal(potion.healAmount());
            hero.getInventory().remove(potion);
            IO.println("You drink " + potion.name() + "and recover "
                    + (hero.getHealth() - healthBefore) + "HP!");
            return true;
        }
    }

    private List<Item> getPotions(Hero hero) {
        if (hero.getInventory() == null) {
            return List.of();
        }
        return hero.getInventory().getItemsOfType(ItemType.POTION);
    }

    private void printHeroMenu(Hero hero) {
        IO.println("---Your turn---" +
                "\n1. " + baseAttack.getName()
                + "\n2. " + heavyAttack.getName()
                + "\n3. " + specialAttack.getName()
                + "\n4. Drink Potion to heal!"
        );
    }

    private void enemyTurn(Hero hero, Enemy enemy) {
        AttackStrategy strategy = getStrategy(enemy);

        attack(enemy, hero, strategy);
    }

    private AttackStrategy getStrategy(Enemy enemy) {
        if (enemy instanceof Monster monster) {
            return monster.getStrategy();
        } else {
            AttackStrategy attack = new MonsterAttack();
            return attack;
        }
    }

    private void attack(Fighter attacker, Fighter defender, AttackStrategy strategy) {
        int damage = DamageCalculator.calculate(strategy, attacker, defender);
        defender.takeDamage(damage);

        String text = strategy.getMessage(attacker, defender) + "\n" + defender.getName()
                + " takes " + damage + " damage!";
        int defense = DamageCalculator.getDefenseBonus(defender);
        if (defense > 0) {
            text = text + "your armor blocked " + defense + "!";
        }
        IO.println(text);
    }

    private void printStatus(Hero hero, Enemy enemy) {
        int AttackValue = hero.getAttack() + DamageCalculator.getWeaponBonus(hero);
        int DefenseValue = DamageCalculator.getDefenseBonus(hero);

        IO.println(hero.getName() + ": " + hero.getHealth() + "/" + hero.getMaxHealth()
                + " HP (ATK " + AttackValue + ", DEF " + DefenseValue + ")"
                + "\t|\t" + enemy.getName() + ": "
                + enemy.getHealth() + "/" + enemy.getMaxHealth() + " HP");

    }
}
