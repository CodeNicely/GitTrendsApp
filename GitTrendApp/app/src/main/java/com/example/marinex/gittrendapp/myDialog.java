package com.example.marinex.gittrendapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.lang.reflect.Method;

/**
 * Created by marinex on 24/2/17.
 */

public class myDialog extends DialogFragment implements CompoundButton.OnCheckedChangeListener {
    TextView t1;
    ToggleButton wifi, data;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater l = getActivity().getLayoutInflater();
        View v = l.inflate(R.layout.networkdialog, null);
        wifi = (ToggleButton) v.findViewById(R.id.wifi);
        data = (ToggleButton) v.findViewById(R.id.data);
        t1 = (TextView) v.findViewById(R.id.textView);
        wifi.setOnCheckedChangeListener(this);
        data.setOnCheckedChangeListener(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);

        return builder.create();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
       if (buttonView.getId() == R.id.data) {


        }
        if (buttonView.getId() == R.id.wifi){

        }
    }
}