package pl.snowdog.aniolstudentauep.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import pl.snowdog.aniolstudentauep.CreateProfileActivity;
import pl.snowdog.aniolstudentauep.R;
import pl.snowdog.aniolstudentauep.StartAppActivity;
import pl.snowdog.aniolstudentauep.dao.Dao;
import pl.snowdog.aniolstudentauep.model.User;
import pl.snowdog.aniolstudentauep.util.PrefsUtil;

public class ProfileFillSkillsFragment extends Fragment {

    private static final String TAG = "ProfileFillSkillsFr";

    @InjectView(R.id.lv_skills)
    ListView lvSkills;

    @InjectView(R.id.et_search)
    AutoCompleteTextView etSearch;

    @InjectView(R.id.btn_add_skill)
    ImageButton btnAddSkill;

    private List<String> lSkills;
    private ArrayAdapter<String> mAdapter;
    private List<String> selectedSkillsList;


    public ProfileFillSkillsFragment() {
        // Required empty public constructor
    }

    public static ProfileFillSkillsFragment newInstance() {
        ProfileFillSkillsFragment fragment = new ProfileFillSkillsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick(R.id.btn_add_skill)
    public void onClick() {
        Dao.addTag(etSearch.getText().toString());

        mAdapter.notifyDataSetChanged();
        checkItemOnListView(etSearch.getText().toString());
        etSearch.setText("");
        btnAddSkill.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_next)
    public void onClickNext() {
        User user = ((CreateProfileActivity) getActivity()).getUserFromFragments();
        user.setAvatar("http://i.imgur.com/upTOAfd.jpg");
        user.setTel("345678912");
        user.setEmail("muniek@staszczyk.pl");
        Dao.addUser(user);
        PrefsUtil.setLoggedInUserId(getActivity(), user.getId());
        Intent intent = new Intent(getActivity(), StartAppActivity.class);
        startActivity(intent);
        Log.d(TAG, "CREATED USER : " + user.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_fill_skills, container, false);
        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnAddSkill.setVisibility(View.GONE);

        lSkills = Dao.getTags();
        mAdapter = new ArrayAdapter<String>(getActivity(), R.layout.skills_item, lSkills);
        lvSkills.setAdapter(mAdapter);

        final ArrayAdapter<String> mAutoCompleteAdapter = new ArrayAdapter<String>(getActivity(), R.layout.skills_item, lSkills);
        etSearch.setAdapter(mAutoCompleteAdapter);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (etSearch.getAdapter().getCount() == 0) {
                    btnAddSkill.setVisibility(View.VISIBLE);
                } else {
                    btnAddSkill.setVisibility(View.GONE);
                }
            }
        });
        etSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                checkItemOnListView(adapterView.getItemAtPosition(i).toString());
            }
        });

    }

    private void checkItemOnListView(String selectedItem) {
        for (int k = 0; k < mAdapter.getCount(); k++) {
            if (mAdapter.getItem(k).equals(selectedItem)) {
                lvSkills.setSelection(k);
                lvSkills.setItemChecked(k, true);
                etSearch.setText("");
                Toast.makeText(getActivity(),
                        selectedItem + " dodane do zainteresowań",
                        Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public List<String> getSelectedSkillsList() {
        List<String> checkedItems = new ArrayList<>();
        int len = lvSkills.getCount();
        SparseBooleanArray checked = lvSkills.getCheckedItemPositions();
        for (int i = 0; i < len; i++) {
            if (checked.get(i)) {
                checkedItems.add(mAdapter.getItem(i));
            }
        }

        return checkedItems;
    }

}
