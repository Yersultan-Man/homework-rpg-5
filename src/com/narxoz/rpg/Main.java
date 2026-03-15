package com.narxoz.rpg;

import com.narxoz.rpg.decorator.*;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("   Homework 5 – RPG Dungeon Run");
        System.out.println("   Decorator + Facade Demonstration");
        System.out.println("=========================================\n");

        // Создаём персонажей
        HeroProfile hero = new HeroProfile("Yersultan the Brave", 120);
        BossEnemy boss = new BossEnemy("Ancient Shadow Dragon", 200, 20);

        System.out.println("Hero prepared:");
        System.out.println("  Name: " + hero.getName());
        System.out.println("  Health: " + hero.getHealth() + " HP");
        System.out.println();
        System.out.println("Boss waiting:");
        System.out.println("  Name: " + boss.getName());
        System.out.println("  Health: " + boss.getHealth() + " HP");
        System.out.println("  Attack power: " + boss.getAttackPower());
        System.out.println();

        // ────────────────────────────────────────────────
        // Демонстрация Decorator
        // ────────────────────────────────────────────────
        System.out.println("=== Decorator Pattern – Attack Upgrades ===");

        AttackAction base = new BasicAttack("Swift Blade", 14);
        printAttack("Base attack", base);

        AttackAction withFire = new FireRuneDecorator(base);
        printAttack("With Fire Rune", withFire);

        AttackAction withPoison = new PoisonCoatingDecorator(withFire);
        printAttack("Fire + Poison", withPoison);

        AttackAction ultimate = new CriticalFocusDecorator(
                new PoisonCoatingDecorator(
                        new FireRuneDecorator(base)
                )
        );
        printAttack("Ultimate (Critical + Poison + Fire)", ultimate);

        AttackAction anotherCombo = new CriticalFocusDecorator(
                new FireRuneDecorator(base)
        );
        printAttack("Critical + Fire only", anotherCombo);

        System.out.println();

        // ────────────────────────────────────────────────
        // Запуск приключения через Facade
        // ────────────────────────────────────────────────
        System.out.println("=== Full Dungeon Run via Facade ===");
        System.out.println("Starting adventure with ultimate attack...\n");

        DungeonFacade facade = new DungeonFacade().setRandomSeed(42L);

        AdventureResult result = facade.runAdventure(hero, boss, ultimate);

        // Вывод результата
        System.out.println("\n=== Adventure Summary ===");
        System.out.println("Winner:          " + result.getWinner());
        System.out.println("Rounds played:   " + result.getRounds());
        System.out.println("Final reward:    " + result.getReward());

        System.out.println("\nBattle log:");
        System.out.println("───────────────────────────────────────────");
        for (String line : result.getLog()) {
            System.out.println(line);
        }
        System.out.println("───────────────────────────────────────────");

        System.out.println("\nDemo finished successfully.");
        System.out.println("Decorator stacking and Facade usage demonstrated.");
    }

    private static void printAttack(String title, AttackAction attack) {
        System.out.println(title + ":");
        System.out.println("  Action name → " + attack.getActionName());
        System.out.println("  Damage     → " + attack.getDamage());
        System.out.println("  Effects    → " + attack.getEffectSummary());
        System.out.println();
    }
}