package com.example.marinex.gittrendapp;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
RecyclerView repo;
    //FragmentManager f=getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.language_choser);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        recycler_ViewAdapter r=new recycler_ViewAdapter();
repo= (RecyclerView) findViewById(R.id.repo);
        repo.setLayoutManager(new LinearLayoutManager(this));
repo.setAdapter(r);
        //myDialog m=new myDialog();
        //m.show(getFragmentManager(),"nbnn");

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.language_choser);
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
        if (id == R.id.today) {
            return true;
        }
        else if (id == R.id.this_week) {
            return true;
        }
        else if (id == R.id.this_month) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.c) {

        } else if (id == R.id.c_plus) {

        } else if (id == R.id.java) {

        } else if (id == R.id.php) {

        } else if (id == R.id.python) {

        } else if (id == R.id.html) {

        }
        else if (id == R.id.javascript) {

        }
        else if (id == R.id.ruby) {

        }else if (id == R.id.all) {

        }else if (id == R.id.html) {

        }
        else if (id == R.id.chash) {

        }
        else if (id == R.id.xml) {

        }else if (id == R.id.css) {

        }
        else if (id == R.id.search) {

        }
        else if (id == R.id.sql) {

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.language_choser);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
