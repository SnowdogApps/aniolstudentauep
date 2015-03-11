package pl.snowdog.aniolstudentauep.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import pl.snowdog.aniolstudentauep.R;
import pl.snowdog.aniolstudentauep.bus.DateSetEvent;
import pl.snowdog.aniolstudentauep.model.UserExperience;

public class AddExperienceDialogFragment extends DialogFragment {
    public static final String TAG = "AddExperienceDialogFragment";

    @InjectView(R.id.et_name)
    EditText etName;

    @InjectView(R.id.tv_date_from)
    TextView tvDateFrom;

    @InjectView(R.id.tv_date_to)
    TextView tvDateTo;

    private boolean isDateDialogShowing;


    public AddExperienceDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog d = super.onCreateDialog(savedInstanceState);
        d.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        d.setCancelable(false);
        d.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (!isDateDialogShowing) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        dialog.dismiss();
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        });

        return d;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_experience_dialog, container, false);
        ButterKnife.inject(this, view);

        return view;
    }

    public void onEvent(DateSetEvent e) {
        switch (e.getFromOrTo()) {
            case DatePickerDialogFragment.FROM:
                tvDateFrom.setText(e.getDate());
                break;
            case DatePickerDialogFragment.TO:
                tvDateTo.setText(e.getDate());
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @OnClick(R.id.btn_date_from)
    void onDateFromClicked() {
        isDateDialogShowing = true;
        DialogFragment newFragment = new DatePickerDialogFragment().newInstance(DatePickerDialogFragment.FROM);
        newFragment.show(getActivity().getSupportFragmentManager(), DatePickerDialogFragment.TAG);
    }

    @OnClick(R.id.btn_date_to)
    void onDateToClicked() {
        isDateDialogShowing = true;
        DialogFragment newFragment = new DatePickerDialogFragment().newInstance(DatePickerDialogFragment.TO);
        newFragment.show(getActivity().getSupportFragmentManager(), DatePickerDialogFragment.TAG);
    }

    @OnClick(R.id.btn_add)
    void onBtnAddClicked() {
        EventBus.getDefault().post(new UserExperience(etName.getText().toString(),
                tvDateFrom.getText().toString() + " - " + tvDateTo.getText().toString()));
        isDateDialogShowing = false;
        dismiss();
    }

}
