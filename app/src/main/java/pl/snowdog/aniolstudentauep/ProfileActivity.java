package pl.snowdog.aniolstudentauep;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import pl.snowdog.aniolstudentauep.adapter.UserExperienceAdapter;
import pl.snowdog.aniolstudentauep.dao.Dao;
import pl.snowdog.aniolstudentauep.model.User;
import pl.snowdog.aniolstudentauep.util.CircleTransform;
import pl.snowdog.aniolstudentauep.util.ListViewUtil;


public class ProfileActivity extends ActionBarActivity {

    @InjectView(R.id.avatar)
    ImageView avatar;

    @InjectView(R.id.name)
    TextView name;

    @InjectView(R.id.surname)
    TextView surname;

    @InjectView(R.id.type)
    TextView type;

    @InjectView(R.id.stars)
    RatingBar stars;

    @InjectView(R.id.tags1)
    LinearLayout tags1;

    @InjectView(R.id.tag1)
    TextView tag1;

    @InjectView(R.id.tag2)
    TextView tag2;

    @InjectView(R.id.tag3)
    TextView tag3;

    @InjectView(R.id.tag4)
    TextView tag4;

    @InjectView(R.id.tag5)
    TextView tag5;

    @InjectView(R.id.tag6)
    TextView tag6;

    @InjectView(R.id.experience_list)
    ListView experienceList;

    @InjectView(R.id.contact)
    ImageButton contact;

    @InjectView(R.id.contact_request)
    TextView contactRequest;

    @InjectView(R.id.tel)
    TextView tel;

    @InjectView(R.id.mail)
    TextView mail;


    UserExperienceAdapter mAdapter;

    private User user;
    private boolean connected = false;
    private int mNotificationId = 002;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.inject(this);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.green)));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        user = Dao.getUser(getIntent().getIntExtra("id", -1));

        Picasso.with(this).load(user.getAvatar()).transform(new CircleTransform()).into(avatar);

        name.setText(user.getName().toUpperCase());
        surname.setText(user.getSurname().toUpperCase());
        if (user.isAngel()) {
            type.setText("Anioł");
        } else {
            type.setText("Student");
        }

        stars.setRating(user.getRating());

        TextView[] tags = new TextView[]{
                tag1,
                tag2,
                tag3,
                tag4,
                tag5,
                tag6
        };
        for (int i = 0; i < 6; i++) {
            if (i < user.getSkills().size()) {
                tags[i].setVisibility(View.VISIBLE);
                tags[i].setText(user.getSkills().get(i));
            } else {
                tags[i].setVisibility(View.GONE);
                tags[i].setText("");
            }
        }

        mAdapter = new UserExperienceAdapter(this, R.layout.user_experience_item,
                user.getExperience());
        experienceList.setAdapter(mAdapter);

        experienceList.post(new Runnable() {
            @Override
            public void run() {
                ListViewUtil.setListViewHeightBasedOnChildren(experienceList);
            }
        });

        connected = Dao.isConnected(user.getId());

        contactRequest.setVisibility(View.GONE);
        showContact();
    }

    private void showContact() {
        if (connected) {
            tel.setText(user.getTel());
            mail.setText(user.getEmail());
            tel.setVisibility(View.VISIBLE);
            mail.setVisibility(View.VISIBLE);
            contactRequest.setVisibility(View.GONE);
            contact.setVisibility(View.GONE);
        } else {
            tel.setText("");
            mail.setText("");
            tel.setVisibility(View.GONE);
            mail.setVisibility(View.GONE);
            contact.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.contact)
    public void contact() {
        contactRequest.setVisibility(View.VISIBLE);
        contact.setVisibility(View.GONE);
        connected = true;
        Dao.addConnected(user.getId());

        contactRequest.postDelayed(new Runnable() {
            @Override
            public void run() {
                showContact();

                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(ProfileActivity.this)
                                .setSmallIcon(R.drawable.empty)
                                .setContentTitle(user.getName() + " " + user.getSurname())
                                .setContentText("dodał Cię do kontaktów")
                                .setAutoCancel(true);

                Intent resultIntent = new Intent(ProfileActivity.this, ProfileActivity.class);
                resultIntent.putExtra("id", user.getId());

                PendingIntent resultPendingIntent =
                        PendingIntent.getActivity(
                                ProfileActivity.this,
                                0,
                                resultIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);

                NotificationManager mNotifyMgr =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                mNotifyMgr.notify(mNotificationId, mBuilder.build());

                try {
                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                    r.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 5000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent usersIntent = new Intent(this, UsersActivity.class);
            startActivity(usersIntent);
            finish();
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
