package net.lzzy.practicesonline.activities.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.lzzy.practicesonline.R;

public class LineChartMainActivity extends AppCompatActivity {

    private static final String[] HORIZONTAL_AXIS= {"1", "2", "3", "4",
            "5", "6", "7", "8", "9", "10", "11", "12"};

    private static final int[] DATA = {12, 24, 45, 56, 89, 70, 49, 22, 23, 10, 12, 3};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_line_chact);
        LineChartView lineChartView = (LineChartView) findViewById(R.id.line_chart_view);
        lineChartView.setHorizontalAxis(HORIZONTAL_AXIS);
        lineChartView.setDataList(DATA, 89);
    }
}