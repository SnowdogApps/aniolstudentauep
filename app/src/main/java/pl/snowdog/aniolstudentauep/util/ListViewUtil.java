package pl.snowdog.aniolstudentauep.util;

/**
 * Created by Bartek on 2015-02-26.
 */

import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewUtil {
    private static final String TAG = "ListViewUtil";

    public static void setListViewHeightBasedOnChildrenBasic(ListView listView, int rowHeight) {
        ArrayAdapter<?> listAdapter = (ArrayAdapter<?>) listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem != null) {
                listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
                Log.d(TAG, "lvu VIEW height: " + listItem.getMeasuredHeight() + " i: " + i);
                totalHeight += listItem.getMeasuredHeight();
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        totalHeight = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount()));

        params.height = totalHeight;
        listView.setLayoutParams(params);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ArrayAdapter<?> listAdapter = (ArrayAdapter<?>) listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem != null) {
                listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
                Log.d(TAG, "lvu VIEW height: " + listItem.getMeasuredHeight() + " i: " + i);
                totalHeight += listItem.getMeasuredHeight();
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        totalHeight = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount()));
        Log.d(TAG, "listviewutil total height:: " + totalHeight);
        params.height = totalHeight;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public static int getSingleRowHeight(ListView listView, int position) {
        ArrayAdapter<?> listAdapter = (ArrayAdapter<?>) listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return 0;
        }

        int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.AT_MOST);
        View listItem = null;
        try {
            listItem = listAdapter.getView(position, null, listView);
            listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return listItem != null ? listItem.getMeasuredHeight() : 0;
    }
}