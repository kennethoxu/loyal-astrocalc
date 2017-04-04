package com;

import com.models.BulkResult;
import com.models.Dice;
import com.models.SurgeConsumer;
import com.models.Weapon;
import com.utils.BulkAnalytics;
import com.utils.HistogramUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {

  public static void main(String[] args) {

    final List<SurgeConsumer> surgeConsumers2 = new ArrayList<>(Arrays.asList(SurgeConsumer.BASIC_1_DMG));
    final Weapon shroudedLightsabre = new Weapon(surgeConsumers2, Dice.YELLOW);


    print(HistogramUtil.cdf((BulkAnalytics.histogram(shroudedLightsabre, Arrays.asList(Dice.BLACK)))));

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

  static void printInt(Map<Integer, Integer> map) {
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue());
    }
  }
}