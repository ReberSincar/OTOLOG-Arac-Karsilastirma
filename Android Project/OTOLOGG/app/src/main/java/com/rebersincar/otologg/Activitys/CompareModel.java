package com.rebersincar.otologg.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rebersincar.otologg.Adapters.ModelAdapter;
import com.rebersincar.otologg.Models.ModelPojo;
import com.rebersincar.otologg.R;
import com.rebersincar.otologg.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompareModel extends AppCompatActivity {

    ListView list;
    List<ModelPojo> pojo;
    ModelAdapter modelAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_model);
        list = findViewById(R.id.modelCompareList);

        listYukle();
        car();

    }

    public void listYukle()
    {

        Bundle extras = getIntent().getExtras();
        int id = extras.getInt("markaid2",-1);

        Call<List<ModelPojo>> request = ManagerAll.getInstance().model(id);
        request.enqueue(new Callback<List<ModelPojo>>() {
            @Override
            public void onResponse(Call<List<ModelPojo>> call, Response<List<ModelPojo>> response) {
                if (response.isSuccessful())
                {
                    pojo = response.body();
                    modelAdapter = new ModelAdapter(pojo,getApplicationContext());
                    list.setAdapter(modelAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<ModelPojo>> call, Throwable t) {
            }
        });
    }

    public void car()
    {

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (pojo.get(position).getAgirlik()==null)
                {
                    Toast.makeText(CompareModel.this, "Bu Markanın Modeli Yok", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Bundle extras = getIntent().getExtras();
                    String araba = extras.getString("araba");
                    int markaid = extras.getInt("markaid");
                    int modelid = extras.getInt("modelid");
                    int markaid2 = extras.getInt("markaid2");

                    Intent intent = new Intent(getApplicationContext(), CompareActivity.class);
                    intent.putExtra("araba",araba);
                    intent.putExtra("where","compare");
                    intent.putExtra("markaid",markaid);
                    intent.putExtra("modelid",modelid);
                    intent.putExtra("markaid2",markaid2);
                    intent.putExtra("modelid2", Integer.valueOf(pojo.get(position).getModelid()));
                    startActivity(intent);
                }
            }
        });
    }
}
