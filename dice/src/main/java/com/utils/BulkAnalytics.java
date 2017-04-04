package com.utils;


import com.models.*;

import java.util.*;

public class BulkAnalytics {

  public static Map<BulkResult, Integer> histogram(Weapon weapon, DefenceDie defenceDie) {
    return histogram(weapon, Arrays.asList(defenceDie));
  }

  public static Map<BulkResult, Integer> histogram(Weapon weapon, List<DefenceDie> defenceDie) {
    final List<AttackDie> attackDie = weapon.getDie();
    final List<SurgeConsumer> surgeConsumers = weapon.getSurgeConsumers();

    return histogram(attackDie, defenceDie);
//    return applySurge(histogram(attackDie, defenceDie), surgeConsumers);
  }


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

  private static Map<BulkResult, Integer> applySurge(Map<BulkResult, Integer> histo, List<SurgeConsumer> surgeConsumers) {
    final Map<BulkResult, Integer> newMap = new HashMap<>(histo.size());
    for (Map.Entry<BulkResult, Integer> entry : histo.entrySet()) {
      final BulkResult keyBr = entry.getKey();

      Integer bonusDamage = 0;
      Integer bonusRange = 0;
      Integer bonusPierce = 0;
      int remainingSurge = keyBr.surge - keyBr.evade;
      final Iterator<SurgeConsumer> surgeConsumerItr = surgeConsumers.iterator();
      while (remainingSurge > 0 && surgeConsumerItr.hasNext()) {
        final SurgeConsumer surgeConsumer = surgeConsumerItr.next();
        if (surgeConsumer.getCost() <= remainingSurge) {
          bonusDamage += surgeConsumer.getDamage();
          bonusRange += surgeConsumer.getRange();
          bonusPierce += surgeConsumer.getPierce();
          remainingSurge -= surgeConsumer.getCost();
        }
      }

      final BulkResult resolvedDmgBr = new BulkResult(
         keyBr.dodge,
         keyBr.block,
         keyBr.evade,
         keyBr.damage + bonusDamage,
         keyBr.surge,
         keyBr.range + bonusRange
      );
      if (newMap.get(resolvedDmgBr) == null) {
        newMap.put(resolvedDmgBr, entry.getValue());
      } else {
        newMap.put(resolvedDmgBr, newMap.get(resolvedDmgBr) + entry.getValue());
      }
    }
    return newMap;
  }

  public static Set<List<SurgeConsumer>> getSurgeConsumerCombos(List<SurgeConsumer> consumers, int surge) {
    // Get all Surge combos:
    final List<List<SurgeConsumer>> surgePerms = Combinatorics.generateAllPerms(consumers);

    // Filtered list that only has usable surges
    final Set<List<SurgeConsumer>> filteredSurgePerms = new HashSet<>(surgePerms.size());

    // Filter down to relevant ones
    for (List<SurgeConsumer> surgeOrder : surgePerms) {
      int remainingSurge = surge;
      final Iterator<SurgeConsumer> surgeConsumerItr = surgeOrder.iterator();
      final List<SurgeConsumer> filteredSC = new ArrayList<>(surgeOrder.size());
      while (remainingSurge > 0 && surgeConsumerItr.hasNext()) {
        final SurgeConsumer currSC = surgeConsumerItr.next();
        if (currSC.getCost() <= remainingSurge) {
          remainingSurge -= currSC.getCost();
          filteredSC.add(currSC);
        }
      }
      filteredSurgePerms.add(filteredSC);
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


