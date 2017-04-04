package com.utils;

import com.models.BulkResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kennethoxu on 4/2/17.
 */
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

  public static Map<Integer, Integer> resolveDamage(Map<BulkResult, Integer> histo) {
    final Map<Integer, Integer> dmgMap = new HashMap<>(histo.size());
    for (Map.Entry<BulkResult, Integer> entry : histo.entrySet()) {
      final BulkResult currBr = entry.getKey();
      final int dmg;
      if (currBr.dodge > 0 ) {
        dmg = 0;
      } else {
        dmg = currBr.damage;
      }
      final Integer zeroBoundedDmg = Math.max(0, dmg);
      if (dmgMap.get(zeroBoundedDmg) == null) {
        dmgMap.put(zeroBoundedDmg, entry.getValue());
      } else {
        dmgMap.put(zeroBoundedDmg, dmgMap.get(zeroBoundedDmg) + entry.getValue());
      }
    }
    return dmgMap;
  }

  public static int resolveDamage(JizzResult jr) {
    if (jr.dodge > 0) {
      return 0;
    }

    if (jr.range < jr.requiredRange) {
      return 0;
    }

    return Math.max(jr.damage - (jr.block - Math.min(jr.block, jr.pierce)), 0);
  }
}
