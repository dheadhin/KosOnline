package id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.activity.DetailActivity;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.activity.InputActivity;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.activity.LoginActivity;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.adapter.kosAdapter;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.core.CoreApplication;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.helper.kosRealmHelper;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.helper.pemilikRealmHelper;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.model.kosModel;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.model.pemilikModel;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navView)
    NavigationView navView;
    TextView txtUserName, txtEmail;
    Realm realm;
    private ArrayList<kosModel> kosList;
    private kosRealmHelper kosHelper;
    private pemilikRealmHelper pemilikHelper;
    private kosAdapter mAdapter;
    //firebase
    private DatabaseReference mFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        realm = Realm.getDefaultInstance();
        kosHelper = new kosRealmHelper(MainActivity.this, realm);
        pemilikHelper = new pemilikRealmHelper(MainActivity.this, realm);

        kosList = kosHelper.getListKos();
        String mineId = CoreApplication.getInstance().getSession().getActiveInformation();

        if (!mineId.equals("")) {
            //menampilkan di header
            Log.d("id", mineId);
            View header = navView.getHeaderView(0);
            txtUserName = (TextView) header.findViewById(R.id.displayName);
            txtEmail = (TextView) header.findViewById(R.id.username);
            txtUserName.setText(pemilikHelper.getPemilik(mineId).getPemilikNama());
            txtEmail.setText(pemilikHelper.getPemilik(mineId).getPemilikEmail());
        }

        /**
         * Setup Drawer Toggle of the Toolbar
         */
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //menutup drawer saat item dipilih
                mDrawerLayout.closeDrawers();

                drawerAction(menuItem.getItemId());

                return true;
            }

        });

        menuDrawer(mineId);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        /*LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);*/
        mAdapter = new kosAdapter(kosList, new kosAdapter.OnClickListener() {
            @Override
            public void onClick(kosModel kos) {
                Intent detail = new Intent(MainActivity.this, DetailActivity.class);
                detail.putExtra("kosId", kos.getKosId());
                startActivity(detail);
            }

            @Override
            public void Delete(kosModel kos) {
                mFirebaseRef.child("kosTabel").removeValue();
            }
        }, mineId);
        recyclerView.setAdapter(mAdapter);

        loginAsAdmin();

        initFirebase();
    }

    private void loginAsAdmin() {
        if (CoreApplication.getInstance().getSession().getKeyIsLoggedIn()) {
            fab.setVisibility(View.VISIBLE);
        } else {
            fab.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void initFirebase() {
        mFirebaseRef = FirebaseDatabase.getInstance().getReference();

        mFirebaseRef.child("kosTabel").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot != null) {
                    kosModel kos = dataSnapshot.getValue(kosModel.class);
                    kos.setKosId(dataSnapshot.getKey());
                    kosHelper.addKos(kos);

                    refreshView();
                    Log.d("ChildAdded", dataSnapshot.toString());
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mFirebaseRef.child("pemilikTabel").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot != null) {
                    pemilikModel pemilik = dataSnapshot.getValue(pemilikModel.class);
                    pemilik.setPemilikId(dataSnapshot.getKey());
                    pemilikHelper.addPemilik(pemilik);

                    refreshView();
                    Log.d("ChildAdded", dataSnapshot.toString());
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void refreshView() {
        kosList.clear();
        kosList.addAll(kosHelper.getListKos());
        mAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.fab)
    public void onClick() {
        Intent i = new Intent(MainActivity.this, InputActivity.class);
        startActivity(i);
    }

    private void drawerAction(int position) {
        switch (position) {
            case R.id.loginDrawer:
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                break;
            case R.id.logoutDrawer:
                CoreApplication.getInstance().showLogoutConfirmation(MainActivity.this);
                break;
            default:
                break;
        }
    }

    private void menuDrawer(String mineId) {
        Menu nav_Menu = navView.getMenu();
        if (mineId == null || mineId == "") {
            nav_Menu.findItem(R.id.loginDrawer).setVisible(true);
            nav_Menu.findItem(R.id.logoutDrawer).setVisible(false);
        } else {
            nav_Menu.findItem(R.id.loginDrawer).setVisible(false);
            nav_Menu.findItem(R.id.logoutDrawer).setVisible(true);
        }

    }
}
