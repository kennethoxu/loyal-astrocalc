package com.kox.r6astrocalc.ia_logic.models.dice;

import com.kox.r6astrocalc.ia_logic.models.DiceColor;

public class RedDie extends AttackDie {

  public static final int[] DAMAGE = {1, 2, 2, 2, 3, 3};
  public static final int[] SURGE = {0, 0, 0, 1, 0, 0};
  public static final int[] RANGE = {0, 0, 0, 0, 0, 0};

  public RedDie() {
    super(DAMAGE, SURGE, RANGE, DiceColor.RED);
  }

}
