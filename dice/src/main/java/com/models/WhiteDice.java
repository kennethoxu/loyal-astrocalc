package com.models;

public class WhiteDice extends DefenceDie {

  public static final int[] BLOCK = {0, 1, 0, 1, 1, 0};
  public static final int[] EVADE = {0, 0, 1, 1, 1, 0};
  public static final int[] DODGE = {0, 0, 0, 0, 0, 1};

  public WhiteDice() {
    super(BLOCK, EVADE, DODGE);
  }
}
