package com.kox.r6astrocalc.ia_logic;

import com.kox.r6astrocalc.ia_logic.models.CombatMods;
import com.kox.r6astrocalc.ia_logic.models.Dice;
import com.kox.r6astrocalc.ia_logic.models.SurgeConsumer;
import com.kox.r6astrocalc.ia_logic.models.dice.DefenceDie;
import com.kox.r6astrocalc.ia_logic.models.weapon.Weapon;
import com.kox.r6astrocalc.ia_logic.testing.Test;
import com.kox.r6astrocalc.ia_logic.utils.BulkAnalytics;
import com.kox.r6astrocalc.ia_logic.utils.HistogramUtil;

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

    final CombatMods cm = CombatMods.Builder.newBuilder().surge(0).build();

    Test.printPercent(HistogramUtil.cdf(BulkAnalytics.histogram(testPresent, Arrays.<DefenceDie>asList(), cm, 0)));
  }
}