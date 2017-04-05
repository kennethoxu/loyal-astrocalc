package com.models;

public class SurgeConsumer {

  public static final SurgeConsumer BASIC_1_DMG = new SurgeConsumer(1, 1, 0, 0);
  public static final SurgeConsumer BASIC_2_DMG = new SurgeConsumer(1, 2, 0, 0);
  public static final SurgeConsumer BASIC_1_DMG_2_ACC = new SurgeConsumer(1, 1, 0, 2);
  public static final SurgeConsumer BASIC_1_RANGE = new SurgeConsumer(1, 0, 0, 1);
  public static final SurgeConsumer BASIC_1_PIERCE = new SurgeConsumer(1, 0, 1, 0);
  public static final SurgeConsumer LS_3_PIERCE = new SurgeConsumer(1, 0, 3, 0);
  public static final SurgeConsumer ADV_2_DMG = new SurgeConsumer(2, 2, 0, 0);
  public static final SurgeConsumer ADV_3_DMG = new SurgeConsumer(2, 3, 0, 0);


  public final int cost;
  public final int damage;
  public final int pierce;
  public final int range;

  public SurgeConsumer(int cost, int damage, int pierce, int range) {
    this.cost = cost;
    this.damage = damage;
    this.pierce = pierce;
    this.range = range;
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
