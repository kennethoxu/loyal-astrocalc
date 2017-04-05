package com.models.weapon;

import com.models.Dice;
import com.models.SurgeConsumer;

import java.util.Arrays;

/**
 * Created by kennethoxu on 4/4/17.
 */
public class Armory {

  public static final Weapon DL_44 = new Weapon(
     Weapon.RangeClass.RANGED,
     Arrays.asList(SurgeConsumer.BASIC_2_DMG, SurgeConsumer.BASIC_1_DMG_2_ACC),
     Arrays.asList(Dice.BLUE, Dice.YELLOW),
     null,
     2
  );

  public static final Weapon SHROUDED_LIGHTSABER = new Weapon(
     Weapon.RangeClass.MELEE,
     Arrays.asList(SurgeConsumer.LS_3_PIERCE, SurgeConsumer.BASIC_1_DMG, SurgeConsumer.BASIC_1_DMG, SurgeConsumer.BASIC_2_DMG),
     Arrays.asList(Dice.GREEN, Dice.YELLOW),
     null,
     2
  );
}
