package com.kox.r6astrocalc.ia_logic.models.dice;

import com.kox.r6astrocalc.ia_logic.models.DiceColor;

public class GreenDie extends AttackDie {

  public static final int[] DAMAGE = {0, 1, 2, 1, 2, 2};
  public static final int[] SURGE = {1, 1, 0, 1, 0, 0};
  public static final int[] RANGE = {1, 1, 1, 2, 2, 3};

  public GreenDie() {
    super(DAMAGE, SURGE, RANGE, DiceColor.GREEN);
  }
}
