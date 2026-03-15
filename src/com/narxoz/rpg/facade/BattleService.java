package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

import java.util.Random;

public class BattleService {
    private Random random = new Random(1L);

    public BattleService setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult result = new AdventureResult();
        int rounds = 0;

        result.addLine("Battle starts: " + hero.getName() + " (" + hero.getHealth() + " HP) vs "
                + boss.getName() + " (" + boss.getHealth() + " HP)");

        while (hero.isAlive() && boss.isAlive()) {
            rounds++;

            // Hero attacks
            int heroDmg = action.getDamage();
            boss.takeDamage(heroDmg);

            String heroMsg = hero.getName() + " attacks with " + action.getActionName()
                    + " → " + heroDmg + " damage";
            if (!boss.isAlive()) {
                heroMsg += " (fatal strike!)";
            }
            result.addLine(heroMsg);

            if (!boss.isAlive()) {
                break;
            }

            // Boss attacks back
            int bossDmg = boss.getAttackPower();

            // Небольшой шанс крита у босса (для демонстрации рандома)
            boolean isCrit = random.nextDouble() < 0.20; // 20% шанс
            if (isCrit) {
                bossDmg = (int)(bossDmg * 1.5);
                result.addLine(boss.getName() + " lands a CRITICAL HIT!");
            }

            hero.takeDamage(bossDmg);

            String bossMsg = boss.getName() + " strikes back → " + bossDmg + " damage";
            if (!hero.isAlive()) {
                bossMsg += " (fatal strike!)";
            }
            result.addLine(bossMsg);

            // Промежуточный статус после раунда
            result.addLine("Round " + rounds + " ends → Hero: " + hero.getHealth()
                    + " HP | Boss: " + boss.getHealth() + " HP");
            result.addLine("---");
        }

        result.setRounds(rounds);

        if (hero.isAlive()) {
            result.setWinner(hero.getName());
            result.addLine("VICTORY! " + hero.getName() + " defeated " + boss.getName() + "!");
        } else {
            result.setWinner(boss.getName());
            result.addLine("DEFEAT... " + boss.getName() + " has slain " + hero.getName() + ".");
        }

        return result;
    }
}