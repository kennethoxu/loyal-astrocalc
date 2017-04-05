package com;

import com.models.Dice;
import com.models.CombatMods;
import com.models.weapon.Weapon;
import com.testing.Test;
import com.utils.BulkAnalytics;
import com.utils.HistogramUtil;

import java.util.Arrays;

public class Main {

  public static final Weapon testPresent = new Weapon(
     Weapon.RangeClass.RANGED,
     Arrays.asList(),
     Arrays.asList(Dice.RED),
     null,
     2
  );

  public static void main(String[] args) {

    final CombatMods scm = CombatMods.Builder.newBuilder().damage(5).build();
    Test.printPercent(HistogramUtil.cdf(BulkAnalytics.histogram(testPresent, Arrays.asList(Dice.BLACK), scm,0)));
  }
}