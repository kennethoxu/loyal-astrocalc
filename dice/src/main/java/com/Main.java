package com;

import com.models.BulkResult;
import com.models.Dice;
import com.models.SurgeConsumer;
import com.models.Weapon;
import com.utils.BulkAnalytics;

import java.util.*;

public class Main {

  public static void main(String[] args) {

    final List<SurgeConsumer> surgeConsumers = new ArrayList<>(Arrays.asList(
       SurgeConsumer.ADV_2_DMG,
       SurgeConsumer.BASIC_1_DMG,
       SurgeConsumer.BASIC_1_DMG));
    final Weapon shroudedLightsabre = new Weapon(surgeConsumers, Dice.GREEN, Dice.YELLOW);

//    print(HistogramUtil.cdf(
//       BulkUtil.resolveDamage(BulkAnalytics.histogram(shroudedLightsabre, Arrays.asList(Dice.WHITE)))));

//    print2(BulkAnalytics.histogram(shroudedLightsabre, Arrays.asList(Dice.BLACK)));


    Set<List<SurgeConsumer>> consumers = BulkAnalytics.getSurgeConsumerCombos(surgeConsumers, 2);
    for (List<SurgeConsumer> currConsumer : consumers) {
      System.out.println(currConsumer);
    }
  }

  static void print2(Map<BulkResult, Integer> map) {
    for (Map.Entry<BulkResult, Integer> entry : map.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue());
    }
  }

  static void print(Map<Integer, Float> map) {
    for (Map.Entry<Integer, Float> entry : map.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue() * 100);
    }
  }
}