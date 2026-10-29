package combat;

import common.Fighter;

public interface AttackStrategy {

    String getName();

    String getDescription();

    int calculateDamage(Fighter attacker, Fighter defender);

    default String getMessage(Fighter attacker) {
        return attacker.getMaxHealth() + "used" + getName() + "!";
    }

    default String getMissMessage(Fighter attacker) {
        return attacker.getName() + "used" + getName() + "but missed!";
    }

}
