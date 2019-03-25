package com.rebersincar.otologg.Activitys.UserSettings;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rebersincar.otologg.Activitys.AdminPanel.ResimEkle;
import com.rebersincar.otologg.Activitys.AdminPanel.VideoOperations;
import com.rebersincar.otologg.Activitys.LoginActivity;
import com.rebersincar.otologg.Models.PasswordChangePojo;
import com.rebersincar.otologg.Models.PasswordPojo;
import com.rebersincar.otologg.R;
import com.rebersincar.otologg.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {

    EditText oldpw,newpw,newpw2;
    Button pwBtn;
    ProgressDialog pd;
    int userId;
    String userPassword,nw,nw2,old;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        oldpw = findViewById(R.id.oldPassword);
        newpw = findViewById(R.id.newPassword);
        newpw2 = findViewById(R.id.newPassword2);

        pwBtn = findViewById(R.id.passwordBtn);

        userId = LoginActivity.sharedPreferences.getInt("uye_id",0);

        Click();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),UserOperations.class);
        startActivity(intent);
    }

    public void Click()
    {
        pwBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                old = oldpw.getText().toString();
                nw = newpw.getText().toString();
                nw2 = newpw2.getText().toString();

                if(old.equals("") || nw.equals("") || nw2.equals(""))
                {
                    Toast.makeText(ChangePassword.this, "Tüm Alanları Doldurun", Toast.LENGTH_SHORT).show();
                }
                else if(old.length()<6 && nw.length()<6 && nw2.length()<6)
                {
                    Toast.makeText(ChangePassword.this, "Şifreleriniz 6 Karakterden Daha Az Olamaz", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Call<PasswordPojo> requestKontrol = ManagerAll.getInstance().sifrekontrol(userId);
                    requestKontrol.enqueue(new Callback<PasswordPojo>() {
                        @Override
                        public void onResponse(Call<PasswordPojo> call, Response<PasswordPojo> response) {

                            if (response.body().isTf())
                            {
                                userPassword = response.body().getPassword();

                                if (userPassword.equals(old))
                                {
                                    if(nw.equals(nw2))
                                    {
                                        Degistir(nw);
                                    }
                                    else
                                    {
                                        Toast.makeText(ChangePassword.this, "Yeni Şifreleriniz Uyuşmuyor", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else
                                {
                                    Toast.makeText(ChangePassword.this, "Geçerli Şifreniz Yalnış", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<PasswordPojo> call, Throwable t) {
                            Log.i("deneme","fail");
                        }
                    });
                }
            }
        });
    }


    public void Degistir(String password)
    {
        pd = new ProgressDialog(ChangePassword.this);
        pd.setMessage("İşlem Gerçekleştiriliyor.Lütfen Bekleyiniz..");
        pd.show();

        Call<PasswordChangePojo> requestPw = ManagerAll.getInstance().sifredegistir(userId,password);
        requestPw.enqueue(new Callback<PasswordChangePojo>() {
            @Override
            public void onResponse(Call<PasswordChangePojo> call, Response<PasswordChangePojo> response) {

                if (response.body().isTf())
                {
                    Toast.makeText(ChangePassword.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),UserOperations.class);
                    pd.cancel();
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(ChangePassword.this, "Bir Hata Oluştu", Toast.LENGTH_SHORT).show();
                    pd.cancel();
                }



            }

            @Override
            public void onFailure(Call<PasswordChangePojo> call, Throwable t) {
                Toast.makeText(ChangePassword.this, "Fail", Toast.LENGTH_SHORT).show();
                pd.cancel();
            }
        });
    }
}
