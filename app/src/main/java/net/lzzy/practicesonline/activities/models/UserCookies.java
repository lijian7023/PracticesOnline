package net.lzzy.practicesonline.activities.models;

import android.content.Context;
import android.content.SharedPreferences;

import net.lzzy.practicesonline.activities.utils.AppUtils;
import net.lzzy.practicesonline.activities.utils.DateTimeUtils;

import java.util.Date;


/**
 * Created by lzzy_gxy on 2019/4/17.
 * Description:
 */
public class UserCookies {
    private static final String KEY_TIME="keyTime";
    private SharedPreferences spTime;
    private static final UserCookies INSTANCE = new UserCookies();

    private UserCookies() {
        spTime=AppUtils.getContext()
                .getSharedPreferences("refresh_time", Context.MODE_PRIVATE);
        }
    public static UserCookies getInstance() {
        return INSTANCE;
    }
    public void updateLastRefreshTime(){
        String time= DateTimeUtils.DATE_TIME_FORMAT.format(new Date());
        spTime.edit().putString(KEY_TIME,time).apply();
    }
    public String getLastRefreshTime(){
        return spTime.getString(KEY_TIME,"");
    }
}
