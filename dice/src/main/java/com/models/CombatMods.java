package com.models;

/**
 * Created by kennethoxu on 4/4/17.
 */
public class CombatMods {

  public final int damage;
  public final int pierce;
  public final int range;
  public final int surge;
  public final int evade;
  public final int block;

  public CombatMods(int damage, int pierce, int range, int surge, int evade, int block) {
    this.damage = damage;
    this.pierce = pierce;
    this.range = range;
    this.surge = surge;
    this.evade = evade;
    this.block = block;
  }

  public CombatMods() {
    this.damage = 0;
    this.pierce = 0;
    this.range = 0;
    this.surge = 0;
    this.evade = 0;
    this.block = 0;
  }


  public static final class Builder {
    public int damage;
    public int pierce;
    public int range;
    public int surge;
    public int evade;
    public int block;

    private Builder() {
    }

    public static Builder newBuilder() {
      return new Builder();
    }

    public Builder damage(int damage) {
      this.damage = damage;
      return this;
    }

    public Builder pierce(int pierce) {
      this.pierce = pierce;
      return this;
    }

    public Builder range(int range) {
      this.range = range;
      return this;
    }

    public Builder surge(int surge) {
      this.surge = surge;
      return this;
    }

    public Builder evade(int evade) {
      this.evade = evade;
      return this;
    }

    public Builder block(int block) {
      this.block = block;
      return this;
    }

    public CombatMods build() {
      CombatMods combatMods = new CombatMods(damage, pierce, range, surge, evade, block);
      return combatMods;
    }
  }
}
