package com.rebersincar.otologg.Activitys.AdminPanel;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.rebersincar.otologg.Adapters.ModelAdapter;
import com.rebersincar.otologg.Models.ModelPojo;
import com.rebersincar.otologg.Models.ModelSilPojo;
import com.rebersincar.otologg.R;
import com.rebersincar.otologg.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelSil extends AppCompatActivity {

    ListView listView;
    ModelAdapter adapter;
    List<ModelPojo> pojo;
    Bundle extras;

    ProgressDialog pd;

    private int markaid,modelid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_sil);
        listView = findViewById(R.id.modelSilList);
        listYukle();
        Sil();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),ModelOperations.class);
        startActivity(intent);
    }

    public void listYukle()
    {
        extras = getIntent().getExtras();
        markaid = extras.getInt("markaid");

        Call<List<ModelPojo>> requestModel = ManagerAll.getInstance().model(markaid);
        requestModel.enqueue(new Callback<List<ModelPojo>>() {
            @Override
            public void onResponse(Call<List<ModelPojo>> call, Response<List<ModelPojo>> response) {
                if (response.isSuccessful())
                {
                    pojo = response.body();
                    adapter = new ModelAdapter(pojo,getApplicationContext());
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<ModelPojo>> call, Throwable t) {

            }
        });
    }

    public void Sil()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (pojo.get(position).getAgirlik()==null)
                {
                    Toast.makeText(ModelSil.this, "Bu Markanın Modeli Yok", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    modelid = Integer.valueOf(pojo.get(position).getModelid());
                    Onay(markaid,modelid);
                }

            }
        });
    }

    public void Onay(final int markaId, final int modelId)
    {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Marka Sil")
                .setMessage("Bu Markayı kaldırmak istediğinize emin misiniz?")
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Baglan(markaId,modelId);
                        Intent intent = new Intent(getApplicationContext(),ModelOperations.class);
                        startActivity(intent);
                    }
                }).setNegativeButton("Hayır", null).show();
    }

    public void Baglan(int markaId, int modelId) {

        pd = new ProgressDialog(ModelSil.this);
        pd.setMessage("İşlem Gerçekleştiriliyor.Lütfen Bekleyiniz..");
        pd.show();

        Call<ModelSilPojo> request = ManagerAll.getInstance().modelsil(markaId, modelId);
        request.enqueue(new Callback<ModelSilPojo>() {
            @Override
            public void onResponse(Call<ModelSilPojo> call, Response<ModelSilPojo> response) {
                if (response.isSuccessful()) {
                    if (response.body().isTf()) {
                        Toast.makeText(ModelSil.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                        pd.cancel();
                    } else {
                        Toast.makeText(ModelSil.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                        pd.cancel();
                    }

                } else {
                    Toast.makeText(ModelSil.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                    pd.cancel();
                }

            }

            @Override
            public void onFailure(Call<ModelSilPojo> call, Throwable t) {
                Toast.makeText(ModelSil.this, "Fail", Toast.LENGTH_SHORT).show();
                pd.cancel();
            }
        });
    }
}
