package com.rebersincar.otologg.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rebersincar.otologg.Models.ResimPojo;
import com.rebersincar.otologg.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ResimAdapter extends BaseAdapter {

    List<ResimPojo> list;
    Context context;

    public ResimAdapter(List<ResimPojo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.resimlayout, parent , false);
        ImageView resim = convertView.findViewById(R.id.resimLayoutImg);
        String url ="http://otolog.atwebpages.com/"+list.get(position).getResim();
        Picasso.with(context).load(url).centerInside().resize(400,200).into(resim);

        return convertView;
    }
}
