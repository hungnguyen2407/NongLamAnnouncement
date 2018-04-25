package vn.edu.hcmuaf.nonglamannouncement.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.edu.hcmuaf.nonglamannouncement.R;
import vn.edu.hcmuaf.nonglamannouncement.activity.AnnounceDetailActivity;
import vn.edu.hcmuaf.nonglamannouncement.adapter.AnnounceAdapter;
import vn.edu.hcmuaf.nonglamannouncement.dao.CustomConnection;
import vn.edu.hcmuaf.nonglamannouncement.model.Announce;
import vn.edu.hcmuaf.nonglamannouncement.model.JSONTags;
import vn.edu.hcmuaf.nonglamannouncement.model.MemoryName;
import vn.edu.hcmuaf.nonglamannouncement.model.NameOfResources;

/*
Xu ly hien thi cho trang thong bao
 */
public class AnnounceFragment extends Fragment {

    private View announceView;
    private Activity mainActivity;
    private ListView listView;
    private AnnounceAdapter adapter;
    private ArrayList<Announce> listAnnouces;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        Lay activity
        mainActivity = getActivity();

//        Khoi tao view
        announceView = inflater.inflate(R.layout.fragment_announcement, container, false);

//        Lay list view
        listView = announceView.findViewById(R.id.announce_list_view);

//        Xu ly hien thi tab
        tabLayoutHandler();
//        Xu ly hien thi thong bao
        announceListHandler();

//        Tra view cho activity hien thi
        return announceView;
    }

    @SuppressLint("NewApi")
    private void tabLayoutHandler() {
//        Tao tab view
        String[] tabsList = {getString(R.string.announce_tabs_all), getString(R.string.announce_tabs_faculty), getString(R.string.announce_tabs_group)};
//        String[] tabsList = {getString(R.string.announce_tabs_all)};
        TabLayout tabLayout = announceView.findViewById(R.id.announce_tablayout);
        tabLayout.setSelectedTabIndicatorColor(mainActivity.getColor(R.color.colorSecondary));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Toast.makeText(mainActivity.getApplicationContext(), getString(R.string.announce_tabs_all), Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(mainActivity.getApplicationContext(), getString(R.string.announce_tabs_faculty), Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(mainActivity.getApplicationContext(), getString(R.string.announce_tabs_group), Toast.LENGTH_SHORT).show();
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//        Dong lap gan cac tab trong tablist vao tablayout
        for (int i = 0; i < tabsList.length; i++) {
            TextView tabCustom = (TextView) LayoutInflater.from(mainActivity).inflate(R.layout.custom_tab, null);
            tabCustom.setText(tabsList[i]);
            tabLayout.addTab(tabLayout.newTab().setCustomView(tabCustom));
        }
    }

    /*
    Gan danh sach cac item vao list view
     */
    private void announceListHandler() {
        try {
            listAnnouces = new ArrayList<>();
            CustomConnection.makeGETConnection(mainActivity, CustomConnection.URLPostfix.ANNOUNCE_ALL, NameOfResources.ANNOUNCE_DATA.toString());
            SharedPreferences sp = mainActivity.getSharedPreferences(MemoryName.TEMP_DATA.toString(), Context.MODE_PRIVATE);
            String data = sp.getString(NameOfResources.ANNOUNCE_DATA.toString(), "");
            JSONArray jsonArray = new JSONObject(data).getJSONArray(JSONTags.ANNOUNCE.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                listAnnouces.add(new Announce(jsonArray.getJSONObject(i)));
            }
//        Tao adapter nhan vao danh sach thong bao
            adapter = new AnnounceAdapter(mainActivity, R.layout.announce_row, listAnnouces);

//        Truyen adapter vao listview
            listView.setAdapter(adapter);

//        Set su kien khi bam vao 1 thong bao trong listview
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Announce announce = listAnnouces.get(position);
                    SharedPreferences sp = mainActivity.getSharedPreferences(MemoryName.TEMP_DATA.toString(), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(NameOfResources.ANNOUNCE_HEADER.toString(), announce.getHeader());
                    editor.putString(NameOfResources.ANNOUNCE_CONTENT.toString(), announce.getContent());
                    editor.putString(NameOfResources.ANNOUNCE_DATE.toString(), announce.getDate());
                    editor.apply();
                    startActivity(new Intent(mainActivity, AnnounceDetailActivity.class));
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
