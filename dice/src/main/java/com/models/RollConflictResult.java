package com.models;

import java.util.List;

public class RollConflictResult {

  public final int dodge;
  public final int damage;
  public final int block;
  public final int surge;
  public final int evade;
  public final int range;
  private final List<RollAttackResult> rollAttackResults;
  private final List<RollDefenseResult> rollDefenseResults;

  public RollConflictResult(List<RollAttackResult> rars, List<RollDefenseResult> rdrs) {
    rollAttackResults = rars;
    rollDefenseResults = rdrs;
    int block = 0;
    int evade = 0;
    int dodge = 0;
    for (RollDefenseResult currRdr : rdrs) {
      block += currRdr.block;
      evade += currRdr.evade;
      dodge += currRdr.dodge;
    }

    int damage = 0;
    int surge = 0;
    int range = 0;
    for (RollAttackResult currRar : rars) {
      damage += currRar.damage;
      surge += currRar.surge;
      range += currRar.range;
    }

    this.block = block;
    this.evade = evade;
    this.dodge = dodge;
    this.damage = damage;
    this.surge = surge;
    this.range = range;
  }

  public RollConflictResolution resolve() {
    return new RollConflictResolution(dodge, range, damage - block, surge - evade);
  }

  public List<RollAttackResult> getRollAttackResults() {
    return rollAttackResults;
  }

  public List<RollDefenseResult> getRollDefenseResults() {
    return rollDefenseResults;
  }

  @Override
  public String toString() {
    return "RollConflictResult{" +
       "dodge=" + dodge +
       ", damage=" + damage +
       ", block=" + block +
       ", surge=" + surge +
       ", evade=" + evade +
       ", range=" + range +
       '}';
  }
}
