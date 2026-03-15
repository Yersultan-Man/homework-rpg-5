package com.narxoz.rpg.decorator;

public class CriticalFocusDecorator extends ActionDecorator {

    public CriticalFocusDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return "Critical " + wrappedAction.getActionName();
    }

    @Override
    public int getDamage() {
        return wrappedAction.getDamage() + 8;
    }

    @Override
    public String getEffectSummary() {
        return wrappedAction.getEffectSummary() + ", increased critical potential";
    }
}