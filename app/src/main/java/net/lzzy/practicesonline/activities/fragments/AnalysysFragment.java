package net.lzzy.practicesonline.activities.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import androidx.annotation.Nullable;

import net.lzzy.practicesonline.R;
import net.lzzy.practicesonline.activities.view.BarChartView;
import net.lzzy.practicesonline.activities.models.view.QuestionResult;
import net.lzzy.practicesonline.activities.models.view.WrongType;

import java.util.ArrayList;
import java.util.List;

public class AnalysysFragment extends BaseFragment {
    private List<QuestionResult> questionResultList;
    public static final String QUESTION_RESULT = "questionResult";
    private ChartFragment.OnItemInterface viewCutInterface;


    /**
     * GridFragment静态工厂类（创建一个GridFragment）
     *
     * @param questionResults 数据源
     * @return GridFragment
     */
    public static AnalysysFragment newInstance(List<QuestionResult> questionResults) {
        //寄存器（用来暂存数据）
        Bundle arge = new Bundle();
        //创建一个GridFragment对象
        AnalysysFragment analysysFragment = new AnalysysFragment();
        //往寄存器存数据
        arge.putParcelableArrayList(QUESTION_RESULT, (ArrayList<? extends Parcelable>) questionResults);
        //将储存器保存到当前GridFragment对象
        analysysFragment.setArguments(arge);
        return analysysFragment;
    }

    private static String[] HORIZONTAL_AXIS;
    private static float[] DATA;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化时取回数据源
        if (getArguments() != null) {
            questionResultList = getArguments().getParcelableArrayList(QUESTION_RESULT);
            HORIZONTAL_AXIS = new String[questionResultList.size()];
            float a = 0, b = 0, c = 0, d = 0;
            for (int i = 0; i < WrongType.values().length; i++) {
                HORIZONTAL_AXIS[i] = WrongType.getInstance(i).toString();
            }
            for (QuestionResult q : questionResultList ) {
                switch (q.getWrongType()) {
                    case RIGHT_OPTIONS:
                        a++;
                        break;
                    case MISS_OPTIONS:
                        b++;
                        break;
                    case EXTRA_OPTIONS:
                        c++;
                        break;
                    case WRONG_OPTIONS:
                        d++;
                        break;
                    default:
                        break;
                }
            }
            DATA = new float[]{a, b, c, d};
        }
    }
    @Override
    protected void populate() {
        BarChartView barChart = find(R.id.bar_chart);
        barChart.setHorizontalAxis(HORIZONTAL_AXIS);
        barChart.setDataList(DATA, 15);


        find(R.id.fragment_analusys_cut_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewCutInterface.onGotoGrid();
            }
        });

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_analusys;
    }

    @Override
    public void search(String kw) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ChartFragment.OnItemInterface) {
            viewCutInterface = (ChartFragment.OnItemInterface) context;
        } else {
            throw new ClassCastException(context.toString() + "必须实现cutViewInterface");
        }
    }

    @Override
    public void onDestroy() {
        viewCutInterface = null;
        super.onDestroy();
    }
}
