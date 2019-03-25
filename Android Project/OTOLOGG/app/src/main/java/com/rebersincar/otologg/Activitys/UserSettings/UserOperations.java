package com.rebersincar.otologg.Activitys.UserSettings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rebersincar.otologg.Activitys.MainActivity;
import com.rebersincar.otologg.Activitys.YoneticiActivity;
import com.rebersincar.otologg.R;

public class UserOperations extends AppCompatActivity {

    Button pwChange,bilgiChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_operations);

        pwChange = findViewById(R.id.userOpParolaDegis);
        bilgiChange = findViewById(R.id.userOpBilgiDegis);

        Yonlendir();
    }

    public void Yonlendir()
    {
        pwChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),ChangePassword.class);
                startActivity(intent);

            }
        });

        bilgiChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ChangePersonel.class);
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
