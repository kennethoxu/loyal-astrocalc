package com.utils;

import java.util.ArrayList;
import java.util.List;

public class Combinatorics {

  public static <T> List<List<T>> generateAllPerms(List<T> original) {
    if (original.isEmpty()) {
      List<List<T>> result = new ArrayList<>();
      result.add(new ArrayList<>());
      return result;
    }
    T firstElement = original.remove(0);
    final List<List<T>> returnValue = new ArrayList<>();
    final List<List<T>> permutations = generateAllPerms(original);
    for (List<T> smallerPermutated : permutations) {
      for (int index = 0; index <= smallerPermutated.size(); index++) {
        List<T> temp = new ArrayList<>(smallerPermutated);
        temp.add(index, firstElement);
        returnValue.add(temp);
      }
    }
    return returnValue;
  }
}
