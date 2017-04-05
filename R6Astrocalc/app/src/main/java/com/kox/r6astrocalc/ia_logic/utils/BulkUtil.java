package com.kox.r6astrocalc.ia_logic.utils;

import com.kox.r6astrocalc.ia_logic.models.BulkResult;

import java.util.HashMap;
import java.util.Map;

public class BulkUtil {

  public static Map<Integer, Integer> extractDamage(Map<BulkResult, Integer> histo) {
    final Map<Integer, Integer> dmgMap = new HashMap<>(histo.size());
    for (Map.Entry<BulkResult, Integer> entry : histo.entrySet()) {
      final Integer dmg = entry.getKey().damage;
      final Integer zeroBoundedDmg = Math.max(0, dmg);
      if (dmgMap.get(zeroBoundedDmg) == null) {
        dmgMap.put(zeroBoundedDmg, entry.getValue());
      } else {
        dmgMap.put(zeroBoundedDmg, dmgMap.get(zeroBoundedDmg) + entry.getValue());
      }
    }
    return dmgMap;
  }


  public static int resolveDamage(FinalCombatResult jr) {
    if (jr.dodge > 0) {
      return 0;
    }

    if (jr.range < jr.requiredRange) {
      return 0;
    }

    return Math.max(jr.damage - (jr.block - Math.min(jr.block, jr.pierce)), 0);
  }
}
