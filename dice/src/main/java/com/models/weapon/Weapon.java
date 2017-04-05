package com.models.weapon;

import com.models.CombatMods;
import com.models.SurgeConsumer;
import com.models.dice.AttackDie;

import java.util.List;

public class Weapon {

  public enum RangeClass {
    MELEE, RANGED
  }

  private final RangeClass rangeClass;
  private final List<SurgeConsumer> surgeConsumers;
  private final List<AttackDie> die;
  private final CombatMods scm;
  private final int maxAttachments;


  public Weapon(RangeClass rangeClass, List<SurgeConsumer> surgeConsumers, List<AttackDie> die, CombatMods scm, int maxAttachments) {
    this.rangeClass = rangeClass;
    this.surgeConsumers = surgeConsumers;
    this.die = die;
    if (scm == null) {
      this.scm = new CombatMods();
    } else {
      this.scm = scm;
    }
    this.maxAttachments = maxAttachments;
  }

  public RangeClass getRangeClass() {
    return rangeClass;
  }

  public List<SurgeConsumer> getSurgeConsumers() {
    return surgeConsumers;
  }

  public List<AttackDie> getDie() {
    return die;
  }

  public CombatMods getScm() {
    return scm;
  }

  public int getMaxAttachments() {
    return maxAttachments;
  }
}
