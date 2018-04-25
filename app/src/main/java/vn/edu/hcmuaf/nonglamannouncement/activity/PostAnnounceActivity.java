package vn.edu.hcmuaf.nonglamannouncement.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import vn.edu.hcmuaf.nonglamannouncement.R;
import vn.edu.hcmuaf.nonglamannouncement.dao.CustomConnection;
import vn.edu.hcmuaf.nonglamannouncement.model.Group;
import vn.edu.hcmuaf.nonglamannouncement.model.JSONTags;
import vn.edu.hcmuaf.nonglamannouncement.model.MemoryName;
import vn.edu.hcmuaf.nonglamannouncement.model.NameOfResources;

public class PostAnnounceActivity extends AppCompatActivity {

    private Activity postAnnounceActivity;
    private EditText editTextHeader, editTextContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_announce);
        Toolbar toolbar = findViewById(R.id.post_announce_toolbar);
        setSupportActionBar(toolbar);
        postAnnounceActivity = this;
        ImageButton btnBack = findViewById(R.id.post_announce_back_btn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        postAnnounceHandler();
    }


    private void postAnnounceHandler() {
        editTextHeader = findViewById(R.id.post_announce_edit_text_header);
        editTextContent = findViewById(R.id.post_announce_edit_text_content);
        final Spinner spinner = findViewById(R.id.post_announce_spinner_group_id);
        List<String> listGroup = new ArrayList<>();
        SharedPreferences sp = getSharedPreferences(MemoryName.TEMP_DATA.toString(), Context.MODE_PRIVATE);

        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONObject(sp.getString(NameOfResources.GROUP_LIST.toString(), "")).getJSONArray(JSONTags.GROUP_LIST.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = null;
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                jsonObject = jsonArray.getJSONObject(i);
                listGroup.add(new Group(jsonObject).getGroupName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listGroup);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        Button btnSubmit = findViewById(R.id.post_announce_btn_submit);
        Button btnCancel = findViewById(R.id.post_announce_btn_cancel);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PostAnnounceTask().equals(null);
            }
        });
    }

    private class PostAnnounceTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            SharedPreferences sp = getSharedPreferences(MemoryName.TEMP_DATA.toString(), Context.MODE_PRIVATE);
            CustomConnection.makePOSTConnectionWithParameter(postAnnounceActivity,
                    CustomConnection.URLPostfix.POST_ANNOUNCE,
                    NameOfResources.POST_ANNOUNCE_MESSAGE.toString(),
                    sp.getString(NameOfResources.USER_ID.toString(), ""),
                    editTextHeader.getText().toString(), editTextContent.getText().toString());
            return null;
        }
    }
}
