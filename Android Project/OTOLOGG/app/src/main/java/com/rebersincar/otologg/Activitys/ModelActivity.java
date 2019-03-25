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

public class ModelActivity extends AppCompatActivity {

    ListView list;
    TextView text;
    List<ModelPojo> pojo;
    ModelAdapter modelAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);
        list = findViewById(R.id.modelList);
        text = findViewById(R.id.modelHead);

        listYukle();
        car();

    }

    public void listYukle()
    {
        Bundle extras = getIntent().getExtras();
        int id = extras.getInt("id",-1);
        String marka = extras.getString("marka",null);
        text.setText(marka);

        Call<List<ModelPojo>> request = ManagerAll.getInstance().model(id);
        request.enqueue(new Callback<List<ModelPojo>>() {
            @Override
            public void onResponse(Call<List<ModelPojo>> call, Response<List<ModelPojo>> response) {
                if (response.isSuccessful())
                {
                    pojo = response.body();
                    Toast.makeText(ModelActivity.this, "Listelendi", Toast.LENGTH_SHORT).show();
                    modelAdapter = new ModelAdapter(pojo,getApplicationContext());
                    list.setAdapter(modelAdapter);
                }
                else
                    Toast.makeText(ModelActivity.this, "Bir Sorun Oluştu", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<ModelPojo>> call, Throwable t) {
                Toast.makeText(ModelActivity.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void car()
    {

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle extras = getIntent().getExtras();
                String button = extras.getString("button",null);

                if (pojo.get(position).getAgirlik()==null)
                {
                    Toast.makeText(ModelActivity.this, "Bu Markanın Modeli Yok", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(button.equals("0"))
                    {
                        Intent intent = new Intent(getApplicationContext(),CarActivity.class);
                        intent.putExtra("markaid",pojo.get(position).getMarkaid());
                        intent.putExtra("modelid",pojo.get(position).getModelid());
                        startActivity(intent);
                    }
                    else
                    {
                        Intent intent = new Intent(getApplicationContext(),CompareActivity.class);
                        intent.putExtra("where","normal");
                        intent.putExtra("araba","1");
                        intent.putExtra("markaid",pojo.get(position).getMarkaid());
                        intent.putExtra("modelid",pojo.get(position).getModelid());
                        startActivity(intent);
                    }
                }

            }
        });
    }
}
