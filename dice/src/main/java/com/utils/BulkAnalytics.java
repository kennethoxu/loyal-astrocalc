package com.utils;


import com.models.*;
import com.models.dice.AttackDie;
import com.models.dice.DefenceDie;
import com.models.weapon.Weapon;

import java.util.*;

public class BulkAnalytics {

  public static Map<Integer, Integer> histogram(
     Weapon weapon,
     List<DefenceDie> defenceDie,
     int requiredRange) {
    return histogram(weapon, defenceDie, new CombatMods(), requiredRange);
  }

  public static Map<Integer, Integer> histogram(
     Weapon weapon,
     List<DefenceDie> defenceDie,
     CombatMods scm,
     int requiredRange) {
    final List<AttackDie> attackDie = weapon.getDie();
    final List<SurgeConsumer> surgeConsumers = weapon.getSurgeConsumers();

    final Map<BulkResult, Integer> histoMap = histogram(attackDie, defenceDie);
    final Map<Integer, Integer> damageMap = applySurge(histoMap, surgeConsumers, scm, requiredRange);

    //Sort by damage:
    return HistogramUtil.sortByKey(damageMap);
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

  private static Map<Integer, Integer> applySurge(
     Map<BulkResult, Integer> histo,
     List<SurgeConsumer> surgeConsumers,
     CombatMods scm,
     int requiredRange) {

    final Map<Integer, Integer> newMap = new HashMap<>(histo.size());
    for (Map.Entry<BulkResult, Integer> entry : histo.entrySet()) {
      final BulkResult keyBr = entry.getKey();
      final Integer keyBrFreq = entry.getValue();
      final int numSurge = Math.max(0, keyBr.surge + scm.surge - keyBr.evade - scm.evade);

      if (numSurge > 0) {
        final Set<SurgeConsumer> combinedSurgeEffects =
           combineSurgeEffects(getSurgeConsumerCombos(surgeConsumers, numSurge));

        if (!combinedSurgeEffects.isEmpty()) {
          final int highestDamageSurge = getHighestDamageSurge(keyBr, combinedSurgeEffects, scm, requiredRange);
          addToMapVal(newMap, highestDamageSurge, keyBrFreq);
          continue;
        }
      }

      //Pierce without surge
      final int damage = BulkUtil.resolveDamage(new FinalCombatResult(keyBr, scm, requiredRange));
      addToMapVal(newMap, damage, keyBrFreq);
    }
    return newMap;
  }

  private static int getHighestDamageSurge(
     BulkResult br,
     Set<SurgeConsumer> combinedSurgeEffects,
     CombatMods scm,
     int requiredRange
  ) {

    System.out.println(scm.damage);

    int highestDamage = Integer.MIN_VALUE;
    for (SurgeConsumer currSc : combinedSurgeEffects) {
      final FinalCombatResult finalCombatResult = new FinalCombatResult(
         br.dodge,
         br.block + scm.block,
         br.damage + currSc.damage + scm.damage,
         currSc.pierce + scm.pierce,
         br.range + currSc.range + scm.range,
         requiredRange
      );

      highestDamage = Math.max(highestDamage, BulkUtil.resolveDamage(finalCombatResult));
    }
    return highestDamage;
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

  private static <T> void addToMapVal(Map<T, Integer> map, T key, Integer val) {
    if (map.get(key) == null) {
      map.put(key, val);
    } else {
      map.put(key, map.get(key) + val);
    }
  }

  private static Map<BulkResult, Integer> combineDistribMap(Map<BulkResult, Integer> prevMap, BulkResult[] bulkResults) {
    if (prevMap == null) {
      final Map<BulkResult, Integer> newMap = new HashMap<>();
      for (int i = 0; i < bulkResults.length; i++) {
        addToMapVal(newMap, bulkResults[i], 1);
      }
      return newMap;
    }

    final Map<BulkResult, Integer> newMap = new HashMap<>(prevMap.size());
    for (Map.Entry<BulkResult, Integer> entry : prevMap.entrySet()) {
      final BulkResult keyBr = entry.getKey();
      for (BulkResult currBr : bulkResults) {
        addToMapVal(newMap, keyBr.add(currBr), prevMap.get(keyBr));
      }
    }
    return newMap;
  }
}


