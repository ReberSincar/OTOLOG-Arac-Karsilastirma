package com.rebersincar.otologg.Activitys.AdminPanel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rebersincar.otologg.Activitys.CarActivity;
import com.rebersincar.otologg.Activitys.MainActivity;
import com.rebersincar.otologg.Models.MarkaPojo;
import com.rebersincar.otologg.Models.ModelEklePojo;
import com.rebersincar.otologg.R;
import com.rebersincar.otologg.RestApi.ManagerAll;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelEkle extends AppCompatActivity {

    Spinner yakit,vites;
    EditText modelAd,modelYil,yakitHacmi,vitesKademesi,motor,silindir,koltuk,kapi,agirlik,hiz,fiyat;
    Button onayBtn;
    TextView textMarka;
    ImageView logo;

    String[] vitesArray = {"Otomatik","Yarı Otomatik","Manuel"};
    int[] vitesIdArray = {1,2,3};
    String[] yakitArray = {"Benzin","Dizel","Otogaz+Benzin","Elektrik+Benzin","Elektrik"};
    int[] yakitIdArray = {1,2,3,4,5};
    MarkaPojo[] markaArray;
    int[] markaIdArray;
    Bundle extras;

    int markaid,yakitid,vitesid,yakitHacmiStr,vitesKademesiStr,silindirStr,koltukStr,kapiStr,agirlikStr,hizStr,fiyatStr;
    String modelAdStr,modelYilStr;
    Float motorStr;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_ekle);

        Define();
        Adapters();
        extra();
        Cek();
        onay();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),ModelOperations.class);
        startActivity(intent);
    }

    public void extra()
    {
        extras = getIntent().getExtras();
        markaid = extras.getInt("id");
        String markaAd = extras.getString("marka");
        String logoPath = extras.getString("logo");
        textMarka.setText(markaAd);

        String url = "http://otolog.atwebpages.com/"+logoPath;
        Picasso.with(getApplicationContext()).load(url).resize(50,50).into(logo);
    }

    public void Define()
    {
        //Spinners
        yakit = findViewById(R.id.modelEkleYakitTuru);
        vites = findViewById(R.id.modelEkleVites);
        //EditTexts
        modelAd = findViewById(R.id.modelEkleAd);
        modelYil = findViewById(R.id.modelEkleYil);
        yakitHacmi = findViewById(R.id.modelEkleYakitDepoHacmi);
        vitesKademesi = findViewById(R.id.modelEkleVitesKademe);
        motor = findViewById(R.id.modelEkleMotorGucu);
        silindir = findViewById(R.id.modelEkleSilindirSayisi);
        koltuk = findViewById(R.id.modelEkleKoltukSayisi);
        kapi = findViewById(R.id.modelEkleKapiSayisi);
        agirlik = findViewById(R.id.modelEkleAgirlik);
        hiz = findViewById(R.id.modelEkleHiz);
        fiyat = findViewById(R.id.modelEkleFiyat);
        //Button
        onayBtn = findViewById(R.id.modelEkleBtn);
        //TextView
        textMarka = findViewById(R.id.textMarka);
        //ImageView
        logo = findViewById(R.id.modelEkleImg);
    }

    public void Adapters()
    {
        ArrayAdapter<String> vitesAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,vitesArray);
        vitesAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        vites.setAdapter(vitesAdapter);

        ArrayAdapter<String> yakitAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,yakitArray);
        yakitAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        yakit.setAdapter(yakitAdapter);


    }

    public void onay()
    {
        onayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pd = new ProgressDialog(ModelEkle.this);
                pd.setMessage("İşlem Gerçekleştiriliyor.Lütfen Bekleyiniz..");
                pd.show();

                modelAdStr = modelAd.getText().toString();
                modelYilStr = modelYil.getText().toString();
                yakitHacmiStr = Integer.valueOf(yakitHacmi.getText().toString());
                vitesKademesiStr = Integer.valueOf(vitesKademesi.getText().toString());
                motorStr = Float.valueOf(motor.getText().toString());
                silindirStr = Integer.valueOf(silindir.getText().toString());
                koltukStr = Integer.valueOf(koltuk.getText().toString());
                kapiStr = Integer.valueOf(kapi.getText().toString());
                agirlikStr = Integer.valueOf(agirlik.getText().toString());
                hizStr = Integer.valueOf(hiz.getText().toString());
                fiyatStr = Integer.valueOf(fiyat.getText().toString());

                Call<ModelEklePojo> requestModelEkle = ManagerAll.getInstance().modelekle(
                        markaid,
                        modelAdStr,
                        modelYilStr,
                        yakitid,
                        vitesid,
                        vitesKademesiStr,
                        motorStr,
                        silindirStr,
                        koltukStr,
                        kapiStr,
                        yakitHacmiStr,
                        agirlikStr,
                        hizStr,
                        fiyatStr);
                requestModelEkle.enqueue(new Callback<ModelEklePojo>() {
                    @Override
                    public void onResponse(Call<ModelEklePojo> call, Response<ModelEklePojo> response) {
                        if (response.isSuccessful())
                        {
                            if (response.body().isTf())
                            {
                                Toast.makeText(ModelEkle.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                pd.cancel();
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(ModelEkle.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                                pd.cancel();
                            }
                        }
                        else
                            Toast.makeText(ModelEkle.this, "Bir Sorun Oluştu", Toast.LENGTH_SHORT).show();
                        pd.cancel();
                    }

                    @Override
                    public void onFailure(Call<ModelEklePojo> call, Throwable t) {
                        Toast.makeText(ModelEkle.this, "Fail", Toast.LENGTH_SHORT).show();
                        pd.cancel();
                    }
                });
            }
        });
    }

    public void Cek()
    {
        vites.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                vitesid = vitesIdArray[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        yakit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                yakitid = yakitIdArray[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
