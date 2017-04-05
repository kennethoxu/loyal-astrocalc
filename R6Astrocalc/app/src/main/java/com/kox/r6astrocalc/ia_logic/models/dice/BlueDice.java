package com.kox.r6astrocalc.ia_logic.models.dice;

import com.kox.r6astrocalc.ia_logic.models.DiceColor;

public class BlueDice extends AttackDie {

  public static final int[] DAMAGE = {0, 1, 2, 1, 2, 1};
  public static final int[] SURGE = {1, 0, 0, 1, 0, 0};
  public static final int[] RANGE = {2, 2, 3, 3, 4, 5};

  public BlueDice() {
    super(DAMAGE, SURGE, RANGE, DiceColor.BLUE);
  }

}
