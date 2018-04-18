package vn.edu.hcmuaf.nonglamannouncement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import vn.edu.hcmuaf.nonglamannouncement.R;
import vn.edu.hcmuaf.nonglamannouncement.model.Group;

public class GroupAdapter extends BaseAdapter {
    private List<Group> listGroups;
    private LayoutInflater inflater;

    public GroupAdapter(Context context, int resource, List<Group> listGroups) {
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
        TextView tvMemNum = view.findViewById(R.id.group_tv_mem_num);
        Group group = listGroups.get(position);
        tvGroupName.setText(group.getGroupName() + "");
        tvMemNum.setText(group.getMemNum() + "");
        return view;
    }
}
