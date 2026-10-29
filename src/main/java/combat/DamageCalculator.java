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

    public int getDefenseBonus(Fighter fighter) {
        if (fighter instanceof Hero hero && hero.getInventory() != null) {
            Optional<Item> armor = hero.getInventory().getEquippedArmor();
            if (armor.isPresent()) {
                return armor.get().defenseBonus();
            }
        }
        return 0;
    }

    public int calculate(AttackStrategy strategy, Fighter attacker, Fighter defender) {
        double multipier = strategy.getDamageMultiplier(attacker, defender);
        int damage = (int) Math.round(attacker.getAttack() * multipier);
        damage = damage + getWeaponBonus(attacker);
        damage = damage - getDefenseBonus(defender);
        return Math.max(MIN_DAMAGE, damage);
    }

}
