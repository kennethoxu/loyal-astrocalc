package com.kox.r6astrocalc.ia_logic.utils;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class HistogramUtil {

  public static <T> Map<Integer, T> sortByKey(Map<Integer, T> histogram) {

    Map<Integer, T> sortedMap = new TreeMap<>(
       new Comparator<Integer>() {
         @Override
         public int compare(Integer o1, Integer o2) {
           return o1.compareTo(o2);
         }

       });

    sortedMap.putAll(histogram);

    return sortedMap;
  }


  public static Map<Integer, Float> percent(Map<Integer, Integer> histogram) {
    int sum = 0;
    for (Map.Entry<Integer, Integer> entry : histogram.entrySet()) {
      sum += entry.getValue();
    }

    final Map<Integer, Float> retMap = new TreeMap<>();
    for (Map.Entry<Integer, Integer> entry : histogram.entrySet()) {
      retMap.put(entry.getKey(), entry.getValue() * 1.0f / sum);
    }

    return retMap;
  }

  public static Map<Integer, Float> cdf(Map<Integer, Integer> histogram) {
    final Map<Integer, Float> distribMap = percent(histogram);

    float sum = 0;
    final Map<Integer, Float> retMap = new TreeMap<>();
    for (Map.Entry<Integer, Float> entry : distribMap.entrySet()) {
      retMap.put(entry.getKey(), 1.0f - sum);
      sum += entry.getValue();
    }
    return retMap;
  }
}
