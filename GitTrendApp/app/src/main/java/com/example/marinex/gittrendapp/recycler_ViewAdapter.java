package com.example.marinex.gittrendapp;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.IntegerRes;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by marinex on 24/2/17.
 */

public class recycler_ViewAdapter extends RecyclerView.Adapter<view_holder> {
    ArrayList<String> tittle=new ArrayList<>();
    ArrayList<String> language=new ArrayList<>();
    ArrayList<Integer> fork=new ArrayList<>();
    ArrayList<Integer> stars=new ArrayList<>();
    ArrayList<String> svn_url=new ArrayList<>();
    Context context;
    public recycler_ViewAdapter(ArrayList<String> name,ArrayList<String> lang,
                                ArrayList<Integer> forks,ArrayList<Integer> star,ArrayList<String> svn,Context c){
       this.tittle.addAll(name);
        this.language.addAll(lang);
        this.fork.addAll(forks);
        this.stars.addAll(star);
        this.svn_url.addAll(svn);
this.context=c;


    }

    @Override
    public view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=(LayoutInflater.from(parent.getContext())).inflate(R.layout.recycler_viewitem,parent,false);

        return new view_holder(v);

    }

    @Override
    public void onBindViewHolder(view_holder holder, final int position) {

        holder.title.setText("  "+tittle.get(position));
        holder.lang_used.setText("  "+language.get(position));

        float j = fork.get(position);
        float i = stars.get(position);
        if (i>= 1000) {
            i = i / 1000;

            holder.star.setText("   Stars " + i + "k");
        } else {

            holder.star.setText("   Stars " + i);

        }
        if (j >= 1000) {
            j = j / 1000;

            holder.forks.setText("forks " + j + "k");
        } else {
           
            holder.forks.setText("forks " + j);

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(context, Uri.parse(svn_url.get(position)));
            }
        });


    }


    @Override
    public int getItemCount() {

        return tittle.size();
    }


}
