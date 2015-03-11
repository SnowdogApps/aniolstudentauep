package pl.snowdog.aniolstudentauep.fragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import pl.snowdog.aniolstudentauep.R;
import pl.snowdog.aniolstudentauep.dao.Dao;

public class FilterFragment extends DialogFragment {
    private static final String ARG_PARAM_ANGEL = "angel";
    private static final String ARG_PARAM_STUDENT = "student";
    private static final String ARG_PARAM_SKILLS = "skills";
    private static final String ARG_PARAM_name = "name";
    private static final String ARG_PARAM_COMMON = "common";
    private static final String ARG_PARAM_COMMON_SKILLS = "common_skils";
    @InjectView(R.id.students)
    CheckBox students;
    @InjectView(R.id.absolvents)
    CheckBox absolvents;
    @InjectView(R.id.common_interests)
    CheckBox commonInterests;
    @InjectView(R.id.interests)
    ListView interests;
    @InjectView(R.id.cancel)
    Button cancel;
    @InjectView(R.id.ok)
    Button ok;
    private boolean angel;
    private boolean student;
    private List<String> skills;
    private String name;
    private OnFragmentInteractionListener mListener;
    private ArrayList<String> commonSkills;
    private boolean common;

    private ArrayAdapter<String> mAdapter;
    private List<String> lSkills;

    public FilterFragment() {
        // Required empty public constructor
    }

    public static FilterFragment newInstance(boolean angel, boolean student, boolean common,
                                             ArrayList<String> userSkills,
                                             ArrayList<String> skills, String name) {
        FilterFragment fragment = new FilterFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM_ANGEL, angel);
        args.putBoolean(ARG_PARAM_STUDENT, student);
        args.putBoolean(ARG_PARAM_COMMON, common);
        args.putStringArrayList(ARG_PARAM_COMMON_SKILLS, userSkills);
        args.putStringArrayList(ARG_PARAM_SKILLS, skills);
        args.putString(ARG_PARAM_name, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            angel = getArguments().getBoolean(ARG_PARAM_ANGEL);
            student = getArguments().getBoolean(ARG_PARAM_STUDENT);
            common = getArguments().getBoolean(ARG_PARAM_COMMON);
            commonSkills = getArguments().getStringArrayList(ARG_PARAM_COMMON_SKILLS);
            skills = getArguments().getStringArrayList(ARG_PARAM_SKILLS);
            name = getArguments().getString(ARG_PARAM_name);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_filter, container, false);
        ButterKnife.inject(this, rootView);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        absolvents.setChecked(angel);
        students.setChecked(student);
        commonInterests.setChecked(common);

        if (common) {
            interests.setVisibility(View.INVISIBLE);
        } else {
            interests.setVisibility(View.VISIBLE);
        }

        lSkills = Dao.getTags();
        mAdapter = new ArrayAdapter<String>(getActivity(), R.layout.skills_item, lSkills);
        interests.setAdapter(mAdapter);

        return rootView;
    }

    @OnCheckedChanged(R.id.common_interests)
    public void onCommonClicked(boolean checked) {
        if (checked) {
            interests.setVisibility(View.INVISIBLE);
        } else {
            interests.setVisibility(View.VISIBLE);
        }
    }


    public ArrayList<String> getSelectedSkillsList() {
        ArrayList<String> checkedItems = new ArrayList<>();
        int len = interests.getCount();
        SparseBooleanArray checked = interests.getCheckedItemPositions();
        for (int i = 0; i < len; i++) {
            if (checked.get(i)) {
                checkedItems.add(mAdapter.getItem(i));

            }
        }

        return checkedItems;
    }


    // TODO: Rename method, update argument and hook method into UI event
    @OnClick(R.id.ok)
    public void onButtonPressed() {
        if (mListener != null) {
            if (commonInterests.isChecked()) {
                mListener.onFragmentInteraction(absolvents.isChecked(), students.isChecked(),
                        commonInterests.isChecked(), commonSkills, null);
            } else {

                mListener.onFragmentInteraction(absolvents.isChecked(), students.isChecked(),
                        commonInterests.isChecked(), getSelectedSkillsList(), null);
            }
        }

        dismiss();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @OnClick(R.id.cancel)
    void cancel() {
        dismiss();
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(boolean angel, boolean student, boolean common,
                                          ArrayList<String> skills, String name);
    }


}
