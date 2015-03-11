package pl.snowdog.aniolstudentauep.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

import de.greenrobot.event.EventBus;
import pl.snowdog.aniolstudentauep.bus.DateSetEvent;

/**
 * Created by chomi3 on 2015-02-26.
 */
public class DatePickerDialogFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    public static final String TAG = "DatePickerDialogFragment";
    public final static int FROM = 0;
    public final static int TO = 1;
    public final static String ARG_PICKER_MODE = "arg_picker_mode";


    private int mFromOrTo;

    public DatePickerDialogFragment() {

    }

    public static DatePickerDialogFragment newInstance(int fromOrTo) {
        DatePickerDialogFragment fragment = new DatePickerDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PICKER_MODE, fromOrTo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_PICKER_MODE)) {
            this.mFromOrTo = getArguments().getInt(ARG_PICKER_MODE);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        String date = year + "-" + month + "-" + day;
        EventBus.getDefault().post(new DateSetEvent(mFromOrTo, date));
    }
}


