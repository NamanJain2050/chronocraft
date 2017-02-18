package com.cronocraft.cronocraft.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cronocraft.cronocraft.R;
import com.cronocraft.cronocraft.Session.LoggedInSession;
import com.cronocraft.cronocraft.fragment.DesignFragment;
import com.cronocraft.cronocraft.fragment.HomeFragment;
import com.cronocraft.cronocraft.fragment.SettingsFragment;
import com.cronocraft.cronocraft.fragment.WatchFragment;

import java.util.HashMap;

public class NavigationDrawerActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener,DesignFragment.OnFragmentInteractionListener,WatchFragment.OnFragmentInteractionListener,SettingsFragment.OnFragmentInteractionListener{

    private static String urlNavHeaderBg = "http://api.androidhive.info/images/nav-menu-header-bg.jpg";
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private View navHeader;
    private TextView txtName,txtEmail;
    private ImageView imgProfile,imgNavHeaderBg;
   // private FirebaseAuth auth;

    LoggedInSession session;

    public static int navItemIndex = 0;
    private static final String TAG_HOME = "home";
    private static final String TAG_DESIGN = "design";
    private static final String TAG_WATCHES = "watches";
    //private static final String TAG_VIDEO = "video";
    //private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_SETTINGS = "settings";
    public static String CURRENT_TAG = TAG_HOME;

    private String[] activityTitles;
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

       // auth = FirebaseAuth.getInstance();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        session = new LoggedInSession(getApplicationContext());

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        //fab = (FloatingActionButton) findViewById(R.id.fab);

        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.name_profile);
        txtEmail = (TextView) navHeader.findViewById(R.id.email_profile);
       // imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);
        imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);

        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();
                ConversationActivity.show(getApplicationContext());
            }
        })*/

        loadNavHeader();
        setUpNavigationView();
        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }
    }

    private void setUpNavigationView(){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem){

                switch(menuItem.getItemId()){
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_design:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_DESIGN;
                        break;
                    case R.id.nav_watches:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_WATCHES;
                        break;
                    //case R.id.nav_video:
                      //  navItemIndex = 3;
                        //CURRENT_TAG = TAG_VIDEO;
                        //break;
                    //case R.id.nav_notifications:
                      //  navItemIndex = 4;
                        //CURRENT_TAG = TAG_NOTIFICATIONS;
                        //break;
                    case R.id.nav_settings:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_SETTINGS;
                        break;
                    case R.id.nav_about_us:
                        startActivity(new Intent(NavigationDrawerActivity.this, AboutUsActivity.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_privacy_policy:
                        startActivity(new Intent(NavigationDrawerActivity.this, PrivacyPolicyActivity.class));
                        drawer.closeDrawers();
                        return true;
                    default:
                        navItemIndex = 0;
                }

                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);
                loadHomeFragment();
                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }
        if (shouldLoadHomeFragOnBackPress) {
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.main, menu);
        }
       /* if (navItemIndex == 4) {
            getMenuInflater().inflate(R.menu.notifications, menu);
        }*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            Toast.makeText(getApplicationContext(), "Logout user!", Toast.LENGTH_LONG).show();
            session.setLogin(false);
            Intent i = new Intent(NavigationDrawerActivity.this,LoginActivity.class);
            startActivity(i);
            finish();

           // auth.signOut();

// this listener will be called when there is change in firebase user session
            /*FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user == null) {
                        // user auth state is changed - user is null
                        // launch login activity
                        startActivity(new Intent(NavigationDrawerActivity.this, LoginActivity.class));
                        finish();
                    }
                }
            };
            auth.addAuthStateListener(authListener);*/
            return true;
        }
        if (id == R.id.action_mark_all_read) {
            Toast.makeText(getApplicationContext(), "All notifications marked as read!", Toast.LENGTH_LONG).show();
        }
        if (id == R.id.action_clear_notifications) {
            Toast.makeText(getApplicationContext(), "Clear all notifications!", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private Fragment getHomeFragment(){
        switch (navItemIndex){

            case 0:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                DesignFragment designFragment = new DesignFragment();
                return designFragment;
            case 2:
                WatchFragment watchFragment = new WatchFragment();
                return watchFragment;
           // case 3:
             //   IntroVideoFragment introVideoFragment = new IntroVideoFragment();
               // return introVideoFragment;
            //case 4:
              //  NotificationsFragment notificationsFragment = new NotificationsFragment();
                //return notificationsFragment;
            case 3:
                SettingsFragment settingsFragment = new SettingsFragment();
                return settingsFragment;
            default:
                return new HomeFragment();
        }
    }

    private void loadHomeFragment(){
        selectNavMenu();
        setToolbarTitle();
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            //toggleFab();
            return;
        }
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        //toggleFab();
        drawer.closeDrawers();
        invalidateOptionsMenu();
    }

   /* private void toggleFab() {
        if (navItemIndex == 0)
            fab.show();
        else
            fab.hide();
    }*/

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    void loadNavHeader(){

        HashMap<String,String> profile = session.getUserDetails();
        txtName.setText(profile.get("name"));
        txtEmail.setText(profile.get("email"));

        Glide.with(this).load(urlNavHeaderBg)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgNavHeaderBg);



        //navigationView.getMenu().getItem(4).setActionView(R.layout.menu_dot);
    }

    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }
}
