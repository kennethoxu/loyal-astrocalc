package com.kox.r6astrocalc.ia_logic.models;

public class RollDefenseResult implements RollResult {

  final int block;
  final int evade;
  final int dodge;

  public RollDefenseResult(int block, int evade, int dodge) {
    this.block = block;
    this.evade = evade;
    this.dodge = dodge;
  }

  @Override
  public String toString() {
    return "RollDefenseResult{" +
       "block=" + block +
       ", evade=" + evade +
       ", dodge=" + dodge +
       '}';
  }
}
