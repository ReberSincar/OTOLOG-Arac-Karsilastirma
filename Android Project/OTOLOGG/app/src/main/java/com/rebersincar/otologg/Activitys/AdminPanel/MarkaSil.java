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

import com.rebersincar.otologg.Adapters.MarkaAdapter;
import com.rebersincar.otologg.Models.MarkaPojo;
import com.rebersincar.otologg.Models.MarkaSilPojo;
import com.rebersincar.otologg.R;
import com.rebersincar.otologg.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarkaSil extends AppCompatActivity {

    ListView listView;
    MarkaAdapter markaAdapter;
    List<MarkaPojo> list;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marka_sil);
        listView = findViewById(R.id.markaSilList);
        listYukle();
        Sil();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),MarkaOperations.class);
        startActivity(intent);
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

                    markaAdapter = new MarkaAdapter(list,getApplicationContext(),MarkaSil.this);
                    listView.setAdapter(markaAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<MarkaPojo>> call, Throwable t) {
            }
        });
    }

    public void Sil()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Onay(list.get(position).getId());

            }
        });
    }

    public void Onay(final int markaId)
    {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Marka Sil")
                .setMessage("Bu Markayı kaldırmak istediğinize emin misiniz?")
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Baglan(markaId);
                        Intent intent = new Intent(getApplicationContext(),MarkaSil.class);
                        startActivity(intent);
                    }
                }).setNegativeButton("Hayır", null).show();
    }

    public void Baglan(int markaId)
    {
        pd = new ProgressDialog(MarkaSil.this);
        pd.setMessage("İşlem Gerçekleştiriliyor.Lütfen Bekleyiniz..");
        pd.show();
        Call<MarkaSilPojo> request = ManagerAll.getInstance().markasil(markaId);
        request.enqueue(new Callback<MarkaSilPojo>() {
            @Override
            public void onResponse(Call<MarkaSilPojo> call, Response<MarkaSilPojo> response) {
                if (response.isSuccessful())
                {
                    if (response.body().isTf())
                    {
                        Toast.makeText(MarkaSil.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                        pd.cancel();
                    }
                    else
                    {
                        Toast.makeText(MarkaSil.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                        pd.cancel();
                    }

                }
                else
                {
                    Toast.makeText(MarkaSil.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                    pd.cancel();
                }

            }

            @Override
            public void onFailure(Call<MarkaSilPojo> call, Throwable t) {
                Toast.makeText(MarkaSil.this, "Fail", Toast.LENGTH_SHORT).show();
                pd.cancel();
            }
        });
    }
}
