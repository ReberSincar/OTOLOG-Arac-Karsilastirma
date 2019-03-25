package com.rebersincar.otologg.Activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.rebersincar.otologg.Activitys.UserSettings.ChangePassword;
import com.rebersincar.otologg.Activitys.UserSettings.UserOperations;
import com.rebersincar.otologg.R;

public class MainActivity extends AppCompatActivity {
    ImageView ara,compare;
    Button yonetici,siralama,cikis,setting;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ara=findViewById(R.id.ara);
        compare=findViewById(R.id.compareImg);
        siralama=findViewById(R.id.Siralama);
        yonetici = findViewById(R.id.yoneticiIslemleri);
        cikis = findViewById(R.id.logOut);
        setting = findViewById(R.id.accountSettings);

        if(LoginActivity.sharedPreferences.getString("uye_admin",null).equals("0"))
        {
            yonetici.setVisibility(View.INVISIBLE);
        }
        click();
        Yonetici();
        LogOut();
        Ayarlar();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Çıkmak istediğinize emin misiniz?")
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }).setNegativeButton("Hayır", null).show();
    }

    public void Yonetici()
    {
        yonetici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),YoneticiActivity.class);
                startActivity(intent);
            }
        });
    }

    public void click()
    {
        ara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MarkaActivity.class);
                intent.putExtra("button","0");
                startActivity(intent);
            }
        });

        compare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MarkaActivity.class);
                intent.putExtra("button","1");
                startActivity(intent);
            }
        });

        siralama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SiralamaActivity.class);
                startActivity(intent);
            }
        });

    }

    public void LogOut()
    {
        cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = LoginActivity.sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void Ayarlar()
    {
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UserOperations.class);
                startActivity(intent);
            }
        });
    }
}
