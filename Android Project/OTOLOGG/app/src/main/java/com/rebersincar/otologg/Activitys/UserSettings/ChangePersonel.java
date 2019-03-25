package com.rebersincar.otologg.Activitys.UserSettings;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rebersincar.otologg.Activitys.LoginActivity;
import com.rebersincar.otologg.Models.BilgiChangePojo;
import com.rebersincar.otologg.Models.Bilgiler;
import com.rebersincar.otologg.R;
import com.rebersincar.otologg.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePersonel extends AppCompatActivity {

    EditText adText,soyadText,mailText,parola;
    Button bilgiDegis;
    ProgressDialog pd;
    int userId;

    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_personel);

        adText = findViewById(R.id.bilgiDegisAd);
        soyadText = findViewById(R.id.bilgiDegisSoyad);
        mailText = findViewById(R.id.bilgiDegisMail);
        parola = findViewById(R.id.bilgiDegisPass);
        bilgiDegis = findViewById(R.id.bilgiBtn);

        userId = LoginActivity.sharedPreferences.getInt("uye_id",0);

        Yukle();
        Degistir();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),UserOperations.class);
        startActivity(intent);
    }

    public void Yukle()
    {
        Call<Bilgiler> requestBilgi = ManagerAll.getInstance().bilgiler(userId);
        requestBilgi.enqueue(new Callback<Bilgiler>() {
            @Override
            public void onResponse(Call<Bilgiler> call, Response<Bilgiler> response) {
                if (response.body().isTf())
                {
                    adText.setText(response.body().getAd());
                    soyadText.setText(response.body().getSoyad());
                    mailText.setText(response.body().getMail());
                    password = response.body().getPassword();
                }
            }

            @Override
            public void onFailure(Call<Bilgiler> call, Throwable t) {

            }
        });
    }

    public void Degistir()
    {
        bilgiDegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adText.getText().toString().equals("") || soyadText.getText().toString().equals("") || mailText.getText().toString().equals(""))
                {
                    Toast.makeText(ChangePersonel.this, "Tüm Alanları Doldurun", Toast.LENGTH_SHORT).show();
                }
                else if(parola.getText().toString().length()<6)
                {
                    Toast.makeText(ChangePersonel.this, "Şifreniz 6 Karakterden Küçük Olamaz", Toast.LENGTH_SHORT).show();
                }
                else if (password.equals(parola.getText().toString()))
                {
                    pd = new ProgressDialog(ChangePersonel.this);
                    pd.setMessage("İşlem Gerçekleştiriliyor.Lütfen Bekleyiniz..");
                    pd.show();

                    Call<BilgiChangePojo> requestBilgiDegistir = ManagerAll.getInstance().bilgidegistir(
                            userId,
                            adText.getText().toString(),
                            soyadText.getText().toString(),
                            mailText.getText().toString());

                    requestBilgiDegistir.enqueue(new Callback<BilgiChangePojo>() {
                        @Override
                        public void onResponse(Call<BilgiChangePojo> call, Response<BilgiChangePojo> response) {
                            if (response.body().isTf())
                            {
                                Toast.makeText(ChangePersonel.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),UserOperations.class);
                                pd.cancel();
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(ChangePersonel.this, "Bir Sorun Oluştu", Toast.LENGTH_SHORT).show();
                                pd.cancel();
                            }
                        }

                        @Override
                        public void onFailure(Call<BilgiChangePojo> call, Throwable t) {
                            Toast.makeText(ChangePersonel.this, "Fail", Toast.LENGTH_SHORT).show();
                            pd.cancel();
                        }
                    });
                }
                else
                {
                    Toast.makeText(ChangePersonel.this, "Şifrenizi Yanlış Girdiniz", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
