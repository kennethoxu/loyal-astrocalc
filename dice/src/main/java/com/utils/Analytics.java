package com.utils;

import com.models.dice.DefenceDie;
import com.models.weapon.Weapon;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by kennethoxu on 4/4/17.
 */
public class Analytics {

  public static Map<Integer, Float> cdf(Weapon weapon, List<DefenceDie> defenceDie, int requiredRange) {
    return HistogramUtil.cdf(BulkAnalytics.histogram(weapon, defenceDie, requiredRange));
  }

  public static Map<Integer, Float> cdf(Weapon weapon, DefenceDie defenceDie, int requiredRange) {
    return HistogramUtil.cdf(BulkAnalytics.histogram(weapon, Arrays.asList(defenceDie), requiredRange));
  }


}
