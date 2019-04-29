package net.lzzy.practicesonline.activities.utils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import net.lzzy.practicesonline.R;
import net.lzzy.practicesonline.activities.activities.SplashActivity;



/**
 * Created by lzzy_gxy on 2019/4/15.
 * Description:
 */
public  class ViewUtils {
    private static AlertDialog dialog;

    public static void ShowProgress(Context context,String message){
        if (dialog==null){
            View view=LayoutInflater.from(context).inflate(R.layout.dialog_progress,null);
            TextView tv=view.findViewById(R.id.dialog_progress_tv);
            tv.setText(message);
            dialog=new AlertDialog.Builder(context).create();
            dialog.setView(view);
        }
        dialog.show();
    }
    public static void dismissProgress(){
        if (dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }

    public static void gotoSetting(Context context){
        View view= LayoutInflater.from(context).inflate(R.layout.dialog_setting,null);
        Pair<String,String> url=AppUtils.loadServerSetting(context);
        EditText edtIp=view.findViewById(R.id.dialog_setting_edt_ip);
        edtIp.setText(url.first);
        EditText edtPort=view.findViewById(R.id.dialog_setting_edt_port);
        edtPort.setText(url.second);
        new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("取消",(dialog, which) -> gotoMain(context))
                .setPositiveButton("保存",(dialog, which) -> {
                    String ip=edtIp.getText().toString();
                    String port=edtPort.getText().toString();
                    if(TextUtils.isEmpty(ip)||TextUtils.isEmpty(port)){
                        Toast.makeText(context,"信息不完整",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    AppUtils.saveServerSetting(ip,port,context);
                    gotoMain(context);
                })
                .show();

    }

    private static void gotoMain(Context context) {
        if (context instanceof SplashActivity){
            ((SplashActivity)context).gotoMain();
        }

    }

    public static abstract class AbstractQueryHandler implements SearchView.OnQueryTextListener {

        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return handleQuery(newText);
        }

        /**
         * handle query logic
         *
         * @param kw keyword
         * @return end query
         */
        public abstract boolean handleQuery(String kw);
    }

    public static abstract class AbstractTouchHandler implements View.OnTouchListener {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return handleTouch(event);
        }

        /**
         * 处理触摸事件
         *
         * @param event 触摸事件对象
         * @return 消费触摸事件吗
         */
        public abstract boolean handleTouch(MotionEvent event);
    }
    public abstract static class AbstractQueryListener implements SearchView.OnQueryTextListener{
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            handleQuery(newText);
            return true;
        }

        public abstract void handleQuery(String kw);
    }

}

