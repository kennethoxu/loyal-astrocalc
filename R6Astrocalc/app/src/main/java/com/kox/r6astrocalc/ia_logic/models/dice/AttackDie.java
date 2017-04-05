package com.kox.r6astrocalc.ia_logic.models.dice;

import com.kox.r6astrocalc.ia_logic.models.BulkResult;
import com.kox.r6astrocalc.ia_logic.models.Dice;
import com.kox.r6astrocalc.ia_logic.models.DiceColor;
import com.kox.r6astrocalc.ia_logic.models.RollAttackResult;

import java.util.Random;

public abstract class AttackDie implements Dice {

  private final Random random = new Random();

  private final float avgDamage;
  private final float avgSurge;
  private final float avgRange;

  private final int[] damage;
  private final int[] surge;
  private final int[] range;
  private final DiceColor diceColor;
  private final BulkResult[] bulkResults;

  AttackDie(int[] damage, int[] surge, int[] range, DiceColor diceColor) {
    int dmgSum = 0;
    int surgeSum = 0;
    int rangeSum = 0;
    for (int i = 0; i < damage.length; i++) {
      dmgSum += damage[i];
      surgeSum += surge[i];
      rangeSum += range[i];
    }

    this.avgDamage = dmgSum / 6f;
    this.avgSurge = surgeSum / 6f;
    this.avgRange = rangeSum / 6f;
    this.damage = damage;
    this.surge = surge;
    this.range = range;
    this.diceColor = diceColor;
    this.bulkResults = new BulkResult[6];
    for (int i = 0; i < 6; i++) {
      bulkResults[i] = new BulkResult(0, 0, 0, damage[i], surge[i], range[i]);
    }
  }

  public int[] getDamage() {
    return damage;
  }

  public int[] getSurge() {
    return surge;
  }

  public int[] getRange() {
    return range;
  }

  public float getAvgDamage() {
    return avgDamage;
  }

  public float getAvgSurge() {
    return avgSurge;
  }

  public float getAvgRange() {
    return avgRange;
  }

  public BulkResult[] getBulkResults() {
    return bulkResults;
  }

  @Override
  public RollAttackResult roll() {
    final int index = random.nextInt(6);
    return new RollAttackResult(damage[index], surge[index], range[index], diceColor);
  }
}
