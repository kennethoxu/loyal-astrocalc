package com.models;

public class GreenDice extends AttackDie {

  public static final int[] DAMAGE = {0, 1, 2, 1, 2, 2};
  public static final int[] SURGE = {1, 1, 0, 1, 0, 0};
  public static final int[] RANGE = {1, 1, 1, 2, 2, 3};

  public GreenDice() {
    super(DAMAGE, SURGE, RANGE, DiceColor.GREEN);
  }
}
