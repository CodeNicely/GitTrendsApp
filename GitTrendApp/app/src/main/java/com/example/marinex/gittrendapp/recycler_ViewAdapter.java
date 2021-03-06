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
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by marinex on 24/2/17.
 */

public class recycler_ViewAdapter extends RecyclerView.Adapter<view_holder> {
ArrayList<dataModel> data=new ArrayList<>();
    Context context;


    public recycler_ViewAdapter(ArrayList<dataModel> data1, Context c) {
        this.data=data1;
        this.context=c;

    }


    @Override
    public view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=(LayoutInflater.from(parent.getContext())).inflate(R.layout.recycler_viewitem,parent,false);

        return new view_holder(v);

    }

    @Override
    public void onBindViewHolder(view_holder holder, final int position) {

        holder.title.setText(data.get(position).getRepo_name());
        holder.lang_used.setText(data.get(position).getLanguage());
        holder.star.setText(data.get(position).getStars());
        holder.forks.setText(data.get(position).getForks());
NumberFormat nf=NumberFormat.getInstance();
        float j = data.get(position).getForks();
        float i = data.get(position).getStars();
        if (i>= 1000) {
            i = i / 1000;
            nf.setMaximumFractionDigits(1);

            holder.star.setText("   stars " + nf.format(i) + "k");
        } else {
            nf.setMaximumFractionDigits(0);

            holder.star.setText("   Stars " + nf.format(i));

        }
        if (j >= 1000) {
            j = j / 1000;
            nf.setMaximumFractionDigits(1);

            holder.forks.setText("forks " +   nf.format(i)+ "k");
        } else {

            nf.setMaximumFractionDigits(0);

            holder.forks.setText("forks " +  nf.format(i));

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(context, Uri.parse(data.get(position).getSvn_url()));
            }
        });


    }


    @Override
    public int getItemCount() {

        return data.size();
    }


}
