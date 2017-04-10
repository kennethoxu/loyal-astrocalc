package com.kox.r6astrocalc;

import com.kox.r6astrocalc.ia_logic.models.CombatMods;
import com.kox.r6astrocalc.ia_logic.models.Dice;
import com.kox.r6astrocalc.ia_logic.models.SurgeConsumer;
import com.kox.r6astrocalc.ia_logic.models.dice.DefenceDie;
import com.kox.r6astrocalc.ia_logic.models.weapon.Weapon;
import com.kox.r6astrocalc.ia_logic.utils.BulkAnalytics;
import com.kox.r6astrocalc.ia_logic.utils.HistogramUtil;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class IaUnitTest {

  public static final Weapon testPresent = new Weapon(
      Weapon.RangeClass.RANGED,
      Arrays.asList(SurgeConsumer.BASIC_1_DMG, SurgeConsumer.BASIC_1_DMG),
      Arrays.asList(Dice.YELLOW, Dice.GREEN, Dice.BLUE),
      null,
      2
  );

  @Test
  public void addition_isCorrect() throws Exception {
    assertEquals(4, 2 + 2);
    final CombatMods cm = CombatMods.Builder.newBuilder().surge(0).build();
    com.kox.r6astrocalc.ia_logic.testing.Test.printPercent(HistogramUtil.cdf(BulkAnalytics.histogram(testPresent, Arrays.asList(), cm, 0)));
  }
}