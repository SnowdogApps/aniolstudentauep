package pl.snowdog.aniolstudentauep.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import pl.snowdog.aniolstudentauep.CreateProfileActivity;
import pl.snowdog.aniolstudentauep.R;
import pl.snowdog.aniolstudentauep.util.CircleTransform;


public class ProfileFillDataFragment extends Fragment {

    @InjectView(R.id.et_name)
    EditText etName;

    @InjectView(R.id.et_surname)
    EditText etSurname;

    @InjectView(R.id.et_age)
    EditText etAge;

    @InjectView(R.id.iv_avatar)
    ImageView ivAvatar;


    public ProfileFillDataFragment() {
        // Required empty public constructor
    }

    public static ProfileFillDataFragment newInstance() {
        ProfileFillDataFragment fragment = new ProfileFillDataFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_fill_data, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @OnClick(R.id.btn_next)
    public void click() {
        ((CreateProfileActivity) getActivity()).setViewPagerItem(1);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Picasso.with(getActivity()).load("http://i.imgur.com/upTOAfd.jpg").transform(new CircleTransform()).into(ivAvatar);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public String getName() {
        return etName.getText().toString();
    }

    public String getSurname() {
        return etSurname.getText().toString();
    }

    public int getAge() {
        int age = -1;
        try {
            age = Integer.parseInt(etAge.getText().toString());
        } catch (NumberFormatException e) {
            //TODO handle bad input
        }
        return age;
    }


}
