package com.example.marinex.gittrendapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by marinex on 24/2/17.
 */

public class recycler_ViewAdapter extends RecyclerView.Adapter<view_holder> {
    @Override
    public view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=(LayoutInflater.from(parent.getContext())).inflate(R.layout.recycler_viewitem,parent,false);
        return new view_holder(v);
    }

    @Override
    public void onBindViewHolder(view_holder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 1;
    }
}
