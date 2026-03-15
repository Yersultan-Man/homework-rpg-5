package com.narxoz.rpg.decorator;

public class PoisonCoatingDecorator extends ActionDecorator {

    public PoisonCoatingDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return "Venomous " + wrappedAction.getActionName();
    }

    @Override
    public int getDamage() {
        return wrappedAction.getDamage() + 4;
    }

    @Override
    public String getEffectSummary() {
        return wrappedAction.getEffectSummary() + ", poisons target (ongoing damage)";
    }
}