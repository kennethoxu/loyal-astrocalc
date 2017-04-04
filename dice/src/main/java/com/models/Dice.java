package com.models;

public interface Dice {

  AttackDie RED = new RedDice();
  AttackDie GREEN = new GreenDice();
  AttackDie BLUE = new BlueDice();
  AttackDie YELLOW = new YellowDice();

  DefenceDie BLACK = new BlackDice();
  DefenceDie WHITE = new WhiteDice();

  RollResult roll();
}
