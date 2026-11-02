package combat;

import common.Fighter;

public interface AttackStrategy {

    String getName();

    String getDescription();

    double getDamageMultiplier(Fighter attacker, Fighter defender);

    default String getMessage(Fighter attacker, Fighter defender) {
        return attacker.getName() + " used " + getName() + "!";
    }

    default String getMissMessage(Fighter attacker, Fighter defender) {
        return attacker.getName() + " used " + getName() + " but missed!";
    }

}
