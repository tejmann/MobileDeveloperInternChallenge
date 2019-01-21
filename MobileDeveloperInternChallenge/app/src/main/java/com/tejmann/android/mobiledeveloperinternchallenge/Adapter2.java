package com.tejmann.android.mobiledeveloperinternchallenge;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter2 extends ArrayAdapter {
    public Adapter2(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater layoutInflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.product,parent,false);
        }
        Product product= (Product) getItem(position);
        String name=product.getName();
        String availability=product.getAvailability();
        String title=product.getTitle();
        String imageUrl=product.getImageUrl();
        TextView nameview=convertView.findViewById(R.id.name);
        TextView titles=convertView.findViewById(R.id.title);
        TextView avail=convertView.findViewById(R.id.avail);
        nameview.setText(name);
        titles.setText(title);
        avail.setText(availability);
        Bitmap bitmap=product.getImaage();
        ImageView imageView=convertView.findViewById(R.id.image);
        if(bitmap==null){
            imageView.setImageResource(R.drawable.icon);
            return convertView;
        }
        else{
            imageView.setImageBitmap(bitmap);
            return convertView;

        }


    }
}
