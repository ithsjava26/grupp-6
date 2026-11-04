package combat;

import items.Armor;
import items.HeroInventory;
import items.Weapon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DamageCalculatorTest {
    Weapon testWeapon = new Weapon("Sword", 1, 6);
    Player hero = new Player("Hero", 100, 100, 10,
            0, null);
    Monster monster = new Monster("Dummy", 100, 10,
            false, 0, new BaseAttack(), 100);
    HeroInventory inventory = new HeroInventory();


    @Test
    @DisplayName("Base attack without equipment = attack value")
    void baseAttackWithoutEquipmentIsAttackValue() {
        assertEquals(10,
                DamageCalculator.calculate(new BaseAttack(), hero, monster));
    }

    @Test
    @DisplayName("Equipped weapon adds its bonus to the damage")
    void equippedWeaponAddsItsBonusToTheDamage() {
        inventory.add(testWeapon);
        Player armedHero = new Player("Hero", 100, 100, 10, 0, inventory);

        assertEquals(16,
                DamageCalculator.calculate(new BaseAttack(), armedHero, monster));
    }

    @Test
    @DisplayName("Weaponbonus is added after the multiplier")
    void weaponBonusIsAddedAfterTheMultiplier() {
        inventory.add(testWeapon);
        Player armedHero = new Player("Hero", 100, 100,
                10, 0, inventory);

        assertEquals(21, DamageCalculator.calculate(new HeavyAttack(), armedHero, monster));
    }

    @Test
    @DisplayName("Hero armor lowers the damage he takes")
    void heroArmorLowersTheDamageIntake() {
        inventory.add(new Armor("Shield", 1, 5));
        Player armoredHero = new Player("Hero", 100, 100,
                10, 0, inventory);

        assertEquals(5, DamageCalculator.calculate(new BaseAttack(), monster, armoredHero));
    }

}