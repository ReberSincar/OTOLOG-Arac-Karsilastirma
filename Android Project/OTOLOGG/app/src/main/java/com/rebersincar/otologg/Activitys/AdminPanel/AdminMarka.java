package com.rebersincar.otologg.Activitys.AdminPanel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.rebersincar.otologg.Activitys.ModelActivity;
import com.rebersincar.otologg.Adapters.MarkaAdapter;
import com.rebersincar.otologg.Models.MarkaPojo;
import com.rebersincar.otologg.R;
import com.rebersincar.otologg.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminMarka extends AppCompatActivity {

    ListView listView;
    MarkaAdapter markaAdapter;
    List<MarkaPojo> list;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_marka);
        listView = findViewById(R.id.markaAdminList);
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

                    markaAdapter = new MarkaAdapter(list,getApplicationContext(),AdminMarka.this);
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
                String islem = extras.getString("islem");

                if (islem.equals("markaduzenle"))
                {
                    Intent intent = new Intent(getApplicationContext(),MarkaDuzenle.class);
                    intent.putExtra("marka",list.get(position).getAd());
                    intent.putExtra("logo",list.get(position).getLogo());
                    intent.putExtra("id",list.get(position).getId());

                    startActivity(intent);
                }
                else if(islem.equals("modelekle"))
                {
                    Intent intent = new Intent(getApplicationContext(),ModelEkle.class);
                    intent.putExtra("marka",list.get(position).getAd());
                    intent.putExtra("logo",list.get(position).getLogo());
                    intent.putExtra("id",list.get(position).getId());

                    startActivity(intent);
                }
                else if(islem.equals("modelduzenle"))
                {
                    Intent intent = new Intent(getApplicationContext(),AdminModel.class);
                    intent.putExtra("islem","modelduzenle");
                    intent.putExtra("marka",list.get(position).getAd());
                    intent.putExtra("logo",list.get(position).getLogo());
                    intent.putExtra("id",list.get(position).getId());

                    startActivity(intent);
                }
                else if(islem.equals("modelsil"))
                {
                    Intent intent = new Intent(getApplicationContext(),ModelSil.class);
                    intent.putExtra("markaid",list.get(position).getId());

                    startActivity(intent);
                }
                else if(islem.equals("videoekle"))
                {
                    Intent intent = new Intent(getApplicationContext(),AdminModel.class);
                    intent.putExtra("islem","videoekle");
                    intent.putExtra("marka",list.get(position).getAd());
                    intent.putExtra("logo",list.get(position).getLogo());
                    intent.putExtra("id",list.get(position).getId());
                    startActivity(intent);
                }
                else if(islem.equals("videosil"))
                {
                    Intent intent = new Intent(getApplicationContext(),VideoSil.class);
                    intent.putExtra("markaid",list.get(position).getId());
                    startActivity(intent);
                }
                else if(islem.equals("resimekle"))
                {
                    Intent intent = new Intent(getApplicationContext(),AdminModel.class);
                    intent.putExtra("islem","resimekle");
                    intent.putExtra("marka",list.get(position).getAd());
                    intent.putExtra("logo",list.get(position).getLogo());
                    intent.putExtra("id",list.get(position).getId());
                    startActivity(intent);
                }
                else if(islem.equals("resimsil"))
                {

                    Intent intent = new Intent(getApplicationContext(),AdminModel.class);
                    intent.putExtra("islem","resimsil");
                    intent.putExtra("marka",list.get(position).getAd());
                    intent.putExtra("logo",list.get(position).getLogo());
                    intent.putExtra("id",list.get(position).getId());
                    startActivity(intent);
                }


            }
        });
    }
}


