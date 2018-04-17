package vn.edu.hcmuaf.nonglamannouncement.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import vn.edu.hcmuaf.nonglamannouncement.R;
import vn.edu.hcmuaf.nonglamannouncement.fragment.AnnounceFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private SharedPreferences pre;
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

        //Tao fragment va hien thi trang thong bao
        TextView tvHeader = findViewById(R.id.toolbar_header);
        tvHeader.setText(R.string.nav_home);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contentFrame, new AnnounceFragment()).commit();

        //Luu du lieu xuong file data_login.xml khi dang nhap thanh cong
        pre = this.getSharedPreferences("data_login", Context.MODE_PRIVATE);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            SharedPreferences.Editor edit=pre.edit();
            edit.clear();
            edit.putString("email",bundle.getString("id"));
            edit.commit();
        }
        //kiem tra dang nhap neu chua dang nhap quay ve man hinh login
        checkLogin();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            SharedPreferences.Editor edit=pre.edit();
            edit.remove("email");
            edit.commit();
            checkLogin();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void checkLogin(){
        pre = this.getSharedPreferences("data_login", Context.MODE_PRIVATE);
        if(pre.getString("email","").equals("")){
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }
}
