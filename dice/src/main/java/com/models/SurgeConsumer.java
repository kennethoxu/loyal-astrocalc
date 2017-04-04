package com.models;

public class SurgeConsumer {

  public static final SurgeConsumer BASIC_1_DMG = new SurgeConsumer(1, 1, 0, 0);
  public static final SurgeConsumer BASIC_2_DMG = new SurgeConsumer(1, 2, 0, 0);
  public static final SurgeConsumer BASIC_1_RANGE = new SurgeConsumer(1, 0, 0, 1);
  public static final SurgeConsumer BASIC_1_PIERCE = new SurgeConsumer(1, 0, 1, 0);
  public static final SurgeConsumer LS_3_PIERCE = new SurgeConsumer(1, 0, 3, 0);
  public static final SurgeConsumer ADV_2_DMG = new SurgeConsumer(2, 2, 0, 0);


  private final int cost;
  private final int damage;
  private final int pierce;
  private final int range;

  public SurgeConsumer(int cost, int damage, int pierce, int range) {
    this.cost = cost;
    this.damage = damage;
    this.pierce = pierce;
    this.range = range;
  }

  public int getCost() {
    return cost;
  }

  public int getDamage() {
    return damage;
  }

  public int getPierce() {
    return pierce;
  }

  public int getRange() {
    return range;
  }


  @Override
  public String toString() {
    return "SurgeConsumer{" +
       "cost=" + cost +
       ", damage=" + damage +
       ", pierce=" + pierce +
       ", range=" + range +
       '}';
  }
}
