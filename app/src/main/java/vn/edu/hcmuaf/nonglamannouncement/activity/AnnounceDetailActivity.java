package vn.edu.hcmuaf.nonglamannouncement.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import vn.edu.hcmuaf.nonglamannouncement.R;

public class AnnounceDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announce_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        ImageButton btnBack = findViewById(R.id.announce_detail_back_btn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        SharedPreferences sp = getSharedPreferences("announce_data", Context.MODE_PRIVATE);

        TextView tvHeader = findViewById(R.id.announce_detail_header);
        tvHeader.setText(sp.getString("header", "Header"));
        TextView tvContent = findViewById(R.id.announce_detail_content);
        tvContent.setText(sp.getString("content", "Content"));
        TextView tvDate = findViewById(R.id.announce_detail_date);
        tvDate.setText(sp.getString("date", "Date"));
    }

}
