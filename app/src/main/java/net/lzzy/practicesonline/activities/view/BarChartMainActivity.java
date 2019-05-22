package net.lzzy.practicesonline.activities.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.lzzy.practicesonline.R;

public class BarChartMainActivity extends AppCompatActivity {

    private static final String[] HORIZONTAL_AXIS= {"1", "2", "3", "4",
            "5", "6", "7", "8", "9", "10", "11", "12"};

    private static final float[] DATA = {12, 24, 45, 56, 89, 70, 49, 22, 23, 10, 12, 3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart_main);
        BarChartView lineChart = (BarChartView) findViewById(R.id.bar_chart);
        lineChart.setHorizontalAxis(HORIZONTAL_AXIS);
        lineChart.setDataList(DATA, 89);
    }

}
