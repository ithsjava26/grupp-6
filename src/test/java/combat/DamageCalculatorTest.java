package combat;

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


    @Test
    @DisplayName("Base attack without equipment = attack value")
    void baseAttackWithoutEquipmentIsAttackValue() {
        assertEquals(10,
                DamageCalculator.calculate(new BaseAttack(), hero, monster));
    }

    //TODO: As soon as Inventory exists i can use weapon to test DamageCalculator


}