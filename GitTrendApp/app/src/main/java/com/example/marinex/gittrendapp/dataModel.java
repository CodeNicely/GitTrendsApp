package com.example.marinex.gittrendapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by marinex on 25/2/17.
 */


public class dataModel extends AsyncTask<Void, Void, Void> {
    RecyclerView.Adapter<view_holder> r;
    ProgressDialog pDialog;
    private String TAG = dataModel.class.getSimpleName();
    String url;
    Context context;
    String jsonStr;
    ArrayList<String> name = new ArrayList<String>();
    ArrayList<String> language = new ArrayList<String>();
    ArrayList<Integer> forks = new ArrayList<Integer>();
    ArrayList<Integer> stars = new ArrayList<Integer>();



    public dataModel(String api,Context c) {
        this.url = api;
this.context=c;
        Toast.makeText(c, "hkuh", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
        Toast.makeText(context, "x", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        HttpHandler sh = new HttpHandler();

        // Making a request to url and getting response
        jsonStr = sh.makeServiceCall(url);

        Log.e(TAG, "Response from url: " + jsonStr);

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

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        // Dismiss the progress dialog
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public ArrayList<String> getName() {
        return name;
    }

    public ArrayList<String> getLanguage() {
        return language;
    }

    public ArrayList<Integer> getForks() {
        return forks;
    }

    public ArrayList<Integer> getStars() {
        return stars;
    }
}

