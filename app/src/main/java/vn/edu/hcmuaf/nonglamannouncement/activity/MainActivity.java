package vn.edu.hcmuaf.nonglamannouncement.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import vn.edu.hcmuaf.nonglamannouncement.R;
import vn.edu.hcmuaf.nonglamannouncement.dao.CustomConnection;
import vn.edu.hcmuaf.nonglamannouncement.fragment.AnnounceFragment;
import vn.edu.hcmuaf.nonglamannouncement.fragment.GroupFragment;
import vn.edu.hcmuaf.nonglamannouncement.fragment.HelpFragment;
import vn.edu.hcmuaf.nonglamannouncement.fragment.SettingFragment;
import vn.edu.hcmuaf.nonglamannouncement.model.JSONTags;
import vn.edu.hcmuaf.nonglamannouncement.model.MemoryName;
import vn.edu.hcmuaf.nonglamannouncement.model.NameOfResources;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private boolean loginSuccess;
    private SharedPreferences sp;
    private NavigationView navigationView;
    private FragmentManager fragmentManager;
    private TextView tvHeader;
    private Toolbar toolbar;
    private Activity mainActivity;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = this;
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        sp = getSharedPreferences(MemoryName.TEMP_DATA.toString(), Context.MODE_PRIVATE);


        loginSuccess = Boolean.valueOf(sp.getString(NameOfResources.LOGIN_SUCCESS.toString(), "false"));

        if (loginSuccess) {
            navHeaderHandler();
            initHandle();
            //Tao fragment va hien thi trang thong bao
            tvHeader = findViewById(R.id.toolbar_header);
            tvHeader.setText(R.string.nav_home);
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.contentFrame, new AnnounceFragment()).commit();


        } else {
            //kiem tra dang nhap neu chua dang nhap quay ve man hinh login
            login();
        }
    }

    private void initHandle() {
        userID = sp.getString(NameOfResources.USER_ID.toString(), "");
        if (TextUtils.isEmpty(userID))
            userID = sp.getString(NameOfResources.USER_ID.toString(), getIntent().getStringExtra(NameOfResources.USER_ID.toString()));

        new GetUserInfoTask().execute();
        CustomConnection.makeGETConnectionWithParameter(this, CustomConnection.URLPostfix.GROUP_LIST, NameOfResources.GROUP_LIST.toString(), sp.getString(NameOfResources.USER_ID.toString(), ""));
    }

    private void navHeaderHandler() {
        try {
            JSONObject userInfoJSON = new JSONObject(sp.getString(NameOfResources.USER_INFO.toString(), ""));
            TextView tvUserName = navigationView.findViewById(R.id.nav_user_tv_name);
            tvUserName.setText(userInfoJSON.getString(JSONTags.USER_LNAME.toString()) + " " + userInfoJSON.getString(JSONTags.USER_FNAME.toString()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.menu_action_group_join:
                Toast.makeText(getApplicationContext(), "Join", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_action_post_announce:
                startActivity(new Intent(this, PostAnnounceActivity.class));
                return true;
            case R.id.menu_action_search:
                Toast.makeText(getApplicationContext(), "Search", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /*
        Xử lý nav
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_home) {
            tvHeader.setText(R.string.nav_home);
            fragmentManager.beginTransaction().replace(R.id.contentFrame, new AnnounceFragment()).commit();
        } else if (id == R.id.nav_group) {
            tvHeader.setText(R.string.nav_group);
            fragmentManager.beginTransaction().replace(R.id.contentFrame, new GroupFragment()).commit();
        } else if (id == R.id.nav_setting) {
            tvHeader.setText(R.string.nav_setting);
            fragmentManager.beginTransaction().replace(R.id.contentFrame, new SettingFragment()).commit();
        } else if (id == R.id.nav_help) {
            tvHeader.setText(R.string.nav_help);
            fragmentManager.beginTransaction().replace(R.id.contentFrame, new HelpFragment()).commit();
        } else if (id == R.id.nav_logout) {
            AlertDialog logoutDialog = new AlertDialog.Builder(this)
                    .setTitle("Xác nhận đăng xuất")
                    .setMessage("Bạn chắc chắn muốn đăng xuất ? ")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("Có", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            // xoa du lieu o file data_login sau khi da dang xuat
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString(NameOfResources.LOGIN_SUCCESS.toString(), "false");
                            editor.putString(NameOfResources.USER_ID.toString(), "");
                            editor.commit();
                            login();
                        }
                    })
                    .setNegativeButton("Không", null).show();
            logoutDialog.getButton(AlertDialog.BUTTON_POSITIVE).setBackgroundColor(Color.GRAY);
            logoutDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void login() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);

    }

    private class GetUserInfoTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {

            CustomConnection.makeGETConnectionWithParameter(mainActivity,
                    CustomConnection.URLPostfix.USER_INFO,
                    NameOfResources.USER_INFO.toString(),
                    userID);
            return null;
        }
    }
}
