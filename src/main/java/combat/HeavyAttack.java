package combat;

import common.Fighter;

public class HeavyAttack implements AttackStrategy {
    private static final double DAMAGE_FACTOR = 1.5;

    @Override
    public String getName() {
        return "Heavy Attack";
    }

    @Override
    public String getDescription() {
        return "A powerful attack that deals 50% more damage than the players basic attack";
    }

    @Override
    public String getMessage(Fighter attacker, Fighter defender) {
        return "You gather your strength before unleashing a powerful blow, " +
                "putting all your force into one hit.";
    }

    @Override
    public double getDamageMultiplier(Fighter attacker, Fighter defender) {
        return DAMAGE_FACTOR;
    }
}
