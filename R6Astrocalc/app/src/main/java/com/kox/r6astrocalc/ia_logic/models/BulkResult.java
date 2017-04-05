package com.kox.r6astrocalc.ia_logic.models;

public class BulkResult {

  public final int dodge;
  public final int block;
  public final int evade;
  public final int damage;
  public final int surge;
  public final int range;

  public BulkResult(int dodge, int block, int evade, int damage, int surge, int range) {
    this.dodge = dodge;
    this.block = block;
    this.evade = evade;
    this.damage = damage;
    this.surge = surge;
    this.range = range;
  }

  public BulkResult add(BulkResult bulkResult) {
    return new BulkResult(dodge + bulkResult.dodge,
       block + bulkResult.block,
       evade + bulkResult.evade,
       damage + bulkResult.damage,
       surge + bulkResult.surge,
       range + bulkResult.range
    );
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BulkResult that = (BulkResult) o;

    if (dodge != that.dodge) return false;
    if (block != that.block) return false;
    if (evade != that.evade) return false;
    if (damage != that.damage) return false;
    if (surge != that.surge) return false;
    return range == that.range;
  }

  @Override
  public int hashCode() {
    int result = dodge;
    result = 31 * result + block;
    result = 31 * result + evade;
    result = 31 * result + damage;
    result = 31 * result + surge;
    result = 31 * result + range;
    return result;
  }

  @Override
  public String toString() {
    return "BulkResult{" +
       "block=" + block +
       ", evade=" + evade +
       ", damage=" + damage +
       ", surge=" + surge +
       '}';
  }
}
