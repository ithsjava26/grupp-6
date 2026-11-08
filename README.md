# Below the surface - Group 6
A simple text based RPG for the console. It is played as a hero who fights through five rooms in a dungeon,
filled with monsters.
In each room the Hero meets a group of monster, except for the last (room 5), there they will meet the final Boss. 
Between the rooms it is possible to shop, check the inventory, drink potion and save the current game. 

## How to run it
It is played in JDK 27 and Maven. Start the class `world.Main`.
### How the game works 
- The player starts with 100 HP
- The player gets always the first hit in a fight, it is possible to choose between a basic attack, heavy attack(x1.5) and the finisher (special attack -> if the enemy is less then 20% of maxHealth it has a attack multiplier x2).
- In room 1-4 are groups of monster spawning, the player only fights them one by one. The healht is not resotred
between them.
- Damage = attack x attack multiplier + weapon bonus - armor defense.
- Potions can only be used between the rooms, not in a fight.
- Between the rooms the player can choose if continuing the game, visit the shop, look at the equipment, use potions or save the game.
- There are three difficulties to choose from, easy(x0.8), normal(x1.0) and hard (x1.2).
- In the main menu the player is able to choose the option game guide to get all the information what is needed.

## Package and who did what 
We started speaking and writing `common` together before anyone wrote their own part. It incluedes all the Interfaces, Records, classes and Enums that needed to communicate with eachother during the project. Like Enum Difficulty, Interface Fighter, Interface Enemy, Interface Player, Interface ShopInfo, Enum ItemType and more...

Alexander Linryd
- Package `world` includes menus, game loop, rooms, save and load, event log and general game functions. 
- `Main` Starts the game. Creates `Game` with the combat and shop modules.
- `Game` The loop, Runs the main menu, plays one room after eachother, shows the menu between and handles the save and load. 
- `Dungeon` Plays one room (enemy, fight, gold reward). 
- `ConsoleUI` Prints most of the menus and reads the input. 
- `SaveData`/`SaveManager` are holding everything that gets saved, writes it to savegame.txt and are reading it back to the gane.
- `EventPublisher`/`FileLogListener` important events like "room started", "enemy defeated" and "game saved" are written to a log file (game.log). 
- `Tests` JUnit tests for save/load and for potions. 

Lisa Mosberger
- Package `combat` includes hero, monsters, fight loop, attacks, damage calcultaions, enemy factory and the game guide. 
- `Player` The Hero. Holds the base values of the Hero.
- `Monster`/`Monstergroup` A single monster and a group of monsters are created here, both of these classes implement the class `Enemy` in the common package. 
- `Enemyfactory` creates monsters and the Boss depending on room and difficulty.
- `CombatCoordinator` was the first class, it runs the fight loop and important information that other Team Members needed while writing their part, like create Hero and load Hero. 
- `Damage Calculator` calculates the damage of the hero and enmey.
- `AttackStrategy` is an Interface for all attacks (Stragety pattern). It handles `BaseAttack`,`HeavyAttack`,`SpecialAttack` and all the monster attacks `MonsterAttack` and `MonsterSpecialBossAttack`(both build on ChanceAttack).
- `GameGuide` was the last added class, it includes all the information a player needs to know about this game.

Emil Lennström 
- Package `items` includes Armor, weapons, potions, shop, itemfactory, itemrules and the heros inventory. 
- `Armor`, `Weapon` and `Potion` implement all the `Item` Interface in `Common` all of them control the creation of weapons, armor, and potions. Each of them give different bonus types (defense, attack or heal bonus). 
- `HeroInventory` The inventory of the Hero, Holds the items and knows which weapon and armor are equipped
- `ItemRules` checks that the name cannot be null or the level can't be below one. 
- `ItemFactory` creates all items from type and level (five different levels) 
- `Shop` is between rooms, Each visit it creates three offers, the item level increases the further the hero is in the dungeon.

## Design Patterns 
### Observer (World) 
The game sends out messages when something happens, for example "enemy defeated". The game does not need to know who receives them. We have two receivers: one that keeps the events in memory and one that writes them to the log file. 
-> Because we can add more receivers without changing the rest of the game. 
### Observer (Combat) 
We used `AttackStrategy` as an Interface and every attack builds on it (`BaseAttack`, `HeavyAttack`, `SpecialAttack` `MonsterAttack` and `MonsterSpecialBossAttack`. 
-> That way it is easy to add different attacks to the game. 
Factory: `EnemyFactory` creates monsters and the boss depending on room and difficulty while `ItemFactory` creates items. The rest of the game doesn't need to know how the values are calculated, so it happens in just one place. 
-> It is easy to add more Items or Monsters just by adding them into these Factory classes. 

## Tests 
There are JUnit tests for each package, damage calculations, attacks, monsters, and the enmemyfactory in `combat`. Inventory, items and shop in `Items`. Potions, Saving and loading in `World`.

## Group reflection 
We worked with one feature branch per task, each branch could only be merged with permission of one other group member with a pull request. 
### What worked well 
Writing the interfaces, records and enemus already in the beginning in `Common` was a really good idea. That way we could work at the same time in different folders without waiting or merging problems. 
The assignment of tasks to the group members was easy and everyone agreed on it. 

The communication worked really well, even if meetings didn't always worked. Everyone was reachable through text. The pull request was never waiting for too long. 
We never had any merging conflicts, because everyone was working in their own package and never in the same one. If there was something to fix in the `Common` package, we spoke about it who will fix it. 

### Problems we had
Some text were printed twice. We hadn't agreed on who prints what, so we found it when we test played the game and removed the duplicates. 
`Common` was not finished in the beginning. `Item` had no bonus values and `Inventory` had no way to get the equipped weapon or armor. 
All these problems that we met were easy to fix. 

## Overall reflection 
Working together in a group was a good new experience for all of us. It went very well from start to finsih. We never had any disagreements, everyone was respectfull and the work was efficient. When something was unclear, everyone was always reachable through chat and answered as soon as possible. 

Dividing the project into three packages worked well, because each of us could focus on their own part of the project. The project thaught us a lot about how to work in a team. We learned how to use branches and pull requests, how and when to create commits and merges that everyone understands. 


