package com.tutorials.hp.navviewrecyclerview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.tutorials.hp.navviewrecyclerview.mFragments.English11;
import com.tutorials.hp.navviewrecyclerview.mFragments.Home;
import com.tutorials.hp.navviewrecyclerview.mFragments.Test;

import static com.tutorials.hp.navviewrecyclerview.R.*;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final Test mTest = new Test();
   // private final English_12 mEnglish12 = new English_12();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(id.containerID, Home.newInstance("Home")).commit();
        }


        Toolbar toolbar = (Toolbar) findViewById(id.toolbar);
        setSupportActionBar(toolbar);



        //REFERENCE DRAWER,TOGGLE ITS INDICATOR
        DrawerLayout drawer = (DrawerLayout) findViewById(id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, string.navigation_drawer_open, string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //REFERNCE NAV VIEW AND ATTACH ITS ITEM SELECTION LISTENER
        NavigationView navigationView = (NavigationView) findViewById(id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //CLOSE DRAWER WHEN BACK BTN IS CLICKED,IF OPEN
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //RAISED WHEN NAV VIEW ITEM IS SELECTED
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String idText = getResources().getResourceName(id).split("_")[1];
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Log.d(TAG, "onNavigationItemSelected: home "+idText );
        switch(idText){
            case "home":
                MainActivity.this.getSupportActionBar().setTitle(item.getTitle());

                //PERFORM TRANSACTION TO REPLACE CONTAINER WITH FRAGMENT
                MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.containerID, Home.newInstance(idText)).commit();

                //REFERENCE AND CLOSE DRAWER LAYOUT

                drawer.closeDrawer(GravityCompat.START);
                break;
            default:
                MainActivity.this.getSupportActionBar().setTitle(item.getTitle());

                //PERFORM TRANSACTION TO REPLACE CONTAINER WITH FRAGMENT
                MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.containerID, English11.newInstance(idText)).commit();

                //REFERENCE AND CLOSE DRAWER LAYOUT;
                drawer.closeDrawer(GravityCompat.START);
                break;
        }
        return true;



    }
    public void buttonOnClick(View view){
        Toast.makeText(this, ((Button)view).getText(), Toast.LENGTH_SHORT).show();
        MainActivity.this.getSupportActionBar().setTitle(((Button)view).getText());
        //PERFORM TRANSACTION TO REPLACE CONTAINER WITH FRAGMENT
        MainActivity.this.getSupportFragmentManager().beginTransaction().replace(id.containerID, HomeItem.newInstance(((Button)view).getText().toString(), this)).commit();

        //REFERENCE AND CLOSE DRAWER LAYOUT
        return;
    }
}
