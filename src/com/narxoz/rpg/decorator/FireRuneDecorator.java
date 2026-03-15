package com.narxoz.rpg.decorator;

public class FireRuneDecorator extends ActionDecorator {

    public FireRuneDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return "Fiery " + wrappedAction.getActionName();
    }

    @Override
    public int getDamage() {
        return wrappedAction.getDamage() + 6;
    }

    @Override
    public String getEffectSummary() {
        return wrappedAction.getEffectSummary() + ", ignites target (extra fire damage)";
    }
}