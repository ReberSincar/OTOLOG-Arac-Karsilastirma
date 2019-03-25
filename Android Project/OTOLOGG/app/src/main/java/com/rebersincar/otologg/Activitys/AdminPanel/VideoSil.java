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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rebersincar.otologg.Adapters.ModelAdapter;
import com.rebersincar.otologg.Models.ModelPojo;
import com.rebersincar.otologg.Models.ModelSilPojo;
import com.rebersincar.otologg.Models.VideoSilPojo;
import com.rebersincar.otologg.R;
import com.rebersincar.otologg.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoSil extends AppCompatActivity {

    ListView listView;
    ModelAdapter adapter;
    List<ModelPojo> pojo;
    Bundle extras;

    private int markaid,modelid;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_sil);
        listView = findViewById(R.id.videoSilList);
        listYukle();
        Sil();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),VideoOperations.class);
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

                modelid = Integer.valueOf(pojo.get(position).getModelid());
                Onay(markaid,modelid);

            }
        });
    }

    public void Onay(final int markaId, final int modelId)
    {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Video Sil")
                .setMessage("Bu modelin videolarını kaldırmak istediğinize emin misiniz?")
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Baglan(markaId,modelId);
                    }
                }).setNegativeButton("Hayır", null).show();
    }

    public void Baglan(int markaId, int modelId) {

        pd = new ProgressDialog(VideoSil.this);
        pd.setMessage("İşlem Gerçekleştiriliyor.Lütfen Bekleyiniz..");
        pd.show();

        Call<VideoSilPojo> request = ManagerAll.getInstance().videosil(markaId, modelId);
        request.enqueue(new Callback<VideoSilPojo>() {
            @Override
            public void onResponse(Call<VideoSilPojo> call, Response<VideoSilPojo> response) {
                if (response.isSuccessful()) {
                    if (response.body().isTf()) {
                        Toast.makeText(VideoSil.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),VideoOperations.class);
                        pd.cancel();
                        startActivity(intent);
                    } else {
                        Toast.makeText(VideoSil.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                        pd.cancel();
                    }

                } else {
                    Toast.makeText(VideoSil.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                    pd.cancel();
                }

            }

            @Override
            public void onFailure(Call<VideoSilPojo> call, Throwable t) {
                Toast.makeText(VideoSil.this, "Fail", Toast.LENGTH_SHORT).show();
                pd.cancel();
            }
        });
    }
}
