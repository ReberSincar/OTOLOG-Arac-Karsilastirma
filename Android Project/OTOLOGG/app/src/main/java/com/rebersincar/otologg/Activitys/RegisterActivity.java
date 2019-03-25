package com.rebersincar.otologg.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rebersincar.otologg.Activitys.UserSettings.ChangePassword;
import com.rebersincar.otologg.Models.RegisterPojo;
import com.rebersincar.otologg.R;
import com.rebersincar.otologg.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    TextView toLogin;
    EditText ETad,ETsoyad,ETmail,ETsifre,ETsifre2;
    Button btnRegister;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        define();
        login();
        clickRegister();
    }

    public void define()
    {
        toLogin = findViewById(R.id.toLogin);
        ETad = findViewById(R.id.ad);
        ETsoyad = findViewById(R.id.soyad);
        ETmail = findViewById(R.id.mail);
        ETsifre = findViewById(R.id.sifre);
        ETsifre2 = findViewById(R.id.sifre2);
        btnRegister = findViewById(R.id.btnRegister);
    }
    public void login()
    {
        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void clickRegister()
    {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ad = ETad.getText().toString();
                String soyad = ETsoyad.getText().toString();
                String mail = ETmail.getText().toString();
                String sifre = ETsifre.getText().toString();
                String sifre2 = ETsifre2.getText().toString();

                if(mail.length()==0 || ad.length()==0 || soyad.length()==0 || sifre.length()==0 || sifre2.length()==0)
                    Toast.makeText(RegisterActivity.this, "Tüm alanları doldurun.", Toast.LENGTH_LONG).show();
                else if(!mail.contains("@"))
                    Toast.makeText(RegisterActivity.this, "Mail adresiniz mail formatına uymuyor", Toast.LENGTH_LONG).show();
                else if (!mail.contains(".com"))
                    Toast.makeText(RegisterActivity.this, "Mail adresiniz mail formatına uymuyor", Toast.LENGTH_LONG).show();
                else if(sifre.length()<6)
                    Toast.makeText(RegisterActivity.this, "Şifreniz en az 6 karakterden oluşmalı.", Toast.LENGTH_LONG).show();
                else if(!sifre2.equals(sifre))
                    Toast.makeText(RegisterActivity.this, "Girdiğiniz şifreler uyuşmuyor", Toast.LENGTH_LONG).show();
                else
                {

                    Register(ad,soyad,mail,sifre);
                }

            }
        });
    }
    public void Register(String ad, String soyad, String email, String sifre)
    {
        pd = new ProgressDialog(RegisterActivity.this);
        pd.setMessage("Üyelik Açılıyor.Lütfen Bekleyiniz..");
        pd.show();

        Call<RegisterPojo> request = ManagerAll.getInstance().register(ad,soyad,email,sifre);
        request.enqueue(new Callback<RegisterPojo>() {
            @Override
            public void onResponse(Call<RegisterPojo> call, Response<RegisterPojo> response) {
                if (response.isSuccessful())
                {
                    if (response.body().isTf())
                    {
                        Log.i("kayit",response.body().toString());
                        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                        Toast.makeText(RegisterActivity.this, "Kayit Olusturuldu", Toast.LENGTH_SHORT).show();
                        pd.cancel();
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "Bir Hata Oluştu", Toast.LENGTH_SHORT).show();
                        pd.cancel();
                    }
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Bir Hata Oluştu", Toast.LENGTH_SHORT).show();
                    pd.cancel();
                }

            }

            @Override
            public void onFailure(Call<RegisterPojo> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "fail", Toast.LENGTH_SHORT).show();
                pd.cancel();
            }
        });
    }
}
