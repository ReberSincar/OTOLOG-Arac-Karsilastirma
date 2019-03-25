package com.rebersincar.otologg.Activitys.AdminPanel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rebersincar.otologg.Activitys.CompareModel;
import com.rebersincar.otologg.Activitys.MainActivity;
import com.rebersincar.otologg.Models.ComparePojo;
import com.rebersincar.otologg.Models.MarkaPojo;
import com.rebersincar.otologg.Models.ModelDuzenlePojo;
import com.rebersincar.otologg.Models.ModelEklePojo;
import com.rebersincar.otologg.Models.ModelPojo;
import com.rebersincar.otologg.R;
import com.rebersincar.otologg.RestApi.ManagerAll;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelDuzenle extends AppCompatActivity {

    private Spinner yakit,vites;
    private EditText modelAd,modelYil,yakitHacmi,vitesKademesi,motor,silindir,koltuk,kapi,agirlik,hiz,fiyat;
    private Button duzenleBtn;
    private TextView textMarka;
    private ImageView logo;

    ComparePojo model;

    private String[] vitesArray = {"Otomatik","Yarı Otomatik","Manuel"};
    private int[] vitesIdArray = {1,2,3};
    private String[] yakitArray = {"Benzin","Dizel","Otogaz+Benzin","Elektrik+Benzin","Elektrik"};
    private int[] yakitIdArray = {1,2,3,4,5};
    private Bundle extras;

    private int markaid,modelid,yakitid,vitesid,yakitHacmiStr,vitesKademesiStr,silindirStr,koltukStr,kapiStr,agirlikStr,hizStr,fiyatStr;
    String modelAdStr,modelYilStr,logoUrl,puan;
    Float motorStr;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_duzenle);

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
        markaid = Integer.valueOf(extras.getString("markaid"));
        modelid = Integer.valueOf(extras.getString("modelid"));
        logoUrl = extras.getString("logo");

        Call<ComparePojo> requestModel = ManagerAll.getInstance().compare(markaid,modelid);
        Log.i("deneme",""+markaid+" "+modelid);
        requestModel.enqueue(new Callback<ComparePojo>() {
            @Override
            public void onResponse(Call<ComparePojo> call, Response<ComparePojo> response) {
                if (response.isSuccessful())
                {
                    model = response.body();
                    Log.i("deneme",model.toString());
                    ViewYukle();
                }
            }

            @Override
            public void onFailure(Call<ComparePojo> call, Throwable t) {
                Toast.makeText(ModelDuzenle.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void ViewYukle()
    {
        textMarka.setText(model.getMrkad());
        String url = "http://otolog.atwebpages.com/"+logoUrl;
        Picasso.with(getApplicationContext()).load(url).resize(50,50).into(logo);
        modelAd.setText(model.getMdlad());
        modelYil.setText(model.getMdlyil());
        yakitHacmi.setText(model.getYktdepohacmi());
        vitesKademesi.setText(model.getVtskademe());
        motor.setText(model.getMtrgucu());
        silindir.setText(model.getSlndrsayisi());
        koltuk.setText(model.getKltksayisi());
        kapi.setText(model.getKapisayisi());
        agirlik.setText(model.getAgrlk());
        hiz.setText(model.getHz());
        fiyat.setText(model.getFyt());
        puan = model.getPoint();
    }

    public void Define()
    {
        //Spinners
        yakit = findViewById(R.id.modelDuzenleYakitTuru);
        vites = findViewById(R.id.modelDuzenleVites);
        //EditTexts
        modelAd = findViewById(R.id.modelDuzenleAd);
        modelYil = findViewById(R.id.modelDuzenleYil);
        yakitHacmi = findViewById(R.id.modelDuzenleYakitDepoHacmi);
        vitesKademesi = findViewById(R.id.modelDuzenleVitesKademe);
        motor = findViewById(R.id.modelDuzenleMotorGucu);
        silindir = findViewById(R.id.modelDuzenleSilindirSayisi);
        koltuk = findViewById(R.id.modelDuzenleKoltukSayisi);
        kapi = findViewById(R.id.modelDuzenleKapiSayisi);
        agirlik = findViewById(R.id.modelDuzenleAgirlik);
        hiz = findViewById(R.id.modelDuzenleHiz);
        fiyat = findViewById(R.id.modelDuzenleFiyat);
        //Button
        duzenleBtn = findViewById(R.id.modelDuzenleBtn);
        //TextView
        textMarka = findViewById(R.id.textDuzenleMarka);
        //ImageView
        logo = findViewById(R.id.modelDuzenleImg);
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
        duzenleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pd = new ProgressDialog(ModelDuzenle.this);
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

                Call<ModelDuzenlePojo> requestModelDuzenle = ManagerAll.getInstance().modelduzenle(
                        markaid,
                        modelid,
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
                        fiyatStr,
                        Integer.valueOf(puan));

                requestModelDuzenle.enqueue(new Callback<ModelDuzenlePojo>() {
                    @Override
                    public void onResponse(Call<ModelDuzenlePojo> call, Response<ModelDuzenlePojo> response) {
                        if (response.isSuccessful())
                        {
                            Toast.makeText(ModelDuzenle.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),ModelOperations.class);
                            pd.cancel();
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(ModelDuzenle.this, "Bir Sorun Oluştu", Toast.LENGTH_SHORT).show();
                            pd.cancel();
                        }

                    }

                    @Override
                    public void onFailure(Call<ModelDuzenlePojo> call, Throwable t) {
                        Toast.makeText(ModelDuzenle.this, "Fail", Toast.LENGTH_SHORT).show();
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
