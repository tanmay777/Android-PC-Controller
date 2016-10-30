package com.example.tanmayjha.androidmousepadandvideocontrolller.Entity.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tanmayjha.androidmousepadandvideocontrolller.Boundary.ConnectionService;
import com.example.tanmayjha.androidmousepadandvideocontrolller.Entity.About.AboutFragment;
import com.example.tanmayjha.androidmousepadandvideocontrolller.Entity.Mouse.MouseFragment;
import com.example.tanmayjha.androidmousepadandvideocontrolller.Entity.Player.PlayerFragment;
import com.example.tanmayjha.androidmousepadandvideocontrolller.Entity.Search.SearchFragment;
import com.example.tanmayjha.androidmousepadandvideocontrolller.Entity.SetUpConnection.SetUpConnectionFragment;
import com.example.tanmayjha.androidmousepadandvideocontrolller.Entity.SystemSettings.SystemSettingsFragment;
import com.example.tanmayjha.androidmousepadandvideocontrolller.R;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        SetUpConnectionFragment setUpConnectionFragment=new SetUpConnectionFragment();
        ft.replace(R.id.container,setUpConnectionFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();

        if (id == R.id.set_up_connection) {
            SetUpConnectionFragment setUpConnectionFragment=new SetUpConnectionFragment();
            ft.replace(R.id.container,setUpConnectionFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            // Handle the camera action
        } else if (id == R.id.mouse) {
            MouseFragment mouseFragment=new MouseFragment();
            ft.replace(R.id.container,mouseFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();

        } else if (id == R.id.player) {
            PlayerFragment playerFragment=new PlayerFragment();
            ft.replace(R.id.container,playerFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();

        } else if (id == R.id.search) {
            SearchFragment searchFragment=new SearchFragment();
            ft.replace(R.id.container,searchFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();

        } else if (id == R.id.system_settings) {
            SystemSettingsFragment systemSettingsFragment=new SystemSettingsFragment();
            ft.replace(R.id.container,systemSettingsFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();

        } else if (id == R.id.about) {
            AboutFragment aboutFragment=new AboutFragment();
            ft.replace(R.id.container,aboutFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(getBaseContext(),ConnectionService.class));
    }
}
