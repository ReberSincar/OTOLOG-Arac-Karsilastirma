package com.rebersincar.otologg.Activitys.AdminPanel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rebersincar.otologg.Activitys.YoneticiActivity;
import com.rebersincar.otologg.R;

public class ResimOperations extends AppCompatActivity {

    Button resimEkle,resimSil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resim_operations);

        resimEkle = findViewById(R.id.resimOpEkle);
        resimSil = findViewById(R.id.resimOpSil);

        git();
    }

    public void git()
    {
        resimEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AdminMarka.class);
                intent.putExtra("islem","resimekle");
                startActivity(intent);
            }
        });

        resimSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AdminMarka.class);
                intent.putExtra("islem","resimsil");
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
