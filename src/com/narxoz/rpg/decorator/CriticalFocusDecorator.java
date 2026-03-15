package com.narxoz.rpg.decorator;

public class CriticalFocusDecorator extends ActionDecorator {

    public CriticalFocusDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return "Critical " + getWrappedAction().getActionName();
    }

    @Override
    public int getDamage() {
        return getWrappedAction().getDamage() + 8;
    }

    @Override
    public String getEffectSummary() {
        String base = getWrappedAction().getEffectSummary();
        return base.isEmpty() ? "critical strike chance" : base + ", critical strike chance";
    }
}