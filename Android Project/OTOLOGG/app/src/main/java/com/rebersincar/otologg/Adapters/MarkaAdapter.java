package com.rebersincar.otologg.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rebersincar.otologg.Models.MarkaPojo;
import com.rebersincar.otologg.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MarkaAdapter extends BaseAdapter {

    List<MarkaPojo> list;
    Context context;

    public MarkaAdapter(List<MarkaPojo> list, Context context, Activity activity) {
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.markalayout, parent , false);
        ImageView resim = convertView.findViewById(R.id.markaImage);
        TextView head = convertView.findViewById(R.id.markaAd);

        head.setText(list.get(position).getAd());
        String url ="http://otolog.atwebpages.com/"+list.get(position).getLogo();
        Picasso.with(context).load(url).resize(50,50).into(resim);
        return convertView;
    }
}
