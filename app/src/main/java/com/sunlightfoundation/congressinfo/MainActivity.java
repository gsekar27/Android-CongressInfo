package com.sunlightfoundation.congressinfo;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager legfragmentManager;
    public static String mainActivityState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


            //FragmentManager legFragmentManager = getSupportFragmentManager();
            legfragmentManager = getSupportFragmentManager();
            navigationView.setNavigationItemSelectedListener(this);

        if(mainActivityState == null) {
            setTitle(R.string.app_leg);  //Set up the title of the Header Tab
            legfragmentManager.beginTransaction().replace(R.id.content_frame, new LegTabFragment())
            .commit();
        }
        else if(mainActivityState.equals("bill")){

            setTitle(R.string.app_bills);  //Set up the title of the Header Tab
            legfragmentManager.beginTransaction().replace(R.id.content_frame, new BillTabFragment())
                    .commit();

        }

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

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

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


        if (id == R.id.nav_legislators) {
            mainActivityState = "leg";
            // Handle the Legislator action
            setTitle(R.string.app_leg);  //Set up the title of the Header Tab
            legfragmentManager.beginTransaction().replace(R.id.content_frame, new LegTabFragment())
                    .commit();
        } else if (id == R.id.nav_bills) {
            mainActivityState = "bill";
            setTitle(R.string.app_bills);  //Set up the title of the Header Tab
            legfragmentManager.beginTransaction().replace(R.id.content_frame, new BillTabFragment())
                    .commit();


        } else if (id == R.id.nav_committees) {
            setTitle(R.string.app_comm);  //Set up the title of the Header Tab
            legfragmentManager.beginTransaction().replace(R.id.content_frame, new CommTabFragment())
                    .commit();


        } else if (id == R.id.nav_favorites) {

            setTitle(R.string.app_fav);  //Set up the title of the Header Tab
            legfragmentManager.beginTransaction().replace(R.id.content_frame, new FavTabFragment())
                    .commit();

        }  else if (id == R.id.nav_aboutMe) {

            setTitle(R.string.app_abtMe);  //Set up the title of the Header Tab
            legfragmentManager.beginTransaction().replace(R.id.content_frame, new Other())
                    .commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
