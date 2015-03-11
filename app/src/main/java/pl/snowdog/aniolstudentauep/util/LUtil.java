package pl.snowdog.aniolstudentauep.util;


import android.os.Build;

/**
 * Created by chomi3 on 2014-11-25.
 */
public class LUtil {
    public static boolean isL() {
        return android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
