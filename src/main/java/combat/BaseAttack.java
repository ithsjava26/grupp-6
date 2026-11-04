package combat;

import common.Fighter;

public class BaseAttack implements AttackStrategy {
    private static final double DAMAGE_FACTOR = 1.0;

    @Override
    public String getName() {
        return "Basic Attack";
    }

    @Override
    public String getDescription() {
        return "A quick strike that deals standard damage to your enemy.";
    }

    @Override
    public String getMessage(Fighter attacker, Fighter defender) {
        return "You launch a swift attack against your enemy, attacking with speed and " +
                "precision";
    }

    @Override
    public double getDamageMultiplier(Fighter attacker, Fighter defender) {
        return DAMAGE_FACTOR;
    }

}
