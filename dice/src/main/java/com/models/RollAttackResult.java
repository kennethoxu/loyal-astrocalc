package com.models;

public class RollAttackResult implements RollResult {

  final int damage;
  final int surge;
  final int range;
  final DiceColor diceColor;

  public RollAttackResult(int damage, int surge, int range, DiceColor diceColor) {
    this.damage = damage;
    this.surge = surge;
    this.range = range;
    this.diceColor = diceColor;
  }

  @Override
  public String toString() {
    return "RollAttackResult{" +
       "damage=" + damage +
       ", surge=" + surge +
       ", range=" + range +
       ", diceColor=" + diceColor +
       '}';
  }
}
