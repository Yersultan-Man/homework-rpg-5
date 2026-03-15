package com.narxoz.rpg.decorator;

public class PoisonCoatingDecorator extends ActionDecorator {

    public PoisonCoatingDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return "Venomous " + getWrappedAction().getActionName();
    }

    @Override
    public int getDamage() {
        return getWrappedAction().getDamage() + 4;
    }

    @Override
    public String getEffectSummary() {
        String base = getWrappedAction().getEffectSummary();
        return base.isEmpty() ? "poisoned (DoT)" : base + ", poisoned (DoT)";
    }
}