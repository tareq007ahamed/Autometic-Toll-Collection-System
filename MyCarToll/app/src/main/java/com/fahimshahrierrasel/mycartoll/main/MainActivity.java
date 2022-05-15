package com.fahimshahrierrasel.mycartoll.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fahimshahrierrasel.mycartoll.R;
import com.fahimshahrierrasel.mycartoll.car.CarFragment;
import com.fahimshahrierrasel.mycartoll.car.CarPresenter;
import com.fahimshahrierrasel.mycartoll.data.model.Driver;
import com.fahimshahrierrasel.mycartoll.data.model.User;
import com.fahimshahrierrasel.mycartoll.home.HomeFragment;
import com.fahimshahrierrasel.mycartoll.home.HomePresenter;
import com.fahimshahrierrasel.mycartoll.log.LogFragment;
import com.fahimshahrierrasel.mycartoll.log.LogPresenter;
import com.fahimshahrierrasel.mycartoll.login.LoginActivity;
import com.fahimshahrierrasel.mycartoll.profile.ProfileFragment;
import com.fahimshahrierrasel.mycartoll.profile.ProfilePresenter;

import java.text.BreakIterator;

import nouri.in.goodprefslib.GoodPrefs;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private HomePresenter homePresenter;
    private LogPresenter logPresenter;
    private CarPresenter carPresenter;
    private ProfilePresenter profilePresenter;

    GoodPrefs goodPrefs;
    private Driver driver;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        goodPrefs = GoodPrefs.getInstance();

        driver = goodPrefs.getObject("driver", Driver.class);
        user = goodPrefs.getObject("user", User.class);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView textViewDriverName = headerView.findViewById(R.id.textView_driver_name);
        TextView textViewDriverEmail = headerView.findViewById(R.id.textView_driver_email);
        textViewDriverName.setText(driver.getName());
        textViewDriverEmail.setText(user.getEmail());

        showHomeFragment();
    }

    private void showHomeFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentFrame);
        HomeFragment homeFragment;
        if (fragment instanceof HomeFragment) {
            homeFragment = (HomeFragment) fragment;

        } else {
            homeFragment = HomeFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentFrame, homeFragment)
                    .commit();
        }
        homePresenter = new HomePresenter(homeFragment);
    }

    private void showLogFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentFrame);
        LogFragment logFragment;
        if (fragment instanceof LogFragment) {
            logFragment = (LogFragment) fragment;

        } else {

            logFragment = LogFragment.newInstance(driver.getId(), "DRIVER");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentFrame, logFragment)
                    .addToBackStack(null)
                    .commit();
        }
        logPresenter = new LogPresenter(logFragment);
    }

    private void showCarFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentFrame);
        CarFragment carFragment;
        if (fragment instanceof CarFragment) {
            carFragment = (CarFragment) fragment;
        } else {
            carFragment = CarFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentFrame, carFragment)
                    .addToBackStack(null)
                    .commit();
        }
        carPresenter = new CarPresenter(carFragment);
    }

    private void showProfileFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentFrame);
        ProfileFragment profileFragment;
        if (fragment instanceof ProfileFragment) {
            profileFragment = (ProfileFragment) fragment;
        } else {
            profileFragment = ProfileFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentFrame, profileFragment)
                    .addToBackStack(null)
                    .commit();
        }
        profilePresenter = new ProfilePresenter(profileFragment);
    }

    private void loggingOut() {
        goodPrefs.deleteValue("authenticated");
        goodPrefs.deleteValue("user");
        goodPrefs.deleteValue("driver");
        startActivity(new Intent(this, LoginActivity.class));
        finish();
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            showHomeFragment();
        } else if (id == R.id.nav_cars) {
            showCarFragment();
        } else if (id == R.id.nav_logs) {
            showLogFragment();
        } else if (id == R.id.nav_profile) {
            showProfileFragment();
        } else if (id == R.id.nav_logout) {
            loggingOut();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
