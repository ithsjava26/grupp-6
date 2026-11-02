package combat;

import common.Fighter;

public class MonsterSpecialBossAttack implements AttackStrategy {
    private static final double DAMAGE_FACTOR = 2.0;

    @Override
    public String getName() {
        return "Soul Devourer";
    }

    @Override
    public String getDescription() {
        return "The boss unleashes a masive roar as dark energy gathers around its body," +
                "before releasing a heavy force that drains the life of your soul in seconds.";
    }

    @Override
    public double getDamageMultiplier(Fighter attacker, Fighter defender) {
        return DAMAGE_FACTOR;
    }
}
