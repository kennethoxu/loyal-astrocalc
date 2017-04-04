package com.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Weapon {

  private final List<SurgeConsumer> surgeConsumers;
  private final List<AttackDie> dices;

  public Weapon(List<SurgeConsumer> surgeConsumers, AttackDie... dices) {
    this.surgeConsumers = surgeConsumers;
    this.dices = new ArrayList<>(Arrays.asList(dices));
  }

  public List<SurgeConsumer> getSurgeConsumers() {
    return surgeConsumers;
  }

  public List<AttackDie> getDie() {
    return dices;
  }
}
