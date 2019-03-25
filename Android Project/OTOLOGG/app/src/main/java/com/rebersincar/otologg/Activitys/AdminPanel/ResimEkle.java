package com.rebersincar.otologg.Activitys.AdminPanel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rebersincar.otologg.Activitys.MainActivity;
import com.rebersincar.otologg.Adapters.ResimAdapter;
import com.rebersincar.otologg.Models.ResimEklePojo;
import com.rebersincar.otologg.Models.ResimPojo;
import com.rebersincar.otologg.R;
import com.rebersincar.otologg.RestApi.ManagerAll;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResimEkle extends AppCompatActivity {

    ListView liste;
    ImageView logo,resim;
    TextView marka,model;
    Button yukle;

    ProgressDialog pd;

    List<ResimPojo> pojo;

    ResimAdapter resimAdapter;

    Bitmap bitmap;

    Bundle extras;

    String markaAd,modelAd,logoLink;

    boolean resimdurum = false;

    int markaId,modelId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resim_ekle);

        define();
        ListYukle();
        selectImage();
        Ekle();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),ResimOperations.class);
        startActivity(intent);
    }

    public void define()
    {
        liste = findViewById(R.id.resimEkleList);
        logo = findViewById(R.id.resimEkleMarkaLogo);
        marka = findViewById(R.id.resimEkleMarka);
        model = findViewById(R.id.resimEkleModel);
        resim = findViewById(R.id.resimEkleImg);
        yukle = findViewById(R.id.resimEkleBtn);

        extras = getIntent().getExtras();

        markaId = Integer.valueOf(extras.getString("markaid"));
        modelId = Integer.valueOf(extras.getString("modelid"));
        markaAd = extras.getString("markaad");
        modelAd = extras.getString("modelad");
        logoLink = extras.getString("logo");
        marka.setText(markaAd);
        model.setText(modelAd);

        Log.i("deneme", markaId+" "+modelId);

        String url ="http://otolog.atwebpages.com/"+logoLink;
        Picasso.with(getApplicationContext()).load(url).resize(50,50).into(logo);
    }

    public void ListYukle()
    {
        Call<List<ResimPojo>> requestResim = ManagerAll.getInstance().resimler(markaId,modelId);
        requestResim.enqueue(new Callback<List<ResimPojo>>() {
            @Override
            public void onResponse(Call<List<ResimPojo>> call, Response<List<ResimPojo>> response) {
                pojo = response.body();
                resimAdapter = new ResimAdapter(pojo,getApplicationContext());
                liste.setAdapter(resimAdapter);
            }

            @Override
            public void onFailure(Call<List<ResimPojo>> call, Throwable t) {

            }
        });
    }

    public void Ekle()
    {
        yukle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (resimdurum == true)
                {
                    pd = new ProgressDialog(ResimEkle.this);
                    pd.setMessage("İşlem Gerçekleştiriliyor.Lütfen Bekleyiniz..");
                    pd.show();
                    Baglan();

                }
                else
                {
                    Toast.makeText(ResimEkle.this, "Resim Seçmediniz", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void Baglan()
    {
        final String resim = imageToString();

        Call<ResimEklePojo> request = ManagerAll.getInstance().resimekle(markaId,modelId,resim);
        request.enqueue(new Callback<ResimEklePojo>() {
            @Override
            public void onResponse(Call<ResimEklePojo> call, Response<ResimEklePojo> response) {
                if (response.isSuccessful())
                {
                    if (response.body().isTf())
                    {
                        Toast.makeText(ResimEkle.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),ResimEkle.class);
                        intent.putExtra("markaid",""+markaId);
                        intent.putExtra("modelid",""+modelId);
                        intent.putExtra("markaad",markaAd);
                        intent.putExtra("modelad",modelAd);
                        intent.putExtra("logo",logoLink);
                        pd.cancel();
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(ResimEkle.this, "Lütfen Bu Resimi Değiştirin", Toast.LENGTH_SHORT).show();
                        pd.cancel();
                    }

                }
                else
                {
                    Toast.makeText(ResimEkle.this, "Bir Hata Oluştu", Toast.LENGTH_SHORT).show();
                    pd.cancel();
                }
            }

            @Override
            public void onFailure(Call<ResimEklePojo> call, Throwable t) {
                Toast.makeText(ResimEkle.this, "Fail", Toast.LENGTH_SHORT).show();
                pd.cancel();
            }
        });

    }

    public void selectImage()
    {
        resim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent , 47);
            }
        });
    }

    protected void onActivityResult(int requestCode,int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==47 && resultCode==RESULT_OK && data != null)
        {
            Uri path = data.getData();

            try
            {
                resimdurum=true;
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                resim.setImageBitmap(bitmap);
                resim.setVisibility(View.VISIBLE);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public String imageToString()
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        String image = Base64.encodeToString(bytes,Base64.DEFAULT);
        return image;
    }
}
