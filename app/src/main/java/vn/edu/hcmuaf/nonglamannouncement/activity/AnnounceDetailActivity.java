package vn.edu.hcmuaf.nonglamannouncement.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import vn.edu.hcmuaf.nonglamannouncement.R;
import vn.edu.hcmuaf.nonglamannouncement.model.AnnounceData;
import vn.edu.hcmuaf.nonglamannouncement.model.MemoryName;

public class AnnounceDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announce_detail);
        Toolbar toolbar = findViewById(R.id.announce_detail_toolbar);
        setSupportActionBar(toolbar);
        ImageButton btnBack = findViewById(R.id.announce_detail_back_btn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        SharedPreferences sp = getSharedPreferences(MemoryName.TEMP_DATA.toString(), Context.MODE_PRIVATE);

        TextView tvHeader = findViewById(R.id.announce_detail_header);
        tvHeader.setText(sp.getString(AnnounceData.HEADER.toString(), "Header"));
        TextView tvContent = findViewById(R.id.announce_detail_content);
        tvContent.setText(sp.getString(AnnounceData.CONTENT.toString(), "Content"));
        TextView tvDate = findViewById(R.id.announce_detail_date);
        tvDate.setText(sp.getString(AnnounceData.DATE.toString(), "Date"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }
}
