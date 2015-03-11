package pl.snowdog.aniolstudentauep;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import pl.snowdog.aniolstudentauep.adapter.UserAdapter;
import pl.snowdog.aniolstudentauep.dao.Dao;
import pl.snowdog.aniolstudentauep.fragment.FilterFragment;
import pl.snowdog.aniolstudentauep.model.User;
import pl.snowdog.aniolstudentauep.util.PrefsUtil;

public class UsersActivity extends ActionBarActivity implements FilterFragment.OnFragmentInteractionListener {
    public static final String ARG_MODE_MATCH = "mode_match";

    @InjectView(R.id.gridview)
    GridView gridView;

    User mLoggedInUser;
    List<User> users;
    UserAdapter adapter;
    private boolean angel = true;
    private boolean student = true;
    private boolean common = false;
    private ArrayList<String> skills = null;
    private String name = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        ButterKnife.inject(this);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.green)));

        if (getIntent().getBooleanExtra(ARG_MODE_MATCH, false)) {
            User user = Dao.getUser(PrefsUtil.getLoggedInUserId(this));
            onFragmentInteraction(!user.isAngel(), user.isAngel(), true, (ArrayList<String>) user.getSkills(), null);
        } else {

            users = Dao.getUsers();
            adapter = new UserAdapter(this, R.layout.user_item, R.id.name, users);
            gridView.setAdapter(adapter);
        }
    }

    @OnItemClick(R.id.gridview)
    void onItemClick(int position) {
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        profileIntent.putExtra("id", users.get(position).getId());
        startActivity(profileIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_users, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {
            User user = Dao.getUser(PrefsUtil.getLoggedInUserId(this));
            FilterFragment filterFragment = FilterFragment.newInstance(angel, student, common,
                    (ArrayList<String>) user.getSkills(), skills, null);

            filterFragment.show(getFragmentManager(), "filter");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(boolean angel, boolean student, boolean common, ArrayList<String> skills, String name) {
        this.angel = angel;
        this.student = student;
        this.common = common;
        this.skills = skills;
        this.name = name;
        users = Dao.getUsers(angel, student, skills, name);
        adapter = new UserAdapter(this, R.layout.user_item, R.id.name, users);
        gridView.setAdapter(adapter);
    }
}
