package com.example.marinex.gittrendapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView repo;
    recycler_ViewAdapter adapter;
    String language, date,url,url1,url2,url3;
    String baseUrl="https://api.github.com";


    Calendar calendar = Calendar.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repo = (RecyclerView) findViewById(R.id.repo);
        repo.setLayoutManager(new LinearLayoutManager(this));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.language_choser);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

checkConnection();
        date = thisweek();
        language = "all";
        url1 = "repositories?q=created:";
        url2 = "+language:";
        url3 = "&sort=stars&order=desc";
        url = url1 + date + url2 + language + url3;
new extractJson().execute();

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
        if (id == R.id.week) {
            date = this.thisweek();
           
          new extractJson().execute();

        } else if (id == R.id.this_month) {
            date = this.thisMonth();
           
          new extractJson().execute();

        } else if (id == R.id.this_year) {
            date = this.thisYear();
           
          new extractJson().execute();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.c) {
            language = "c";
            new extractJson().execute();

        } else if (id == R.id.c_plus) {
            language = "cpp";
           
          new extractJson().execute();
        } else if (id == R.id.java) {
            language = "java";
           
          new extractJson().execute();
        } else if (id == R.id.php) {
            language = "php";
           
          new extractJson().execute();
        } else if (id == R.id.python) {
            language = "python";
           
          new extractJson().execute();
        } else if (id == R.id.html) {
            language = "html";
           
          new extractJson().execute();
        } else if (id == R.id.javascript) {
            language = "javascript";
           
          new extractJson().execute();
        } else if (id == R.id.ruby) {
            language = "ruby";
           
          new extractJson().execute();
        } else if (id == R.id.all) {
            language = "all";
           
          new extractJson().execute();
        } else if (id == R.id.css) {
            language = "css";
           
          new extractJson().execute();
        } else if (id == R.id.perl) {
            language = "perl";
           
          new extractJson().execute();
        } else if (id == R.id.matlab) {
            language = "matlab";
           
          new extractJson().execute();
        } else if (id == R.id.shell) {
            language = "shell";
           
          new extractJson().execute();
        } else if (id == R.id.assembly) {
            language = "assembly";
           
          new extractJson().execute();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.language_choser);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

 public class extractJson extends AsyncTask<Void, Void, Void> {

        ProgressDialog pDialog;

        Retrofit retrofit;
     ArrayList<dataModel> data1=new ArrayList<>();


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog

            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {

           OkHttpClient httpClient=new OkHttpClient();
            Retrofit.Builder builder=new Retrofit.Builder().
                    baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create());
            retrofit=builder.client(httpClient).build();
            return null;
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            api client=retrofit.create(api.class);

          Call<List<dataModel>> call=client.popularRepo(url);
            call.enqueue(new Callback<List<dataModel>>() {
                @Override
                public void onResponse(Response<List<dataModel>> response, Retrofit retrofit) {
                    List<dataModel> data=response.body();
                    for(int i=0;i<20;i++){
                        data1.add(data.get(i));
                    }
                    adapter=new recycler_ViewAdapter(data1,MainActivity.this);
                    repo.setAdapter(adapter);
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }


    public String thisweek() {
        String month;
        int date1 = calendar.get(Calendar.DAY_OF_MONTH);
        int month1 = calendar.get(Calendar.MONTH);
        int Year = calendar.get(Calendar.YEAR);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        if (day != 1) {
            date1 = date1 - day;
        }
        if (date1 < day) {
            month1 = month1 - 1;
            if ((month1 == 0) || (month1 == 2) || (month1 == 4) || (month1 == 6) || (month1 == 7) || (month1 == 9) || (month1 == 11)) {
                date1 = -date1 + 31 - day;
                if (month1 == -1) {
                    month1 = 11;
                    Year = Year - 1;
                }

            } else if (month1 == 1) {
                if ((Year % 4 == 0 || Year % 400 == 0) && (Year % 4 != 0)) {
                    date1 = -date1 + 29 - day;
                } else
                    date1 = -date1 + 28 - day;

            } else {
                date1 = -date1 + 30 - day;

            }
        }
        if (month1 < 10) {
            month = "0" + (month1 + 1);
        } else {
            month = "" + (month1 + 1);
        }
        if (date1 < 10) {
            date = "0" + (date1);
        } else {
            date = "" + (date1);
        }

        return Year + "-" + month + "-" + date1;

    }

    public String thisMonth() {
        String month;
        int month1 = calendar.get(Calendar.MONTH);
        if (month1 < 10) {
            month = "0" + (month1 + 1);
        } else {
            month = "" + (month1 + 1);
        }

        return calendar.get(Calendar.YEAR) + "-" + month;
    }

    public String thisYear() {
        return "" + calendar.get(Calendar.YEAR);
    }

    protected void turnondata() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("you seems offline")
                .setTitle("Unable to connect")
                .setCancelable(false)
                .setPositiveButton("Settings",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(Settings.ACTION_SETTINGS);
                                startActivity(i);
                            }
                        }
                )
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MainActivity.this.finish();
                            }
                        }
                );
        AlertDialog alert = builder.create();
        alert.show();
    }

    public boolean checkConnection() {
        ConnectivityManager con = (ConnectivityManager) getSystemService(MainActivity.this.CONNECTIVITY_SERVICE);
        NetworkInfo net = con.getActiveNetworkInfo();
        if (net != null && net.isConnected()) {
            return true;}
            else{
            MainActivity.this.turnondata();
            return  false;
        }
    }
}



