package com.rebersincar.otologg.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rebersincar.otologg.Activitys.AdminPanel.AdminMarka;
import com.rebersincar.otologg.Activitys.AdminPanel.MarkaEkle;
import com.rebersincar.otologg.Activitys.AdminPanel.MarkaOperations;
import com.rebersincar.otologg.Activitys.AdminPanel.MarkaSil;
import com.rebersincar.otologg.Activitys.AdminPanel.ModelEkle;
import com.rebersincar.otologg.Activitys.AdminPanel.ModelOperations;
import com.rebersincar.otologg.Activitys.AdminPanel.ResimOperations;
import com.rebersincar.otologg.Activitys.AdminPanel.VideoOperations;
import com.rebersincar.otologg.R;

public class YoneticiActivity extends AppCompatActivity {

    Button marka,model,resim,video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yonetici);

        marka = findViewById(R.id.markaIslemleri);
        model = findViewById(R.id.modelIslemleri);
        resim = findViewById(R.id.resimIslemleri);
        video = findViewById(R.id.videoIslemleri);

        marka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MarkaOperations.class);
                startActivity(intent);
            }
        });
        model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ModelOperations.class);
                startActivity(intent);
            }
        });
        resim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ResimOperations.class);
                startActivity(intent);
            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),VideoOperations.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}
