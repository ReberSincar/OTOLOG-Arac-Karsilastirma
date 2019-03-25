package com.rebersincar.otologg.Activitys.AdminPanel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rebersincar.otologg.Activitys.YoneticiActivity;
import com.rebersincar.otologg.R;

public class VideoOperations extends AppCompatActivity {

    Button videoEkle,videoSil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_operations);

        videoEkle = findViewById(R.id.videoOpEkle);
        videoSil = findViewById(R.id.videoOpSil);

        git();

    }

    public void git()
    {
        videoEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),AdminMarka.class);
                intent.putExtra("islem","videoekle");
                startActivity(intent);
            }
        });

        videoSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),AdminMarka.class);
                intent.putExtra("islem","videosil");
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
