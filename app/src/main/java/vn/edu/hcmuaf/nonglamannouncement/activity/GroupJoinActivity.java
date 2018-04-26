package vn.edu.hcmuaf.nonglamannouncement.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class GroupJoinActivity extends AppCompatActivity {

    private Activity groupJoinActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        groupJoinActivity = this;
    }
}
