package com.rebersincar.otologg.Activitys.AdminPanel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rebersincar.otologg.Activitys.YoneticiActivity;
import com.rebersincar.otologg.R;

public class ModelOperations extends AppCompatActivity {

    Button modelEkle,modelDuzenle,modelSil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_operations);

        modelEkle = findViewById(R.id.modelOpEkle);
        modelDuzenle = findViewById(R.id.modelOpDuzenle);
        modelSil = findViewById(R.id.modelOpSil);

        modelEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AdminMarka.class);
                intent.putExtra("islem","modelekle");
                startActivity(intent);
            }
        });
        modelDuzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AdminMarka.class);
                intent.putExtra("islem","modelduzenle");
                startActivity(intent);
            }
        });
        modelSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AdminMarka.class);
                intent.putExtra("islem","modelsil");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),YoneticiActivity.class);
        startActivity(intent);
    }
}
