package com.rebersincar.otologg.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.rebersincar.otologg.Models.SliderPojo;
import com.rebersincar.otologg.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    List<SliderPojo> resimler;
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(List<SliderPojo> resimler, Context context) {
        this.resimler = resimler;
        this.context = context;
    }

    @Override
    public int getCount() {
        return resimler.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {


        return (view==(RelativeLayout)o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.sliderlayout,container,false);
        ImageView image = view.findViewById(R.id.sliderImageView);
        String url = "http://otolog.atwebpages.com/"+resimler.get(position).getResim();
        Picasso.with(context).load(url).centerInside().resize(400,250).into(image);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }
}
