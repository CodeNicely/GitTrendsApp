package com.example.marinex.gittrendapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
RecyclerView repo;recycler_ViewAdapter adapter;
    String url1,url2,url3,language,date,url;


    Calendar calendar=Calendar.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      repo=(RecyclerView)findViewById(R.id.repo);
        repo.setLayoutManager(new LinearLayoutManager(this));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.language_choser);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();



        date=thisweek();
         language="all";
        url1="https://api.github.com/search/repositories?q=created:";
        url2="+language:";
        url3="&sort=stars&order=desc";
        url=url1+date+url2+language+url3;
        new dataModel(url).execute();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);





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
            date=this.thisweek();
            url=url1+date+url2+language+url3;
            new dataModel(url).execute();

        }
        else if (id == R.id.this_month) {
            date=this.thisMonth();
            url=url1+date+url2+language+url3;
            new dataModel(url).execute();

        }
        else if (id == R.id.this_year) {
            date=this.thisYear();
            url=url1+date+url2+language+url3;
            new dataModel(url).execute();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.c) {
            language="c";
            url=url1+date+url2+language+url3;
            new dataModel(url).execute();

        } else if (id == R.id.c_plus) {
            language="cpp";
            url=url1+date+url2+language+url3;
            new dataModel(url).execute();
        } else if (id == R.id.java) {
            language="java";
            url=url1+date+url2+language+url3;
            new dataModel(url).execute();
        } else if (id == R.id.php) {
            language="php";
            url=url1+date+url2+language+url3;
            new dataModel(url).execute();
        } else if (id == R.id.python) {
            language="python";
            url=url1+date+url2+language+url3;
            new dataModel(url).execute();
        } else if (id == R.id.html) {
            language="html";
            url=url1+date+url2+language+url3;
            new dataModel(url).execute();
        }
        else if (id == R.id.javascript) {
            language="javascript";
            url=url1+date+url2+language+url3;
            new dataModel(url).execute();
        }
        else if (id == R.id.ruby) {
            language="ruby";
            url=url1+date+url2+language+url3;
            new dataModel(url).execute();
        }else if (id == R.id.all) {
            language="all";
            url=url1+date+url2+language+url3;
            new dataModel(url).execute();
        }

        else if (id == R.id.css) {
            language="css";
            url=url1+date+url2+language+url3;
            new dataModel(url).execute();
        }
        else if (id == R.id.perl) {
            language="perl";
            url=url1+date+url2+language+url3;
            new dataModel(url).execute();
        }
        else if (id == R.id.matlab) {
            language="matlab";
            url=url1+date+url2+language+url3;
            new dataModel(url).execute();
        }
        else if (id == R.id.shell) {
            language="shell";
            url=url1+date+url2+language+url3;
            new dataModel(url).execute();
        }
        else if (id == R.id.assembly) {
            language="assembly";
            url=url1+date+url2+language+url3;
            new dataModel(url).execute();
        }





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.language_choser);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class dataModel extends AsyncTask<Void, Void, Void> {

        ProgressDialog pDialog;
        private String TAG = dataModel.class.getSimpleName();
        String url;

        String jsonStr;
        ArrayList<String> name = new ArrayList<String>();
        ArrayList<String> language = new ArrayList<String>();
        ArrayList<Integer> forks = new ArrayList<Integer>();
        ArrayList<Integer> stars = new ArrayList<Integer>();
        ArrayList<String> svn_url=new ArrayList<>();


        public dataModel(String api) {
            this.url = api;

        }




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
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);


            return null;
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray item = jsonObj.getJSONArray("items");

                    // looping through All Contacts
                    for (int i = 0; i < 20; i++) {
                        JSONObject c = item.getJSONObject(i);
                        name.add(c.getString("name"));
                        language.add(c.getString("language"));
                        forks.add(c.getInt("forks"));
                        stars.add(c.getInt("watchers"));
                        svn_url.add(c.getString("svn_url"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
adapter=new recycler_ViewAdapter(name,language,forks,stars,svn_url,MainActivity.this);
                repo.setAdapter(adapter);
            }
        }
    }


    public String thisweek() {
        String month;
        int date1= calendar.get(Calendar.DAY_OF_MONTH);
        int month1=calendar.get(Calendar.MONTH);
        int Year=calendar.get(Calendar.YEAR);
        int day=calendar.get(Calendar.DAY_OF_WEEK);
if(day!=1){
    date1=date1-day;
}
        if(date1<day) {
            month1=month1-1;
            if ((month1 == 0) || (month1 == 2) || (month1 == 4) || (month1 == 6) || (month1 == 7) || (month1 == 9) || (month1 == 11)) {
                date1 = -date1 + 31-day;
                if (month1 == -1) {
                    month1 = 11;
                    Year = Year - 1;
                }

            } else if (month1 == 1) {
                if ((Year % 4 == 0 || Year % 400 == 0) && (Year % 4 != 0)) {
                    date1 = -date1 + 29 - day;
                } else
                    date1 = -date1 + 28 - day;

            }
            else{
                date1 = -date1 + 30 - day;

            }
        }
        if(month1<10){
            month="0"+(month1+1);
        }
        else
        {
            month=""+(month1+1);
        }
        if(date1<10){
            date="0"+(date1);
        }
        else
        {
            date=""+(date1);
        }

        return Year+"-"+month+"-"+date1;

    }
    public String thisMonth(){
        String month;
         int month1=calendar.get(Calendar.MONTH);
        if(month1<10){
            month="0"+(month1+1);
        }
        else
        {
            month=""+(month1+1);
        }

return calendar.get(Calendar.YEAR)+"-"+month;
    }
    public String thisYear(){
        return ""+calendar.get(Calendar.YEAR);
    }

}



