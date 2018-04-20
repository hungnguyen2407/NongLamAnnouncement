package vn.edu.hcmuaf.nonglamannouncement.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.edu.hcmuaf.nonglamannouncement.R;
import vn.edu.hcmuaf.nonglamannouncement.adapter.GroupAdapter;
import vn.edu.hcmuaf.nonglamannouncement.model.Group;
import vn.edu.hcmuaf.nonglamannouncement.model.MemoryName;
import vn.edu.hcmuaf.nonglamannouncement.model.NameOfResult;
import vn.edu.hcmuaf.nonglamannouncement.model.ObjectTypes;

public class GroupFragment extends Fragment {
    private Activity mainActivity;
    private View groupView;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainActivity = getActivity();
        groupView = inflater.inflate(R.layout.group_layout, container, false);
        listView = groupView.findViewById(R.id.group_list_view);
        listGroupHandler();
        return groupView;
    }

    private void listGroupHandler() {
        try {
            ArrayList<Group> listGroups = new ArrayList<>();
            SharedPreferences sp = mainActivity.getSharedPreferences(MemoryName.TEMP_DATA.toString(), Context.MODE_PRIVATE);

            JSONArray jsonArray = new JSONObject(sp.getString(NameOfResult.GROUP_LIST.toString(), "")).getJSONArray(ObjectTypes.GROUP_LIST.toString());
            JSONObject jsonObject = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                listGroups.add(new Group(jsonObject.getString(ObjectTypes.GROUP.toString()), jsonObject.getString(ObjectTypes.GROUP_NAME.toString()), jsonObject.getString(ObjectTypes.GROUP_FACULTY.toString()), jsonObject.getInt(ObjectTypes.GROUP_MEM.toString())));
            }
//            listGroups.add(new Group(sp.getString(NameOfResult.USER_CLASS_ID.toString(), ""), sp.getString(NameOfResult.USER_CLASS_NAME.toString(), ""), sp.getString(NameOfResult.USER_FACULTY_ID.toString(), ""), 0));
            groupView.findViewById(R.id.group_setting_btn);
            listView.setAdapter(new GroupAdapter(mainActivity, mainActivity, R.layout.group_row, listGroups));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
