package pl.snowdog.aniolstudentauep.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.snowdog.aniolstudentauep.R;
import pl.snowdog.aniolstudentauep.model.UserExperience;


/**
 * Created by chomi3 on 2014-09-02.
 */
public class UserExperienceAdapter extends ArrayAdapter<UserExperience> {
    private static final String TAG = "UserExperienceAdapter";

    int resource;
    private Context mContext;
    private LayoutInflater mInflater;
    private List<UserExperience> mItems;


    public UserExperienceAdapter(Context _context, int _textViewResourceId, List<UserExperience> _items) {
        super(_context, _textViewResourceId, _items);
        resource = _textViewResourceId;
        mContext = _context;
        mItems = _items;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vHolder;
        final UserExperience item = getItem(position);


        //Normal Adapter behaviour
        if (convertView == null) {
            convertView = mInflater.inflate(resource, null);
            vHolder = new ViewHolder(convertView);
            convertView.setTag(vHolder);

        } else {
            vHolder = (ViewHolder) convertView.getTag();

        }

        vHolder.tvName.setText(item.getName());
        vHolder.tvDate.setText(item.getDate());

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_name)
        TextView tvName;

        @InjectView(R.id.tv_date)
        TextView tvDate;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}
