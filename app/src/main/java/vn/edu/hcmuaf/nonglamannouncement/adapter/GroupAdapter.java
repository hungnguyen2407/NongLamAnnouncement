package vn.edu.hcmuaf.nonglamannouncement.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vn.edu.hcmuaf.nonglamannouncement.R;
import vn.edu.hcmuaf.nonglamannouncement.model.Group;
import vn.edu.hcmuaf.nonglamannouncement.model.MemoryName;

public class GroupAdapter extends BaseAdapter {
    private List<Group> listGroups;
    private LayoutInflater inflater;
    private Activity activity;

    public GroupAdapter(Activity activity, Context context, int resource, List<Group> listGroups) {
        this.activity = activity;
        this.listGroups = listGroups;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listGroups.size();
    }

    @Override
    public Group getItem(int position) {
        return listGroups.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null)
            view = inflater.inflate(R.layout.group_row, null);
        TextView tvGroupName = view.findViewById(R.id.group_tv_name);
        final ImageButton settingBtn = view.findViewById(R.id.group_setting_btn);
        final Group group = listGroups.get(position);
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = activity.getSharedPreferences(MemoryName.TEMP_DATA.toString(), Context.MODE_PRIVATE);
                Toast.makeText(activity.getApplicationContext(), "Click", Toast.LENGTH_LONG).show();
            }
        });
        tvGroupName.setText(group.getGroupName());
        return view;
    }
}
