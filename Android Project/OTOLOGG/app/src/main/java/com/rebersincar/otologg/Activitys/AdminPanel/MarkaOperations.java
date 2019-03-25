package com.rebersincar.otologg.Activitys.AdminPanel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rebersincar.otologg.Activitys.YoneticiActivity;
import com.rebersincar.otologg.R;

public class MarkaOperations extends AppCompatActivity {

    Button markaEkle,markaDuzenle,markaSil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marka_operations);

        markaEkle = findViewById(R.id.markaOpEkle);
        markaDuzenle = findViewById(R.id.markaOpDuzenle);
        markaSil = findViewById(R.id.markaOpSil);

        markaEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MarkaEkle.class);
                startActivity(intent);
            }
        });
        markaDuzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AdminMarka.class);
                intent.putExtra("islem","markaduzenle");
                startActivity(intent);
            }
        });
        markaSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MarkaSil.class);
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
