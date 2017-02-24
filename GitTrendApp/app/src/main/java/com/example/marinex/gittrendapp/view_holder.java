package com.example.marinex.gittrendapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by marinex on 24/2/17.
 */

public class view_holder extends RecyclerView.ViewHolder {
    TextView title,lang_used,star,watch,forks;
    public view_holder(View itemView) {
        super(itemView);
        title= (TextView) itemView.findViewById(R.id.title);
        lang_used= (TextView) itemView.findViewById(R.id.lang_used);
        star= (TextView) itemView.findViewById(R.id.star);
        watch= (TextView) itemView.findViewById(R.id.watcher);
        forks= (TextView) itemView.findViewById(R.id.forks);
    }
}
