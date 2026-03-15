package com.narxoz.rpg.facade;

public class RewardService {

    public String determineReward(AdventureResult battleResult) {
        if (battleResult == null) {
            return "No reward (invalid result)";
        }

        String winner = battleResult.getWinner();
        if (winner == null) {
            return "No reward (no winner determined)";
        }

        if (battleResult.getRounds() <= 0) {
            return "No reward (battle did not occur)";
        }

        if (winner.contains("TODO")) {
            return "Reward pending (incomplete battle)";
        }

        // Простая логика наград
        if (battleResult.getRounds() <= 5) {
            return "Epic Victory! 300 gold + Rare Artifact";
        } else if (battleResult.getRounds() <= 10) {
            return "Solid win! 180 gold + Health Potion";
        } else {
            return "Hard-fought victory! 100 gold";
        }
    }
}