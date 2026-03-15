package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class PreparationService {

    public String prepare(HeroProfile hero, BossEnemy boss, AttackAction action) {
        if (hero == null || boss == null || action == null) {
            return "Preparation failed: missing hero, boss or attack action";
        }
        if (!hero.isAlive()) {
            return "Preparation failed: hero is already defeated";
        }
        if (!boss.isAlive()) {
            return "Preparation failed: boss is already defeated";
        }

        return "Preparation complete. " + hero.getName() + " is ready to fight " +
                boss.getName() + " with " + action.getActionName() + " (" +
                action.getDamage() + " base damage)";
    }
}