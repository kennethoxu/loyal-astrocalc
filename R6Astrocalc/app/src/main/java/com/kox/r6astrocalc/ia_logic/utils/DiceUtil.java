package com.kox.r6astrocalc.ia_logic.utils;

import com.kox.r6astrocalc.ia_logic.models.dice.AttackDie;
import com.kox.r6astrocalc.ia_logic.models.dice.DefenceDie;
import com.kox.r6astrocalc.ia_logic.models.DiceStat;

public class DiceUtil {

  public static int[] getStat(AttackDie attackDie, DiceStat diceStat) {
    switch (diceStat) {
      case DAMAGE:
        return attackDie.getDamage();
      case SURGE:
        return attackDie.getSurge();
      case RANGE:
        return attackDie.getRange();
      default:
        throw new UnsupportedOperationException("Invalid stat for attack dice");
    }
  }

  public static int[] getStat(DefenceDie defenceDie, DiceStat diceStat) {
    switch (diceStat) {
      case BLOCK:
        return defenceDie.getBlock();
      case EVADE:
        return defenceDie.getEvade();
      case DODGE:
        return defenceDie.getDodge();
      default:
        throw new UnsupportedOperationException("Invalid stat for attack dice");
    }
  }
}
