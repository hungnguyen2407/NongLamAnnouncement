package vn.edu.hcmuaf.nonglamannouncement.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import vn.edu.hcmuaf.nonglamannouncement.R;

public class PostAnnounceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_announce);
        Toolbar toolbar = findViewById(R.id.post_announce_toolbar);
        setSupportActionBar(toolbar);

        ImageButton btnBack = findViewById(R.id.post_announce_back_btn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

}
