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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.edu.hcmuaf.nonglamannouncement.R;
import vn.edu.hcmuaf.nonglamannouncement.activity.AnnounceDetailActivity;
import vn.edu.hcmuaf.nonglamannouncement.adapter.AnnounceAdapter;
import vn.edu.hcmuaf.nonglamannouncement.model.Announce;

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
        announceView = inflater.inflate(R.layout.announcement_layout, container, false);

//        Lay list view
        listView = announceView.findViewById(R.id.list_view);

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
        String[] tabsList = {getString(R.string.announce_tabs_all), getString(R.string.announce_tabs_important), getString(R.string.announce_tabs_recent), getString(R.string.announce_tabs_faculty), getString(R.string.announce_tabs_subject), getString(R.string.announce_tabs_group)};

        TabLayout tabLayout = announceView.findViewById(R.id.announce_tablayout);
        tabLayout.setSelectedTabIndicatorColor(mainActivity.getColor(R.color.colorSecondary));
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
            makeConnect();

            SharedPreferences sp = mainActivity.getSharedPreferences("announce_data", Context.MODE_PRIVATE);
            String data = sp.getString("post1", "Post1");
            JSONObject post = new JSONObject(data);
            Announce announce = new Announce(post.getString("title"), "admin", post.getString("content"), null);
            listAnnouces = new ArrayList<>();
            listAnnouces.add(announce);
//        Tao adapter nhan vao danh sach thong bao
            adapter = new AnnounceAdapter(mainActivity, R.layout.announce_row, listAnnouces);

//        Truyen adapter vao listview
            listView.setAdapter(adapter);

//        Set su kien khi bam vao 1 thong bao trong listview
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Announce announce = listAnnouces.get(position);
                    SharedPreferences sp = mainActivity.getSharedPreferences("announce_data", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("header", announce.getHeader());
                    editor.putString("content", announce.getContent());
                    editor.putString("date", announce.getDate());
                    editor.apply();
                    startActivity(new Intent(mainActivity, AnnounceDetailActivity.class));
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void makeConnect() {
        RequestQueue queue = Volley.newRequestQueue(mainActivity.getApplicationContext());
        String url = "http://localhost:8080/NongLamAnnounceService/webresources/post";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        SharedPreferences sp = mainActivity.getSharedPreferences("announce_data", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("post1", response);
                        editor.apply();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mainActivity.getApplicationContext(), getText(R.string.error_connection), Toast.LENGTH_LONG);
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
