package pl.snowdog.aniolstudentauep;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pl.snowdog.aniolstudentauep.fragment.ProfileFillDataFragment;
import pl.snowdog.aniolstudentauep.fragment.ProfileFillExperienceFragment;
import pl.snowdog.aniolstudentauep.fragment.ProfileFillSkillsFragment;
import pl.snowdog.aniolstudentauep.model.User;
import pl.snowdog.aniolstudentauep.model.UserExperience;


public class CreateProfileActivity extends ActionBarActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    private boolean isAngel = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.green)));

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        int angel = getIntent().getIntExtra(StudentOrAngelActivity.ARG_SELECTED_USER_TYPE, 0);
        if (angel == StudentOrAngelActivity.ANGEL) {
            isAngel = true;
        }
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        setTitle("Krok 1/3");
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        setTitle("Krok 1/3");
                        break;

                    case 1:
                        setTitle("Krok 2/3");
                        break;

                    case 2:
                        setTitle("Krok 3/3");
                        break;


                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void setViewPagerItem(int newPosition) {
        mViewPager.setCurrentItem(newPosition, true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public User getUserFromFragments() {
        List<UserExperience> experience2 = new ArrayList<UserExperience>();
        List<String> skills2 = new ArrayList<String>();

        String name = ((ProfileFillDataFragment) mSectionsPagerAdapter.getActiveFragment(mViewPager, 0)).getName();
        String surname = ((ProfileFillDataFragment) mSectionsPagerAdapter.getActiveFragment(mViewPager, 0)).getSurname();
        int age = ((ProfileFillDataFragment) mSectionsPagerAdapter.getActiveFragment(mViewPager, 0)).getAge();

        experience2 = ((ProfileFillExperienceFragment) mSectionsPagerAdapter.getActiveFragment(mViewPager, 1)).getExperienceList();

        skills2 = ((ProfileFillSkillsFragment) mSectionsPagerAdapter.getActiveFragment(mViewPager, 2)).getSelectedSkillsList();
        User user2 = new User(2, isAngel, name, surname,
                "http://1.fwcdn.pl/p/13/19/1951319/359629.1.jpg",
                age, 2008, "BZ WBK", experience2, skills2);


        return user2;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_create_profile, container, false);

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getActiveFragment(ViewPager container, int position) {
            String name = makeFragmentName(container.getId(), position);
            return getSupportFragmentManager().findFragmentByTag(name);
        }

        private String makeFragmentName(int viewId, int index) {
            return "android:switcher:" + viewId + ":" + index;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:

                    return ProfileFillDataFragment.newInstance();

                case 1:

                    return ProfileFillExperienceFragment.newInstance();

                case 2:

                    return ProfileFillSkillsFragment.newInstance();

                default:
                    PlaceholderFragment.newInstance(position + 1);
                    break;
            }
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

    }

}
