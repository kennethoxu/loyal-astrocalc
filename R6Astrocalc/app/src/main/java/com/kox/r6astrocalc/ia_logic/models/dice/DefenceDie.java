package com.kox.r6astrocalc.ia_logic.models.dice;

import com.kox.r6astrocalc.ia_logic.models.BulkResult;
import com.kox.r6astrocalc.ia_logic.models.Dice;
import com.kox.r6astrocalc.ia_logic.models.RollDefenseResult;

import java.util.Random;

public class DefenceDie implements Dice {

  private final Random random = new Random();

  private final int[] block;
  private final int[] evade;
  private final int[] dodge;

  private final BulkResult[] bulkResults;

  public DefenceDie(int[] block, int[] evade, int[] dodge) {
    this.block = block;
    this.evade = evade;
    this.dodge = dodge;
    this.bulkResults = new BulkResult[6];
    for (int i = 0; i < 6; i++) {
      bulkResults[i] = new BulkResult(dodge[i], block[i], evade[i], 0, 0, 0);
    }
  }

  public int[] getBlock() {
    return block;
  }

  public int[] getEvade() {
    return evade;
  }

  public int[] getDodge() {
    return dodge;
  }

  public BulkResult[] getBulkResults() {
    return bulkResults;
  }

  @Override
  public RollDefenseResult roll() {
    final int index = random.nextInt(6);
    return new RollDefenseResult(block[index], evade[index], dodge[index]);
  }
}
