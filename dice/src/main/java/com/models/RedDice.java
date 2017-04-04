package com.models;

public class RedDice extends AttackDie {

  public static final int[] DAMAGE = {1, 2, 2, 2, 3, 3};
  public static final int[] SURGE = {0, 0, 0, 1, 0, 0};
  public static final int[] RANGE = {0, 0, 0, 0, 0, 0};

  public RedDice() {
    super(DAMAGE, SURGE, RANGE, DiceColor.RED);
  }

}
