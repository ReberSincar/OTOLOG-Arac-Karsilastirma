package com.rebersincar.otologg.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rebersincar.otologg.Models.MarkaPojo;
import com.rebersincar.otologg.R;

import java.util.List;

public class MarkaEkleModelAdapter extends BaseAdapter {
    List<MarkaPojo> markalar;
    Context context;
    LayoutInflater layoutInflater;

    public MarkaEkleModelAdapter(List<MarkaPojo> markalar, Context context, LayoutInflater layoutInflater) {
        this.markalar = markalar;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return markalar.size();
    }

    @Override
    public Object getItem(int position) {
        return markalar.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return convertView;
    }
}
