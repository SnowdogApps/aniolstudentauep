package pl.snowdog.aniolstudentauep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import pl.snowdog.aniolstudentauep.dialog.LoginDialogFragment;
import pl.snowdog.aniolstudentauep.util.RipplesHelper;


public class StartAppActivity extends ActionBarActivity {
    public static final String ARG_SELECTED_USER_TYPE = "arg_selected_user_type";

    @InjectView(R.id.btn_contacts)
    Button btnContacts;

    @InjectView(R.id.btn_match)
    Button btnMatch;

    @InjectView(R.id.btn_list)
    Button btnList;

    @OnClick(R.id.btn_match)
    public void onMatchClicked() {
        Intent intent = new Intent(this, UsersActivity.class);
        intent.putExtra(UsersActivity.ARG_MODE_MATCH, true);
        startActivity(intent);
    }

    @OnClick(R.id.btn_list)
    public void onListClicked() {
        Intent intent = new Intent(this, UsersActivity.class);
        intent.putExtra(UsersActivity.ARG_MODE_MATCH, false);
        //intent.putExtra(StudentOrAngelActivity.ARG_SELECTED_USER_TYPE, mUserType);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_start_app);
        getSupportActionBar().hide();
        ButterKnife.inject(this);

        RipplesHelper.colorRippleButton(btnContacts, getResources().getColor(R.color.green),
                getResources().getColor(R.color.green_dark));
        RipplesHelper.colorRippleButton(btnList, getResources().getColor(R.color.green),
                getResources().getColor(R.color.green_dark));
        RipplesHelper.colorRippleButton(btnMatch, getResources().getColor(R.color.green),
                getResources().getColor(R.color.green_dark));
    }

    private void startViewPagerActivity(int userType) {
        if (getSupportFragmentManager().findFragmentByTag(LoginDialogFragment.TAG)
                == null) {
            Bundle args = new Bundle();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            LoginDialogFragment dp = new LoginDialogFragment();
            args.putInt(ARG_SELECTED_USER_TYPE, userType);
            dp.setArguments(args);
            dp.show(ft, LoginDialogFragment.TAG);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_or_angel, menu);
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

}
