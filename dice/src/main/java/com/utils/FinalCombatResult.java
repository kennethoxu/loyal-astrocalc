package com.utils;

import com.models.BulkResult;

public class FinalCombatResult {

  public final int dodge;
  public final int block;
  public final int damage;
  public final int pierce;
  public final int range;
  public final int requiredRange;

  public FinalCombatResult(BulkResult bulkResult, int pierce, int requiredRange) {
    this.dodge = bulkResult.dodge;
    this.block = bulkResult.block;
    this.damage = bulkResult.damage;
    this.range = bulkResult.range;
    this.pierce = pierce;
    this.requiredRange = requiredRange;
  }

  public FinalCombatResult(int dodge, int block, int damage, int pierce, int range, int requiredRange) {
    this.dodge = dodge;
    this.block = block;
    this.damage = damage;
    this.pierce = pierce;
    this.range = range;
    this.requiredRange = requiredRange;
  }
}
