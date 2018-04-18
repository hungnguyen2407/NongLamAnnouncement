package vn.edu.hcmuaf.nonglamannouncement.activity;

import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import vn.edu.hcmuaf.nonglamannouncement.R;
import vn.edu.hcmuaf.nonglamannouncement.dao.CustomConnection;
import vn.edu.hcmuaf.nonglamannouncement.fragment.AnnounceFragment;
import vn.edu.hcmuaf.nonglamannouncement.model.MemoryName;
import vn.edu.hcmuaf.nonglamannouncement.model.NameOfResult;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private boolean loginSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences sp = getSharedPreferences(MemoryName.LOGIN_DATA.toString(), Context.MODE_PRIVATE);
        loginSuccess = sp.getBoolean("loginSuccess", true);

        if (loginSuccess) {
//            String id = sp.getString("id", "");
            String id = "14130047";
            CustomConnection.makeGETConnectionWithParameter(this, CustomConnection.URLPostfix.FIND_NAME_BY_ID, NameOfResult.USER_NAME.toString(), "id", id);

            //Tao fragment va hien thi trang thong bao
            TextView tvHeader = findViewById(R.id.toolbar_header);
            tvHeader.setText(R.string.nav_home);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.contentFrame, new AnnounceFragment()).commit();


        } else {
            //kiem tra dang nhap neu chua dang nhap quay ve man hinh login
            login();
        }
    }


    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
        Xử lý nav
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_group) {

        } else if (id == R.id.nav_register) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_help) {

        } else if (id == R.id.nav_logout) {
            // xoa du lieu o file data_login sau khi da dang xuat
            getSharedPreferences(MemoryName.LOGIN_DATA.toString(), Context.MODE_PRIVATE).edit().clear();
            login();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void login() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);

    }

    @Override
    public void onCreateNavigateUpTaskStack(TaskStackBuilder builder) {
        super.onCreateNavigateUpTaskStack(builder);
        String userName = getSharedPreferences(MemoryName.TEMP_DATA.toString(), Context.MODE_PRIVATE).getString(NameOfResult.USER_NAME.toString(), "");
        TextView tvUserName = findViewById(R.id.nav_user_tv_name);
        tvUserName.setText(userName);
    }
}
