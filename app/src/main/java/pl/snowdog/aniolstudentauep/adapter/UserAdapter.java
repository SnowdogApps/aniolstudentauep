package pl.snowdog.aniolstudentauep.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.snowdog.aniolstudentauep.R;
import pl.snowdog.aniolstudentauep.model.User;
import pl.snowdog.aniolstudentauep.util.CircleTransform;

/**
 * Created by Bartek on 2015-02-26.
 */
public class UserAdapter extends ArrayAdapter<User> {


    public UserAdapter(Context context, int resource, int textViewResourceId, List<User> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        User user = getItem(position);
        ViewHolder vHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_item, null);
            vHolder = new ViewHolder(convertView);
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();

        }


        Picasso.with(getContext()).load(user.getAvatar()).transform(new CircleTransform()).into(vHolder.avatar);

        vHolder.name.setText(user.getName());
        vHolder.surname.setText(user.getSurname());
        if (user.isAngel()) {
            vHolder.type.setText("Anioł");
        } else {
            vHolder.type.setText("Student");
        }

        vHolder.stars.setRating(user.getRating());

        TextView[] tags = new TextView[]{
                vHolder.tag1,
                vHolder.tag2,
                vHolder.tag3,
                vHolder.tag4
        };

        for (int i = 0; i < 4; i++) {
            if (i < user.getSkills().size()) {
                tags[i].setVisibility(View.VISIBLE);
                tags[i].setText(user.getSkills().get(i));
            } else {
                tags[i].setVisibility(View.GONE);
                tags[i].setText("");
            }
        }

        return convertView;
    }

    static class ViewHolder {

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

        @InjectView(R.id.tag1)
        TextView tag1;

        @InjectView(R.id.tag2)
        TextView tag2;

        @InjectView(R.id.tag3)
        TextView tag3;

        @InjectView(R.id.tag4)
        TextView tag4;


        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
