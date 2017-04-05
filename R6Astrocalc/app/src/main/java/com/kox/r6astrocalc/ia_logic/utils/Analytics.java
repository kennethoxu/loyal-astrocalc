package com.kox.r6astrocalc.ia_logic.utils;

import com.kox.r6astrocalc.ia_logic.models.dice.DefenceDie;
import com.kox.r6astrocalc.ia_logic.models.weapon.Weapon;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Analytics {

  public static Map<Integer, Float> cdf(Weapon weapon, List<DefenceDie> defenceDie, int requiredRange) {
    return HistogramUtil.cdf(BulkAnalytics.histogram(weapon, defenceDie, requiredRange));
  }

  public static Map<Integer, Float> cdf(Weapon weapon, DefenceDie defenceDie, int requiredRange) {
    return HistogramUtil.cdf(BulkAnalytics.histogram(weapon, Arrays.asList(defenceDie), requiredRange));
  }


}
