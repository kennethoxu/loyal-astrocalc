package com.kox.r6astrocalc.ia_logic.models.dice;

import com.kox.r6astrocalc.ia_logic.models.DiceColor;

public class YellowDie extends AttackDie {

  public static final int[] DAMAGE = {0, 1, 2, 1, 0, 1};
  public static final int[] SURGE = {1, 2, 0, 1, 1, 0};
  public static final int[] RANGE = {0, 0, 1, 1, 2, 2};

  public YellowDie() {
    super(DAMAGE, SURGE, RANGE, DiceColor.YELLOW);
  }
}
