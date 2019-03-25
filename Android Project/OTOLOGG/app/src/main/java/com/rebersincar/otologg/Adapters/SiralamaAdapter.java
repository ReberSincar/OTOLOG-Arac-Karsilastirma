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
import com.rebersincar.otologg.Models.SiralamaPojo;
import com.rebersincar.otologg.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SiralamaAdapter extends BaseAdapter {

    List<SiralamaPojo> list;
    Context context;

    public SiralamaAdapter(List<SiralamaPojo> list, Context context) {
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

        convertView = LayoutInflater.from(context).inflate(R.layout.siralamalayout, parent , false);
        ImageView logo = convertView.findViewById(R.id.logo);
        TextView marka = convertView.findViewById(R.id.siralamaMarka);
        TextView model = convertView.findViewById(R.id.siralamaModel);
        TextView puan = convertView.findViewById(R.id.siralamaPuan);

        marka.setText(list.get(position).getMarkaad());
        model.setText(list.get(position).getModelad());
        puan.setText(list.get(position).getPuan());
        String url ="http://otolog.atwebpages.com/"+list.get(position).getLogo();
        Picasso.with(context).load(url).resize(50,50).into(logo);
        return convertView;
    }
}
