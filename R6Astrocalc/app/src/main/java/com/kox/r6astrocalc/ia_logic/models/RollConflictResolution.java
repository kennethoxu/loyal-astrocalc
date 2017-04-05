package com.kox.r6astrocalc.ia_logic.models;

public class RollConflictResolution {

  final int dodge;
  final int range;
  final int damage;
  final int surge;

  public RollConflictResolution(int dodge, int range, int damage, int surge) {
    this.dodge = dodge;
    this.range = range;
    this.damage = damage;
    this.surge = surge;
  }

  @Override
  public String toString() {
    return "RollConflictResolution{" +
       "dodge=" + dodge +
       ", range=" + range +
       ", damage=" + damage +
       ", surge=" + surge +
       '}';
  }
}
