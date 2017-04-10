package com.kox.r6astrocalc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.kox.r6astrocalc.ia_logic.models.CombatMods;
import com.kox.r6astrocalc.ia_logic.models.Dice;
import com.kox.r6astrocalc.ia_logic.models.SurgeConsumer;
import com.kox.r6astrocalc.ia_logic.models.dice.DefenceDie;
import com.kox.r6astrocalc.ia_logic.models.weapon.Weapon;
import com.kox.r6astrocalc.ia_logic.utils.BulkAnalytics;
import com.kox.r6astrocalc.ia_logic.utils.HistogramUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.chart) LineChart mLineChart;

  public static final Weapon testPresent = new Weapon(
      Weapon.RangeClass.RANGED,
      Arrays.asList(SurgeConsumer.BASIC_1_DMG, SurgeConsumer.BASIC_1_DMG),
      Arrays.asList(Dice.YELLOW, Dice.GREEN),
      null,
      2
  );

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);


    final CombatMods cm = CombatMods.Builder.newBuilder().surge(0).build();

    final List<Entry> entries = new ArrayList<>();
    final Map<Integer, Float> map =
        HistogramUtil.cdf(BulkAnalytics.histogram(testPresent, Arrays.asList(), cm, 0));

    for (Map.Entry<Integer, Float> currEntry : map.entrySet()) {
      entries.add(new Entry(currEntry.getKey(), currEntry.getValue() * 100));
    }

    LineDataSet dataSet = new LineDataSet(entries, "Yolo");
    dataSet.setValueTextSize(15f);
    dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
    dataSet.setDrawValues(true);
    dataSet.setLineWidth(2);
    dataSet.setCircleRadius(5);
    dataSet.setColor(getResources().getColor(R.color.colorAccent));
    dataSet.setCubicIntensity(0.10f);
    dataSet.setCircleColor(getResources().getColor(R.color.colorAccent));

    LineData lineData = new LineData(dataSet);
    mLineChart.setData(lineData);
    mLineChart.invalidate(); // refresh

  }
}
