package com.models;

import com.models.dice.*;

public interface Dice {

  AttackDie RED = new RedDie();
  AttackDie GREEN = new GreenDie();
  AttackDie BLUE = new BlueDice();
  AttackDie YELLOW = new YellowDie();

  DefenceDie BLACK = new BlackDie();
  DefenceDie WHITE = new WhiteDice();

  RollResult roll();
}
