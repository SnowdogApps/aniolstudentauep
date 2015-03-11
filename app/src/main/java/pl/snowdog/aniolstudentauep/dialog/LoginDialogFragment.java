package pl.snowdog.aniolstudentauep.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.snowdog.aniolstudentauep.CreateProfileActivity;
import pl.snowdog.aniolstudentauep.R;
import pl.snowdog.aniolstudentauep.StudentOrAngelActivity;

public class LoginDialogFragment extends DialogFragment implements View.OnClickListener {
    public static final String TAG = "LoginDialogFragment";

    @InjectView(R.id.btn_facebook)
    ImageButton btnFacebook;

    @InjectView(R.id.btn_linkedin)
    ImageButton btnLinkedin;

    @InjectView(R.id.btn_google_plus)
    ImageButton btnPlus;

    private int mUserType;

    public LoginDialogFragment() {
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
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialog.dismiss();
                    return true;
                }
                return false;
            }
        });

        return d;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_dialog, container, false);
        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        btnFacebook.setOnClickListener(this);
        btnLinkedin.setOnClickListener(this);
        btnPlus.setOnClickListener(this);

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUserType = getArguments().getInt(StudentOrAngelActivity.ARG_SELECTED_USER_TYPE);
        }
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), CreateProfileActivity.class);
        intent.putExtra(StudentOrAngelActivity.ARG_SELECTED_USER_TYPE, mUserType);
        startActivity(intent);
    }
}
