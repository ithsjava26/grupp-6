package combat;

import common.Fighter;

public interface AttackStrategy {

    String getName();

    String getDescription();

    double getDamageMultiplier(Fighter attacker, Fighter defender);

    default String getMessage(Fighter attacker) {
        return attacker.getMaxHealth() + "used" + getName() + "!";
    }

    default String getMissMessage(Fighter attacker) {
        return attacker.getName() + "used" + getName() + "but missed!";
    }

}
