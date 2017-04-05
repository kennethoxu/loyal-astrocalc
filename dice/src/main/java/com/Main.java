package com;

import com.models.Dice;
import com.models.weapon.Armory;
import com.testing.Test;
import com.utils.Analytics;

public class Main {

  public static void main(String[] args) {

    Test.printPercent((Analytics.cdf(Armory.DL_44, Dice.BLACK, 0)));
  }
}