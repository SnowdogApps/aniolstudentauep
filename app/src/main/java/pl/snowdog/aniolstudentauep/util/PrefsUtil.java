package pl.snowdog.aniolstudentauep.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by chomi3 on 2015-02-26.
 */
public class PrefsUtil {
    private final static String LOGGED_IN_USER_ID = "user_id";

    public static int getLoggedInUserId(Context ctx) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(ctx);
        if (settings == null) return Integer.MIN_VALUE;
        int v = settings.getInt(LOGGED_IN_USER_ID, Integer.MIN_VALUE);
        return v;
    }

    public static void setLoggedInUserId(Context ctx, int v) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(ctx);
        if (settings == null) return;

        SharedPreferences.Editor editor = settings.edit();
        if (editor == null) return;
        editor.putInt(LOGGED_IN_USER_ID, v);
        editor.apply();
    }
}
