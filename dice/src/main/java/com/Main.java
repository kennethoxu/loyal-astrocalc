package com;

import com.models.Dice;
import com.models.CombatMods;
import com.models.SurgeConsumer;
import com.models.weapon.Weapon;
import com.testing.Test;
import com.utils.BulkAnalytics;
import com.utils.HistogramUtil;

import java.util.Arrays;

public class Main {

  public static final Weapon testPresent = new Weapon(
     Weapon.RangeClass.RANGED,
     Arrays.asList(SurgeConsumer.BASIC_1_DMG, SurgeConsumer.BASIC_1_DMG),
     Arrays.asList(Dice.YELLOW, Dice.GREEN),
     null,
     2
  );

  public static void main(String[] args) {

    final CombatMods scm = CombatMods.Builder.newBuilder().surge(0).build();

    Test.printPercent(HistogramUtil.cdf(BulkAnalytics.histogram(testPresent, Arrays.asList(), scm,0)));
  }
}