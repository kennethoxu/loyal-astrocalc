package com.utils;

import com.models.*;
import com.models.dice.AttackDie;
import com.models.dice.DefenceDie;
import com.models.weapon.Weapon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicAnalytics {

  public static Map<Integer, Integer> histogram(Weapon weapon, DefenceDie defenceDie) {
    return histogram(weapon, Arrays.asList(defenceDie));
  }

  public static Map<Integer, Integer> histogram(Weapon weapon, List<DefenceDie> defenceDie) {
    final List<AttackDie> attackDie = weapon.getDie();
    final List<SurgeConsumer> surgeConsumers = weapon.getSurgeConsumers();

    return damageHistogram(attackDie, defenceDie);
  }


  public static Map<Integer, Integer> damageHistogram(List<AttackDie> attackDie, List<DefenceDie> defenceDie) {
    Map<Integer, Integer> prevDistribMap = null;
    for (AttackDie attackDice : attackDie) {
      prevDistribMap = getDistributionArray(prevDistribMap, attackDie.size(), DiceUtil.getStat(attackDice, DiceStat.DAMAGE));
    }

    for (DefenceDie defenceDice : defenceDie) {
      prevDistribMap = getNegDistributionArray(prevDistribMap, DiceUtil.getStat(defenceDice, DiceStat.BLOCK));
    }

    return prevDistribMap;
  }

  public static Map<Integer, Integer> surgeHistogram(DiceGroup diceGroup) {
    final List<AttackDie> attackDie = diceGroup.getAttackDie();
    final List<DefenceDie> defenceDie = diceGroup.getDefenceDice();
    Map<Integer, Integer> prevDistribMap = null;
    for (AttackDie attackDice : attackDie) {
      prevDistribMap = getDistributionArray(prevDistribMap, attackDie.size(), DiceUtil.getStat(attackDice, DiceStat.SURGE));
    }

    for (DefenceDie defenceDice : defenceDie) {
      prevDistribMap = getNegDistributionArray(prevDistribMap, DiceUtil.getStat(defenceDice, DiceStat.EVADE));
    }

    return prevDistribMap;
  }

  private static Map<Integer, Integer> getDistributionArray(Map<Integer, Integer> prevMap, int numDie, int[] stat) {
    if (prevMap == null) {
      final Map<Integer, Integer> newMap = new HashMap<>(numDie * 5);
      for (int j = 0; j < stat.length; j++) {
        if (newMap.get(stat[j]) == null) {
          newMap.put(stat[j], 1);
        } else {
          newMap.put(stat[j], newMap.get(stat[j]) + 1);
        }
      }
      return newMap;
    }

    final Map<Integer, Integer> newMap = new HashMap<>(prevMap.size());
    for (Map.Entry<Integer, Integer> entry : prevMap.entrySet()) {
      final Integer key = entry.getKey();
      for (int face : stat) {
        if (newMap.get(key + face) == null) {
          newMap.put(key + face, prevMap.get(key));
        } else {
          newMap.put(key + face, prevMap.get(key) + newMap.get(key + face));
        }
      }
    }
    return newMap;
  }

  private static Map<Integer, Integer> getNegDistributionArray(Map<Integer, Integer> prevMap, int[] stat) {
    final Map<Integer, Integer> newMap = new HashMap<>(prevMap.size());
    for (Map.Entry<Integer, Integer> entry : prevMap.entrySet()) {
      final Integer key = entry.getKey();
      for (int face : stat) {
        final int newSum = Math.max(0, key - face);
        if (newMap.get(newSum) == null) {
          newMap.put(newSum, prevMap.get(key));
        } else {
          newMap.put(newSum, prevMap.get(key) + newMap.get(newSum));
        }
      }
    }
    return newMap;
  }
}


