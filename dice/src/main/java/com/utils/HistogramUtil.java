package com.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kennethoxu on 4/2/17.
 */
public class HistogramUtil {


  public static Map<Integer, Float> percent(Map<Integer, Integer> histogram) {
    int sum = 0;
    for (Map.Entry<Integer, Integer> entry : histogram.entrySet()) {
      sum += entry.getValue();
    }

    final Map<Integer, Float> retMap = new HashMap<>(histogram.size());
    for (Map.Entry<Integer, Integer> entry : histogram.entrySet()) {
      retMap.put(entry.getKey(), entry.getValue() * 1.0f / sum);
    }

    return retMap;
  }

  public static Map<Integer, Float> cdf(Map<Integer, Integer> histogram) {
    final Map<Integer, Float> distribMap = percent(histogram);

    float sum = 0;
    final Map<Integer, Float> retMap = new HashMap<>(distribMap.size());
    for (Map.Entry<Integer, Float> entry : distribMap.entrySet()) {
      retMap.put(entry.getKey(), 1.0f - sum);
      sum += entry.getValue();
    }
    return retMap;
  }
}
