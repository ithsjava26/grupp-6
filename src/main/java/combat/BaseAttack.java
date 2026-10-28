package combat;

import common.Fighter;

public class BaseAttack implements AttackStrategy {
    @Override
    public String getName() {
        return "Base Attack";
    }

    @Override
    public String getDescription() {
        return "A plain hit with the attackers base damage";
    }

    @Override
    public int calculateDamage(Fighter attacker, Fighter defender) {
        return attacker.getAttack();
    }
}
