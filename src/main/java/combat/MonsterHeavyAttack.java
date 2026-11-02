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
        return "A powerfull attack that deals 50% more damage than a standart attack.";
    }

    @Override
    public String getMessage(Fighter attacker, Fighter defender) {
        return "The creature lowers its body, before suddendly striking " +
                "you  with its full weight and razor sharp claws";
    }

    @Override
    public double getDamageMultiplier(Fighter attacker, Fighter defender) {
        return DAMAGE_FACTOR;
    }
}
