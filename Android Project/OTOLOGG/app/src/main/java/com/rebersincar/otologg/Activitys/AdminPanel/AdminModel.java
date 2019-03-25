package com.rebersincar.otologg.Activitys.AdminPanel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rebersincar.otologg.Activitys.CarActivity;
import com.rebersincar.otologg.Activitys.CompareActivity;
import com.rebersincar.otologg.Activitys.ModelActivity;
import com.rebersincar.otologg.Adapters.ModelAdapter;
import com.rebersincar.otologg.Models.ModelPojo;
import com.rebersincar.otologg.R;
import com.rebersincar.otologg.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminModel extends AppCompatActivity {

    ListView list;
    List<ModelPojo> pojo;
    ModelAdapter modelAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_model);
        list = findViewById(R.id.adminModelList);

        listYukle();
        Operation();

    }

    public void listYukle()
    {
        Bundle extras = getIntent().getExtras();
        int id = extras.getInt("id",-1);

        Call<List<ModelPojo>> request = ManagerAll.getInstance().model(id);
        request.enqueue(new Callback<List<ModelPojo>>() {
            @Override
            public void onResponse(Call<List<ModelPojo>> call, Response<List<ModelPojo>> response) {
                if (response.isSuccessful())
                {
                    pojo = response.body();
                    Toast.makeText(AdminModel.this, "Listelendi", Toast.LENGTH_SHORT).show();
                    modelAdapter = new ModelAdapter(pojo,getApplicationContext());
                    list.setAdapter(modelAdapter);
                }
                else
                    Toast.makeText(AdminModel.this, "Bir Sorun Oluştu", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<ModelPojo>> call, Throwable t) {
                Toast.makeText(AdminModel.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Operation()
    {

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle extras = getIntent().getExtras();
                String islem = extras.getString("islem");
                String logo = extras.getString("logo");
                String markaAd = extras.getString("marka");

                if (pojo.get(position).getAgirlik()==null)
                {
                    Toast.makeText(AdminModel.this, "Bu Markanın Modeli Yok", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(islem.equals("modelduzenle"))
                    {
                        Intent intent = new Intent(getApplicationContext(),ModelDuzenle.class);
                        intent.putExtra("markaid",pojo.get(position).getMarkaid());
                        intent.putExtra("modelid",pojo.get(position).getModelid());
                        Log.i("deneme",""+pojo.get(position).getMarkaid()+" "+pojo.get(position).getModelid());
                        intent.putExtra("logo",logo);
                        startActivity(intent);
                    }
                    else if(islem.equals("videoekle"))
                    {
                        Intent intent = new Intent(getApplicationContext(),VideoEkle.class);
                        intent.putExtra("markaid",pojo.get(position).getMarkaid());
                        intent.putExtra("modelid",pojo.get(position).getModelid());
                        intent.putExtra("modelad",pojo.get(position).getModelad());
                        intent.putExtra("markaad",markaAd);
                        intent.putExtra("logo",logo);
                        startActivity(intent);
                    }
                    else if(islem.equals("resimsil"))
                    {
                        Intent intent = new Intent(getApplicationContext(),ResimDel.class);
                        intent.putExtra("markaid",pojo.get(position).getMarkaid());
                        intent.putExtra("modelid",pojo.get(position).getModelid());
                        intent.putExtra("modelad",pojo.get(position).getModelad());
                        intent.putExtra("markaad",markaAd);
                        intent.putExtra("logo",logo);
                        startActivity(intent);
                    }
                    else if(islem.equals("resimekle"))
                    {
                        Intent intent = new Intent(getApplicationContext(),ResimEkle.class);
                        intent.putExtra("markaid",pojo.get(position).getMarkaid());
                        intent.putExtra("modelid",pojo.get(position).getModelid());
                        intent.putExtra("modelad",pojo.get(position).getModelad());
                        intent.putExtra("markaad",markaAd);
                        intent.putExtra("logo",logo);
                        startActivity(intent);
                    }
                }

            }
        });
    }
}
