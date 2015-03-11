package pl.snowdog.aniolstudentauep.util;


import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.view.View;


/**
 * Created by chomi3 on 2014-11-21.
 */
public class RipplesHelper {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void colorRipple(View buttonView, int bgColor, int tintColor) {
        if (LUtil.isL()) {
            RippleDrawable ripple = (RippleDrawable) buttonView.getBackground();
            ColorDrawable rippleBackground = (ColorDrawable) ripple.getDrawable(0);
            rippleBackground.setColor(bgColor);
            ripple.setColor(ColorStateList.valueOf(tintColor));
        } else {
            buttonView.setBackground(new ColorDrawable(bgColor));
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void colorRippleButton(View buttonView, int bgColor, int tintColor) {
        if (LUtil.isL()) {
            RippleDrawable ripple = (RippleDrawable) buttonView.getBackground();
            ripple.setColor(ColorStateList.valueOf(tintColor));

            InsetDrawable d = (InsetDrawable) ripple.getDrawable(0);
            GradientDrawable shape = (GradientDrawable) d.getDrawable();
            ((GradientDrawable) shape.mutate()).setColor(bgColor);

        } else {
            buttonView.setBackground(new ColorDrawable(bgColor));
        }
    }
}
