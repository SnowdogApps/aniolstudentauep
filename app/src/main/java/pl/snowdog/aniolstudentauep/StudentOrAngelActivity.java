package pl.snowdog.aniolstudentauep;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.snowdog.aniolstudentauep.dialog.LoginDialogFragment;
import pl.snowdog.aniolstudentauep.util.RipplesHelper;


public class StudentOrAngelActivity extends ActionBarActivity {
    public static final String ARG_SELECTED_USER_TYPE = "arg_selected_user_type";
    public static final int STUDENT = 0;
    public static final int ANGEL = 1;

    @InjectView(R.id.btn_angel)
    Button btnAngel;

    @InjectView(R.id.btn_student)
    Button btnStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_student_or_angel);
        ButterKnife.inject(this);
        getSupportActionBar().hide();

        RipplesHelper.colorRippleButton(btnAngel, getResources().getColor(R.color.green),
                getResources().getColor(R.color.green_dark));
        RipplesHelper.colorRippleButton(btnStudent, getResources().getColor(R.color.green),
                getResources().getColor(R.color.green_dark));

        btnAngel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startViewPagerActivity(ANGEL);
            }
        });

        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startViewPagerActivity(STUDENT);
            }
        });
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
