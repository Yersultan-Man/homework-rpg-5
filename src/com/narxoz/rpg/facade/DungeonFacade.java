package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class DungeonFacade {
    private final PreparationService preparationService = new PreparationService();
    private final BattleService battleService = new BattleService();
    private final RewardService rewardService = new RewardService();

    public DungeonFacade setRandomSeed(long seed) {
        battleService.setRandomSeed(seed);
        return this;
    }

    public AdventureResult runAdventure(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult result = new AdventureResult();

        // 1. Подготовка
        String prep = preparationService.prepare(hero, boss, action);
        result.addLine(prep);

        // 2. Битва
        AdventureResult battle = battleService.battle(hero, boss, action);
        result.setRounds(battle.getRounds());
        result.setWinner(battle.getWinner());

        // Переносим лог битвы
        for (String line : battle.getLog()) {
            result.addLine(line);
        }

        // 3. Награда
        String reward = rewardService.determineReward(result);
        result.setReward(reward);
        result.addLine("Reward granted: " + reward);

        return result;
    }
}