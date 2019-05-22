package net.lzzy.practicesonline.activities.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.lzzy.practicesonline.R;
import net.lzzy.practicesonline.activities.fragments.AnalysysFragment;
import net.lzzy.practicesonline.activities.fragments.ChartFragment;
import net.lzzy.practicesonline.activities.fragments.GridFragment;
import net.lzzy.practicesonline.activities.models.view.QuestionResult;
import net.lzzy.practicesonline.activities.utils.AppUtils;
import net.lzzy.practicesonline.activities.utils.ViewUtils;

import java.util.List;

/**
 * Created by lzzy_gxy on 2019/5/13.
 * Description:
 */
public class ResultActivity extends BaseActivity implements
    GridFragment.OnItemInterface,ChartFragment.OnItemInterface {

    public static final String POSITION = "position";
    public static final int RESULT_CODE = 0;
    public static final String PRACTICE_ID = "practiceId";
    private List<QuestionResult> results;
    private String practiceId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        practiceId=getIntent().getStringExtra(QuestionActivity.EXTRA_PRACTICE_ID);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_result;
    }

    @Override
    protected int getContainerId() {
        return R.id.activity_result_container;
    }

    @Override
    protected Fragment createFragment() {
        results=getIntent().getParcelableArrayListExtra(QuestionActivity.EXTRA_RESULT);
        return GridFragment.newInstance(results);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent=new Intent();
        intent.putExtra(POSITION,position);
        setResult(RESULT_CODE,intent);
        finish();
    }

    @Override
    public void onGotoChart() {
        getManager().beginTransaction().replace(R.id.
                activity_result_container, ChartFragment.newInstance(results)).commit();

    }

    @Override
    public void onGotoGrid() {
        getManager().beginTransaction().replace(R.id.
                activity_result_container, GridFragment.newInstance(results)).commit();

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        new AlertDialog.Builder(this)
                .setMessage("返回到哪里？")
                .setNeutralButton("返回题目",(dialog,which)->{
                    finish();
                })
                .setNegativeButton("章节列表",(dialog,which)-> {
                    intent.setClass(this,PracticesActivity.class);
                    startActivity(intent);
                    finish();
                })
                .setPositiveButton("查看收藏",(dialog,which)-> {
                    intent.putExtra(PRACTICE_ID,practiceId);
                    setResult(QuestionActivity.COLLECT_RESULT_CODE,intent);
                    finish();
                })
                .show();
    }

}
