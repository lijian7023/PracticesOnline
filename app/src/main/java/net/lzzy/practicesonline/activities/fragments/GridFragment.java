package net.lzzy.practicesonline.activities.fragments;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import net.lzzy.practicesonline.R;

import net.lzzy.practicesonline.activities.models.view.QuestionResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzzy_gxy on 2019/5/13.
 * Description:
 */
public class GridFragment extends BaseFragment{

    public static final String QUESTION_RESULTS = "questionResults";
    private List<QuestionResult> questionResults;
    private OnItemInterface cutClickListener;

    public static GridFragment newInstance(List<QuestionResult> questionResults){
        Bundle args=new Bundle();
        args.putParcelableArrayList(QUESTION_RESULTS, (ArrayList<? extends Parcelable>) questionResults);
        GridFragment fragment=new GridFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if (getArguments()!=null){
            questionResults = getArguments()
                    .getParcelableArrayList(QUESTION_RESULTS);
           }
    }
    @Override
    protected void populate() {
        GridView gridView=find(R.id.fragment_grid_gv);
        TextView textView=find(R.id.fragment_grid_cut_view);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cutClickListener!=null){
                    cutClickListener.onGotoChart();
                }
            }
        });
        BaseAdapter adapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return  questionResults.size();
            }

            @Override
            public QuestionResult getItem(int position) {
                return questionResults.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.question_grid_item,null);
                }
                TextView textView=convertView.findViewById(R.id.question_grid_item_number);
                QuestionResult questionResult=questionResults.get(position);
                //设置背景
                if (questionResult.isRight()){
                    textView.setBackgroundResource(R.drawable.question_correct);
                }else {
                    textView.setBackgroundResource(R.drawable.question_error);
                }
                textView.setText(position+1+"");
                textView.setTag(position);

                return convertView;
            }
        };
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (cutClickListener!=null){
                    cutClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_grid;
    }

    @Override
    public void search(String kw) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemInterface){
            cutClickListener= (OnItemInterface) context;
        }else {
            throw new ClassCastException(context.toString()+"必须实现cutClickListener");
        }
    }

    @Override
    public void onDestroy() {
        cutClickListener=null;
        super.onDestroy();
    }
    public interface OnItemInterface {
        void onItemClick(int position);
        void onGotoChart();
    }

}
