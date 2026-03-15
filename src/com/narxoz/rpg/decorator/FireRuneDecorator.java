package com.narxoz.rpg.decorator;

public class FireRuneDecorator extends ActionDecorator {

    public FireRuneDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return "Fiery " + getWrappedAction().getActionName();
    }

    @Override
    public int getDamage() {
        return getWrappedAction().getDamage() + 6;
    }

    @Override
    public String getEffectSummary() {
        String base = getWrappedAction().getEffectSummary();
        return base.isEmpty() ? "burning (fire damage)" : base + ", burning (fire damage)";
    }
}