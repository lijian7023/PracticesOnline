package net.lzzy.practicesonline.activities.models;

import net.lzzy.practicesonline.activities.constants.DbConstants;
import net.lzzy.practicesonline.activities.utils.AppUtils;
import net.lzzy.sqllib.SqlRepository;

/**
 * Created by lzzy_gxy on 2019/4/17.
 * Description:
 */
public class UserCookies {
    private static final UserCookies OUR_INSTANCE = new UserCookies();

    public static UserCookies getInstance() {
        return OUR_INSTANCE;
    }

    private UserCookies() {
        }
}
