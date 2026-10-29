package combat;

import common.Fighter;
import common.Hero;
import common.Item;

import java.util.Optional;

public class DamageCalculator {
    public static final int MIN_DAMAGE = 1;

    public int getWeaponBonus(Fighter fighter) {
        if (fighter instanceof Hero hero && hero.getInventory() != null) {
            Optional<Item> weapon = hero.getInventory().getEquippedWeapon();
            if (weapon.isPresent()) {
                return weapon.get().attackBonus();
            }
        }
        return 0;
    }


}
