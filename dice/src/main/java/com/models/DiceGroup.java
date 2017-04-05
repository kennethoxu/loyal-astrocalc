package com.models;

import com.models.dice.AttackDie;
import com.models.dice.DefenceDie;

import java.util.ArrayList;
import java.util.List;

public class DiceGroup {

  private final List<AttackDie> attackDie;
  private final List<DefenceDie> defenceDice;

  // Nullable on all this shit
  public DiceGroup(List<AttackDie> attackDie, List<DefenceDie> defenceDice) {
    this.attackDie = attackDie;
    this.defenceDice = defenceDice;
  }

  public RollConflictResult roll() {
    final List<RollDefenseResult> rollDefenseResults = new ArrayList<>(defenceDice.size());
    for (DefenceDie defenceDice : defenceDice) {
      rollDefenseResults.add(defenceDice.roll());
    }

    final List<RollAttackResult> rollAttackResults = new ArrayList<>(attackDie.size());
    for (AttackDie attackDie : this.attackDie) {
      rollAttackResults.add(attackDie.roll());
    }

    return new RollConflictResult(rollAttackResults, rollDefenseResults);
  }

  public List<AttackDie> getAttackDie() {
    return attackDie;
  }

  public List<DefenceDie> getDefenceDice() {
    return defenceDice;
  }
}
