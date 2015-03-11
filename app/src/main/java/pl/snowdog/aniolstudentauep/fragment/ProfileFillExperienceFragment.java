package pl.snowdog.aniolstudentauep.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import pl.snowdog.aniolstudentauep.CreateProfileActivity;
import pl.snowdog.aniolstudentauep.R;
import pl.snowdog.aniolstudentauep.adapter.UserExperienceAdapter;
import pl.snowdog.aniolstudentauep.dialog.AddExperienceDialogFragment;
import pl.snowdog.aniolstudentauep.model.UserExperience;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFillExperienceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFillExperienceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFillExperienceFragment extends Fragment {

    @InjectView(R.id.btn_add_experience)
    ImageButton btnAddExperience;

    @InjectView(R.id.lv_experience)
    ListView lvExperience;


    UserExperienceAdapter mAdapter;
    private List<UserExperience> lUserExperience;

    public ProfileFillExperienceFragment() {
        // Required empty public constructor
    }

    public static ProfileFillExperienceFragment newInstance() {
        ProfileFillExperienceFragment fragment = new ProfileFillExperienceFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_fill_experience, container, false);
        ButterKnife.inject(this, view);

        return view;
    }

    @OnClick(R.id.btn_next)
    public void click() {
        ((CreateProfileActivity) getActivity()).setViewPagerItem(2);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        btnAddExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddExperienceDialogFragment newFragment = new AddExperienceDialogFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), AddExperienceDialogFragment.TAG);
            }
        });
        lUserExperience = new ArrayList<>();
        mAdapter = new UserExperienceAdapter(getActivity(), R.layout.user_experience_item,
                lUserExperience);
        lvExperience.setAdapter(mAdapter);

        super.onViewCreated(view, savedInstanceState);
    }


    public void onEvent(UserExperience e) {
        lUserExperience.add(e);
        mAdapter.notifyDataSetChanged();
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

    public List<UserExperience> getExperienceList() {
        return lUserExperience;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
