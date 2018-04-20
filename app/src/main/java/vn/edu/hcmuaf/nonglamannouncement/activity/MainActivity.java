package vn.edu.hcmuaf.nonglamannouncement.activity;

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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import vn.edu.hcmuaf.nonglamannouncement.R;
import vn.edu.hcmuaf.nonglamannouncement.dao.CustomConnection;
import vn.edu.hcmuaf.nonglamannouncement.fragment.AnnounceFragment;
import vn.edu.hcmuaf.nonglamannouncement.fragment.GroupFragment;
import vn.edu.hcmuaf.nonglamannouncement.fragment.HelpFragment;
import vn.edu.hcmuaf.nonglamannouncement.fragment.SettingFragment;
import vn.edu.hcmuaf.nonglamannouncement.model.MemoryName;
import vn.edu.hcmuaf.nonglamannouncement.model.NameOfResult;
import vn.edu.hcmuaf.nonglamannouncement.model.ObjectTypes;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private boolean loginSuccess;
    private SharedPreferences sp;
    private NavigationView navigationView;
    private FragmentManager fragmentManager;
    private TextView tvHeader;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.main_toolbar);
        toolbar.inflateMenu(R.menu.main);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//        FirebaseFirestore db = FirebaseFirestore.getInstance();



        sp = getSharedPreferences(MemoryName.TEMP_DATA.toString(), Context.MODE_PRIVATE);
//        db.collection(MemoryName.TEMP_DATA.toString())
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (DocumentSnapshot document : task.getResult()) {
//                                Log.d("respond", document.getId() + " => " + document.getData());
//                            }
//                        } else {
//                            Log.w("respond", "Error getting documents.", task.getException());
//                        }
//                    }
//                });


        loginSuccess = Boolean.valueOf(sp.getString(NameOfResult.LOGIN_SUCCESS.toString(), "false"));

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

        CustomConnection.makeGETConnectionWithParameter(this, CustomConnection.URLPostfix.GROUP_LIST, NameOfResult.GROUP_LIST.toString(), sp.getString(NameOfResult.USER_ID.toString(), ""));
    }

    private void navHeaderHandler() {
        String id = sp.getString(NameOfResult.USER_ID.toString(), "14130047");
        CustomConnection.makeGETConnectionWithParameter(this, CustomConnection.URLPostfix.FIND_NAME_BY_ID, NameOfResult.USER_NAME.toString(), ObjectTypes.USER.toString(), id);
        String userName = getSharedPreferences(MemoryName.TEMP_DATA.toString(), Context.MODE_PRIVATE).getString(NameOfResult.USER_NAME.toString(), "");
        TextView tvUserName = navigationView.getHeaderView(0).findViewById(R.id.nav_user_tv_name);
        tvUserName.setText(userName);

        TextView tvUserEmail = navigationView.getHeaderView(0).findViewById(R.id.nav_user_tv_email);
        tvUserEmail.setText(id + "@st.hcmuaf.edu.vn");
        String className = sp.getString(NameOfResult.USER_CLASS_ID.toString(), "DH14DTA");
        CustomConnection.makeGETConnectionWithParameter(this, CustomConnection.URLPostfix.FIND_NAME_BY_ID, NameOfResult.USER_CLASS_NAME.toString(), ObjectTypes.GROUP.toString(), className);
        String userClass = getSharedPreferences(MemoryName.TEMP_DATA.toString(), Context.MODE_PRIVATE).getString(NameOfResult.USER_CLASS_NAME.toString(), "");
        TextView tvUserClass = navigationView.getHeaderView(0).findViewById(R.id.nav_user_tv_class);
        tvUserClass.setText(userClass);

        String faculty = sp.getString(NameOfResult.USER_FACULTY_ID.toString(), "CNTT");
        CustomConnection.makeGETConnectionWithParameter(this, CustomConnection.URLPostfix.FIND_NAME_BY_ID, NameOfResult.USER_FACULTY_NAME.toString(), ObjectTypes.FACULTY.toString(), faculty);
        String userFaculty = getSharedPreferences(MemoryName.TEMP_DATA.toString(), Context.MODE_PRIVATE).getString(NameOfResult.USER_FACULTY_NAME.toString(), "");
        TextView tvUserFaculty = navigationView.getHeaderView(0).findViewById(R.id.nav_user_tv_faculty);
        tvUserFaculty.setText(userFaculty);
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
//        if (id == R.id.action_settings) {
//            return true;
//        }

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
            // xoa du lieu o file data_login sau khi da dang xuat
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(NameOfResult.LOGIN_SUCCESS.toString(), "false");
            editor.commit();
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

}
