package net.lzzy.practicesonline.activities.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by lzzy_gxy on 2019/4/24.
 * Description:
 */
public class DateTimeUtils {
    public static final SimpleDateFormat DATE_TIME_FORMAT=
            new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.CANADA);
    public static final SimpleDateFormat DATE_FORMAT=
            new SimpleDateFormat("yyyy_MM_dd", Locale.CANADA);
}
