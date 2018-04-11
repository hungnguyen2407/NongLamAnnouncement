package vn.edu.hcmuaf.nonglamannouncement.fragment;

import android.app.Activity;
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

import java.util.ArrayList;

import vn.edu.hcmuaf.nonglamannouncement.R;
import vn.edu.hcmuaf.nonglamannouncement.adapter.AnnounceAdapter;
import vn.edu.hcmuaf.nonglamannouncement.model.Announce;

/*
Xu ly hien thi cho trang thong bao
 */
public class AnnounceFragment extends Fragment {

    private View announceView;
    private Activity mainActivity;
    private AnnounceAdapter adapter;
    private ListView listView;
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        Lay activity
        mainActivity = getActivity();

//        Khoi tao view
        announceView = inflater.inflate(R.layout.announcement_layout, container, false);

//        Lay list view
        listView = announceView.findViewById(R.id.list_view);

//        Xu ly hien thi tab
        //TODO
        String[] tabsList = {getString(R.string.announce_tabs_all), getString(R.string.announce_tabs_important), getString(R.string.announce_tabs_recent)};
        tabLayoutHandler(tabsList);
//        Xu ly hien thi thong bao
        listViewHandler();

//        Tra view cho activity hien thi
        return announceView;
    }

    private void tabLayoutHandler(String[] tabsList) {
//        Tao tab view
        tabLayout = announceView.findViewById(R.id.announce_tablayout);
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
    private void listViewHandler() {

//        Danh sach cac thong bao can hien thi (Demo)
        ArrayList<Announce> listAnnouces = new ArrayList<>();
        Announce announce1 = new Announce("Thong bao 1", "admin", "Hello", null);
        Announce announce2 = new Announce("Thong bao 2", "admin", "Hello", null);
        Announce announce3 = new Announce("Thong bao 3", "admin", "Hello", null);
        Announce announce4 = new Announce("Thong bao 4", "admin", "Hello", null);
        listAnnouces.add(announce1);
        listAnnouces.add(announce2);
        listAnnouces.add(announce3);
        listAnnouces.add(announce4);

//        Tao adapter nhan vao danh sach thong bao
        adapter = new AnnounceAdapter(mainActivity, R.layout.announce_row, listAnnouces);

//        Truyen adapter vao listview
        listView.setAdapter(adapter);

//        Set su kien khi bam vao 1 thong bao trong listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }


}
