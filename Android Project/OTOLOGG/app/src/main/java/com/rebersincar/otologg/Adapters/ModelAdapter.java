package com.rebersincar.otologg.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rebersincar.otologg.Models.ModelPojo;
import com.rebersincar.otologg.R;

import java.util.List;

public class ModelAdapter extends BaseAdapter {

    List<ModelPojo> liste;
    Context context;

    public ModelAdapter(List<ModelPojo> list, Context context) {
        this.liste = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return liste.size();
    }

    @Override
    public Object getItem(int position) {
        return liste.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.markalayout,parent,false);
        TextView model = convertView.findViewById(R.id.markaAd);

        model.setText(liste.get(position).getModelad());





        return convertView;
    }
}
