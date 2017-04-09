package com;

import com.models.Dice;
import com.models.CombatMods;
<<<<<<< HEAD
import com.models.SurgeConsumer;
=======
>>>>>>> 7c354bc724cc2ff097a49a6ccae9e9bf80059c20
import com.models.weapon.Weapon;
import com.testing.Test;
import com.utils.BulkAnalytics;
import com.utils.HistogramUtil;

import java.util.Arrays;

public class Main {

  public static final Weapon testPresent = new Weapon(
     Weapon.RangeClass.RANGED,
<<<<<<< HEAD
     Arrays.asList(SurgeConsumer.BASIC_1_DMG, SurgeConsumer.BASIC_1_DMG),
     Arrays.asList(Dice.YELLOW, Dice.GREEN),
=======
     Arrays.asList(),
     Arrays.asList(Dice.RED),
>>>>>>> 7c354bc724cc2ff097a49a6ccae9e9bf80059c20
     null,
     2
  );

  public static void main(String[] args) {

<<<<<<< HEAD
    final CombatMods scm = CombatMods.Builder.newBuilder().surge(0).build();

    Test.printPercent(HistogramUtil.cdf(BulkAnalytics.histogram(testPresent, Arrays.asList(), scm,0)));
=======
    final CombatMods scm = CombatMods.Builder.newBuilder().damage(5).build();
    Test.printPercent(HistogramUtil.cdf(BulkAnalytics.histogram(testPresent, Arrays.asList(Dice.BLACK), scm,0)));
>>>>>>> 7c354bc724cc2ff097a49a6ccae9e9bf80059c20
  }
}