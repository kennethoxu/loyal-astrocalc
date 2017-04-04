package com.models;

/**
 * Created by kennethoxu on 4/1/17.
 */
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
