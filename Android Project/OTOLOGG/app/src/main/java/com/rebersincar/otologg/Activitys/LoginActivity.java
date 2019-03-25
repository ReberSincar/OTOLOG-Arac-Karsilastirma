package com.rebersincar.otologg.Activitys;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rebersincar.otologg.Activitys.UserSettings.ChangePassword;
import com.rebersincar.otologg.Models.LoginPojo;
import com.rebersincar.otologg.R;
import com.rebersincar.otologg.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText userMail,userPass;
    Button btnSingin;
    TextView toRegister;
    ProgressDialog pd;
    public static SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        spGiris();
        define();
        click();
        register();
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

    public void register()
    {
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void spGiris()
    {
        sharedPreferences = getApplicationContext().getSharedPreferences("giris",0);
        if (sharedPreferences.getString("uye_admin",null) != null  && sharedPreferences.getString("uye_mail",null) != null)
        {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }

    }
    public void define()
    {
        userMail = findViewById(R.id.userMail);
        userPass = findViewById(R.id.UserPass);
        btnSingin = findViewById(R.id.btnSignIn);
        toRegister = findViewById(R.id.toRegister);
    }

    public void click()
    {
        btnSingin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = userMail.getText().toString();
                String password = userPass.getText().toString();
                if(mail.length()==0)
                    Toast.makeText(LoginActivity.this, "Mail adresinizi girin", Toast.LENGTH_LONG).show();
                else if(!mail.contains("@"))
                    Toast.makeText(LoginActivity.this, "Mail adresiniz mail formatına uymuyor", Toast.LENGTH_LONG).show();
                else if (!mail.contains(".com"))
                    Toast.makeText(LoginActivity.this, "Mail adresiniz mail formatına uymuyor", Toast.LENGTH_LONG).show();
                else if(password.length()<6)
                    Toast.makeText(LoginActivity.this, "Şifreniz en az 6 karakter olmalı", Toast.LENGTH_LONG).show();
                else {
                    logIn(mail, password);
                }

            }
        });
    }

    public void logIn(String mail,String password)
    {
        pd = new ProgressDialog(LoginActivity.this);
        pd.setMessage("Giriş Yapılıyor.Lütfen Bekleyiniz..");
        pd.show();

        Call<LoginPojo> request = ManagerAll.getInstance().login(mail,password);
        request.enqueue(new Callback<LoginPojo>() {
            @Override
            public void onResponse(Call<LoginPojo> call, Response<LoginPojo> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getEmail() == null)
                    {
                        Toast.makeText(LoginActivity.this, "Hatali Kullanıcı Adı veya Şifre", Toast.LENGTH_SHORT).show();
                        pd.cancel();
                    }
                    else
                    {
                        int uye_ID = response.body().getId();
                        String uye_Admin = response.body().getAdmin();
                        String uye_Mail = response.body().getEmail();
                        sharedPreferences = getApplicationContext().getSharedPreferences("giris",0);
                        SharedPreferences.Editor  editor = sharedPreferences.edit();
                        editor.putInt("uye_id",uye_ID);
                        editor.putString("uye_admin",uye_Admin);
                        editor.putString("uye_mail",uye_Mail);
                        editor.commit();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        pd.cancel();
                        startActivity(intent);
                    }

                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Bir Hata Oluştu", Toast.LENGTH_SHORT).show();
                    pd.cancel();
                }

            }

            @Override
            public void onFailure(Call<LoginPojo> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "fail", Toast.LENGTH_SHORT).show();
                pd.cancel();

            }
        });
    }
}
