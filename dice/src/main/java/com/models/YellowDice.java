package com.models;

/**
 * Created by kennethoxu on 4/1/17.
 */
public class YellowDice extends AttackDie {

  public static final int[] DAMAGE = {0, 1, 2, 1, 0, 1};
  public static final int[] SURGE = {1, 2, 0, 1, 1, 0};
  public static final int[] RANGE = {0, 0, 1, 1, 2, 2};

  public YellowDice() {
    super(DAMAGE, SURGE, RANGE, DiceColor.YELLOW);
  }
}
