package com.testing;

import com.models.BulkResult;

import java.util.Map;

/**
 * Created by kennethoxu on 4/4/17.
 */
public class Test {

  public static void printBr(Map<BulkResult, Integer> map) {
    for (Map.Entry<BulkResult, Integer> entry : map.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue());
    }
  }

  public static void printPercent(Map<Integer, Float> map) {
    for (Map.Entry<Integer, Float> entry : map.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue() * 100);
    }
  }

  public static void printInt(Map<Integer, Integer> map) {
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue());
    }
  }
}
