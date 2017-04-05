package com.models.dice;

public class BlackDie extends DefenceDie {

  public static final int[] BLOCK = {1, 1, 2, 2, 3, 0};
  public static final int[] EVADE = {0, 0, 0, 0, 0, 1};
  public static final int[] DODGE = {0, 0, 0, 0, 0, 0};

  public BlackDie() {
    super(BLOCK, EVADE, DODGE);
  }
}
