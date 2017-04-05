package com.models.weapon;

import com.models.SurgeConsumer;
import com.models.dice.AttackDie;

import java.util.ArrayList;
import java.util.List;

public class Weapon {

  enum RangeClass {
    MELEE, RANGED
  }

  private final RangeClass rangeClass;
  private final List<SurgeConsumer> surgeConsumers;
  private final List<AttackDie> die;
  private final int maxAttachments;

  public Weapon(RangeClass rangeClass, List<SurgeConsumer> surgeConsumers, List<AttackDie> die, int maxAttachments) {
    this.rangeClass = rangeClass;
    this.surgeConsumers = new ArrayList<>(surgeConsumers);
    this.die = die;
    this.maxAttachments = maxAttachments;
  }

  public List<SurgeConsumer> getSurgeConsumers() {
    return surgeConsumers;
  }

  public List<AttackDie> getDie() {
    return die;
  }

  public RangeClass getRangeClass() {
    return rangeClass;
  }

  public int getMaxAttachments() {
    return maxAttachments;
  }
}
