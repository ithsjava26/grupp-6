package combat;

import common.Fighter;

public class MonsterHeavyAttack implements AttackStrategy {
    private static final double DAMAGE_FACTOR = 1.5;

    @Override
    public String getName() {
        return "Brutal Punce";
    }

    @Override
    public String getDescription() {
        return "The creature lowers its body, before suddendly striking " +
                "you  with its full weight and razor sharp claws";
    }

    @Override
    public double getDamageMultiplier(Fighter attacker, Fighter defender) {
        return DAMAGE_FACTOR;
    }
}
