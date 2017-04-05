package com.kox.r6astrocalc.ia_logic.models;


import com.kox.r6astrocalc.ia_logic.models.dice.AttackDie;
import com.kox.r6astrocalc.ia_logic.models.dice.BlackDie;
import com.kox.r6astrocalc.ia_logic.models.dice.BlueDice;
import com.kox.r6astrocalc.ia_logic.models.dice.DefenceDie;
import com.kox.r6astrocalc.ia_logic.models.dice.GreenDie;
import com.kox.r6astrocalc.ia_logic.models.dice.RedDie;
import com.kox.r6astrocalc.ia_logic.models.dice.WhiteDice;
import com.kox.r6astrocalc.ia_logic.models.dice.YellowDie;

public interface Dice {

  AttackDie RED = new RedDie();
  AttackDie GREEN = new GreenDie();
  AttackDie BLUE = new BlueDice();
  AttackDie YELLOW = new YellowDie();

  DefenceDie BLACK = new BlackDie();
  DefenceDie WHITE = new WhiteDice();

  RollResult roll();
}
