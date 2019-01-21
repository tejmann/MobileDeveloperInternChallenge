package com.tejmann.android.mobiledeveloperinternchallenge;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class Adapter extends ArrayAdapter {


    public Adapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater layoutInflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.customcollectionlayout,parent,false);
        }
        Collection collection1 = (Collection) getItem(position);
        String title= collection1.getTitle();
        TextView textView=convertView.findViewById(R.id.text);
        textView.setText(title);
        return convertView;
    }
}
