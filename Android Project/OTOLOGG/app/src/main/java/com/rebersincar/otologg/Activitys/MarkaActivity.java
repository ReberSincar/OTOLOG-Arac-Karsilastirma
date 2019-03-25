package com.rebersincar.otologg.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.rebersincar.otologg.Adapters.MarkaAdapter;
import com.rebersincar.otologg.Models.MarkaPojo;
import com.rebersincar.otologg.R;
import com.rebersincar.otologg.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarkaActivity extends AppCompatActivity {

    ListView listView;
    TextView baslik;
    MarkaAdapter markaAdapter;
    List<MarkaPojo> list;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marka);
        listView = findViewById(R.id.markaList);
        baslik = findViewById(R.id.markaAd);
        listYukle();
        postAc();
    }

    public void listYukle()
    {
        Call<List<MarkaPojo>> requestMarka = ManagerAll.getInstance().marka();
        requestMarka.enqueue(new Callback<List<MarkaPojo>>() {
            @Override
            public void onResponse(Call<List<MarkaPojo>> call, Response<List<MarkaPojo>> response) {
                if (response.isSuccessful())
                {
                    list = response.body();

                    markaAdapter = new MarkaAdapter(list,getApplicationContext(),MarkaActivity.this);
                    listView.setAdapter(markaAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<MarkaPojo>> call, Throwable t) {
            }
        });
    }

    public void postAc()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                extras = getIntent().getExtras();
                String button = extras.getString("button",null);

                Intent intent = new Intent(getApplicationContext(),ModelActivity.class);
                intent.putExtra("button",button);
                intent.putExtra("marka",list.get(position).getAd());
                intent.putExtra("id",list.get(position).getId());

                startActivity(intent);
            }
        });
    }
}
