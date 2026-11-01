package combat;

import common.Fighter;

//TODO: DamageCalculation should also include dmg of the best weapon
public class HeavyAttack implements AttackStrategy {
    private static final double DAMAGE_FACTOR = 1.5;

    @Override
    public String getName() {
        return "Heavy Attack";
    }

    @Override
    public String getDescription() {
        return "You gather your strength before unleashing a powerful blow, " +
                "putting all your force into one hit.";
    }

    @Override
    public double getDamageMultiplier(Fighter attacker, Fighter defender) {
        return DAMAGE_FACTOR;
    }
}
