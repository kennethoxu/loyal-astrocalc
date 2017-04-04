package com.utils;


import com.models.*;
import com.oracle.tools.packager.Log;

import java.util.*;

public class BulkAnalytics {

  public static Map<BulkResult, Integer> histogram(List<AttackDie> attackDie, List<DefenceDie> defenceDie) {
    Map<BulkResult, Integer> prevDistribMap = null;
    for (AttackDie attackDice : attackDie) {
      prevDistribMap = combineDistribMap(prevDistribMap, attackDice.getBulkResults());
    }

    for (DefenceDie defenceDice : defenceDie) {
      prevDistribMap = combineDistribMap(prevDistribMap, defenceDice.getBulkResults());
    }

    return prevDistribMap;
  }

  static void print2(Map<BulkResult, Integer> map) {
    for (Map.Entry<BulkResult, Integer> entry : map.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue());
    }
  }

  public static Map<Integer, Integer> histogram(Weapon weapon, DefenceDie defenceDie) {
    return histogram(weapon, Arrays.asList(defenceDie));
  }

  public static Map<Integer, Integer> histogram(Weapon weapon, List<DefenceDie> defenceDie) {
    final List<AttackDie> attackDie = weapon.getDie();
    final List<SurgeConsumer> surgeConsumers = weapon.getSurgeConsumers();

    return applySurge(histogram(attackDie, defenceDie), surgeConsumers, -300);
  }

  private static Map<Integer, Integer> applySurge(
     Map<BulkResult, Integer> histo,
     List<SurgeConsumer> surgeConsumers,
     int requiredRange) {

    final Map<Integer, Integer> newMap = new HashMap<>(histo.size());
    for (Map.Entry<BulkResult, Integer> entry : histo.entrySet()) {
      final BulkResult keyBr = entry.getKey();
      final Integer keyBrFreq = entry.getValue();
      final int numSurge = Math.max(0, keyBr.surge - keyBr.evade);

      if (numSurge > 0) {
        final Set<SurgeConsumer> combinedSurgeEffects =
           combineSurgeEffects(getSurgeConsumerCombos(surgeConsumers, numSurge));
        if (!combinedSurgeEffects.isEmpty()) {
          for (SurgeConsumer currSc : combinedSurgeEffects) {
            final JizzResult jizzResult = new JizzResult(
               keyBr.dodge,
               keyBr.block,
               keyBr.damage + currSc.damage,
               currSc.pierce,
               keyBr.range + currSc.range,
               requiredRange
            );
            final int damage = BulkUtil.resolveDamage(jizzResult);
            if (newMap.get(damage) == null) {
              newMap.put(damage, keyBrFreq);
            } else {
              newMap.put(damage, newMap.get(damage) + keyBrFreq);
            }
          }
        } else {
          //Pierce without surge
          final int damage = BulkUtil.resolveDamage(new JizzResult(keyBr, 0, requiredRange));

          System.out.println(keyBr  + " :" + damage);

          if (newMap.get(damage) == null) {
            newMap.put(damage, keyBrFreq);
          } else {
            newMap.put(damage, newMap.get(damage) + keyBrFreq);
          }
        }
      } else {
//        System.out.println(keyBr +": " + keyBrFreq);

        //Pierce without surge
        final int damage = BulkUtil.resolveDamage(new JizzResult(keyBr, 0, requiredRange));


        if (newMap.get(damage) == null) {
          newMap.put(damage, keyBrFreq);
        } else {
          newMap.put(damage, newMap.get(damage) + keyBrFreq);
        }
      }
    }
    return newMap;
  }

  public static Set<SurgeConsumer> combineSurgeEffects(Set<List<SurgeConsumer>> scCombos) {
    final Set<SurgeConsumer> retSet = new HashSet<>(scCombos.size());
    for (List<SurgeConsumer> currScList : scCombos) {
      int cost = 0;
      int damage = 0;
      int pierce = 0;
      int range = 0;
      for (SurgeConsumer currSc : currScList) {
        damage += currSc.damage;
        pierce += currSc.pierce;
        range += currSc.range;
        cost += currSc.cost;
      }
      final SurgeConsumer sc = new SurgeConsumer(cost, damage, pierce, range);
      retSet.add(sc);
    }

    return retSet;
  }

  public static Set<List<SurgeConsumer>> getSurgeConsumerCombos(List<SurgeConsumer> consumers, int surge) {
    // Get all Surge combos:
    final List<List<SurgeConsumer>> surgePerms = Combinatorics.generateAllPerms(consumers);

    final Set<List<SurgeConsumer>> filteredSurgePerms = new HashSet<>(surgePerms.size());

    // Filter down to relevant ones that fullfill cost
    for (List<SurgeConsumer> surgeOrder : surgePerms) {
      int remainingSurge = surge;
      final Iterator<SurgeConsumer> surgeConsumerItr = surgeOrder.iterator();
      final List<SurgeConsumer> filteredSC = new ArrayList<>(surgeOrder.size());
      while (remainingSurge > 0 && surgeConsumerItr.hasNext()) {
        final SurgeConsumer currSC = surgeConsumerItr.next();
        if (currSC.cost <= remainingSurge) {
          remainingSurge -= currSC.cost;
          filteredSC.add(currSC);
        }
      }
      if (!filteredSC.isEmpty()) {
        filteredSurgePerms.add(filteredSC);
      }
    }

    return filteredSurgePerms;
  }


  private static Map<BulkResult, Integer> combineDistribMap(Map<BulkResult, Integer> prevMap, BulkResult[] bulkResults) {
    if (prevMap == null) {
      final Map<BulkResult, Integer> newMap = new HashMap<>();
      for (int i = 0; i < bulkResults.length; i++) {
        if (newMap.get(bulkResults[i]) == null) {
          newMap.put(bulkResults[i], 1);
        } else {
          newMap.put(bulkResults[i], newMap.get(bulkResults[i]) + 1);
        }
      }
      return newMap;
    }

    final Map<BulkResult, Integer> newMap = new HashMap<>(prevMap.size());
    for (Map.Entry<BulkResult, Integer> entry : prevMap.entrySet()) {
      final BulkResult key = entry.getKey();
      for (BulkResult bulkResult : bulkResults) {
        final BulkResult keyPlusBR = key.add(bulkResult);
        if (newMap.get(keyPlusBR) == null) {
          newMap.put(keyPlusBR, prevMap.get(key));
        } else {
          newMap.put(keyPlusBR, prevMap.get(key) + newMap.get(keyPlusBR));
        }
      }
    }
    return newMap;
  }
}


