package combat;

import common.Fighter;

public class BaseAttack implements AttackStrategy {
    private static final double DAMAGE_FACTOR = 1.0;

    @Override
    public String getName() {
        return "Base Attack";
    }

    @Override
    public String getDescription() {
        return "A plain hit with the attackers base damage";
    }

    @Override
    public double getDamageMultiplier(Fighter attacker, Fighter defender) {
        return DAMAGE_FACTOR;
    }
}
