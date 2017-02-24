package com.example.marinex.gittrendapp;

import android.content.Context;
import android.support.annotation.IntegerRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by marinex on 24/2/17.
 */

public class recycler_ViewAdapter extends RecyclerView.Adapter<view_holder> {
    dataModel model;String url;Context context;ArrayList<String> tittle,language=null;
    ArrayList<Integer> stars=null,fork=null;
    public recycler_ViewAdapter(Context c){
        this.context=c;
        model  =new dataModel("https://api.github.com/search/repositories?q=created:2017-02-23+language:assembly&sort=stars&order=desc",c);

        /*tittle=new ArrayList<String>(model.getName());
        language=new ArrayList<String >(model.getLanguage());
        fork=new ArrayList<Integer>(model.getForks());
        stars=new ArrayList<Integer>(model.getStars());
*/

    }

    @Override
    public view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=(LayoutInflater.from(parent.getContext())).inflate(R.layout.recycler_viewitem,parent,false);
        return new view_holder(v);
    }

    @Override
    public void onBindViewHolder(view_holder holder, int position) {

        holder.title.setText(model.getName().get(position));
        holder.lang_used.setText(model.getLanguage().get(position));
        holder.star.setText(model.getStars().get(position));
        holder.forks.setText(model.getForks().get(position));
       /*holder.title.setText(tittle.get(position));
        holder.lang_used.setText(language.get(position));
        holder.star.setText(stars.get(position));
        holder.forks.setText(fork.get(position));
        /*int i = fork.get(position);
        int j = stars.get(position);
        if (i >= 1000) {
            i = i / 1000;
            holder.star.setText("Stars " + i + "k");
        } else {
            holder.star.setText("Stars " + i);
        }
        if (j >= 1000) {
            j = j / 1000;
            holder.forks.setText("Stars " + j + "k");
        } else {
            holder.forks.setText("Stars " + j);
        }*/
    }


    @Override
    public int getItemCount() {

        return model.getName().size();
    }
}
