package com.rebersincar.otologg.Activitys.AdminPanel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.rebersincar.otologg.Activitys.YoneticiActivity;
import com.rebersincar.otologg.Models.MarkaEklePojo;
import com.rebersincar.otologg.R;
import com.rebersincar.otologg.RestApi.ManagerAll;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarkaEkle extends AppCompatActivity {

    EditText markaAd;
    ImageView logo;
    Button onay;
    Bitmap bitmap;

    boolean resimDurum=false;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marka_ekle);

        markaAd = findViewById(R.id.markaEkleAd);
        logo = findViewById(R.id.markaEkleLogo);
        onay = findViewById(R.id.markaEkleBtn);

        selectImage();
        Gonder();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),MarkaOperations.class);
        startActivity(intent);
    }

    public void Gonder()
    {
        onay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ad = String.valueOf(markaAd.getText());

                if (ad.equals(""))
                {
                    Toast.makeText(MarkaEkle.this, "Marka Adını Boş Bırakamazsınız", Toast.LENGTH_SHORT).show();
                }
                else if (resimDurum==true)
                {
                    pd = new ProgressDialog(MarkaEkle.this);
                    pd.setMessage("İşlem Gerçekleştiriliyor.Lütfen Bekleyiniz..");
                    pd.show();
                    String logoImage = imageToString();
                    Call<MarkaEklePojo> request = ManagerAll.getInstance().markaekle(ad,logoImage);
                    request.enqueue(new Callback<MarkaEklePojo>() {
                        @Override
                        public void onResponse(Call<MarkaEklePojo> call, Response<MarkaEklePojo> response) {
                            if (response.isSuccessful())
                            {
                                Toast.makeText(MarkaEkle.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                                if (response.body().isTf())
                                {
                                    Intent intent = new Intent(getApplicationContext(),YoneticiActivity.class);
                                    pd.cancel();
                                    startActivity(intent);
                                }
                            }
                            else
                            {
                                Toast.makeText(MarkaEkle.this, "Bir Hata Oluştu", Toast.LENGTH_SHORT).show();
                                pd.cancel();
                            }
                        }

                        @Override
                        public void onFailure(Call<MarkaEklePojo> call, Throwable t) {
                            Toast.makeText(MarkaEkle.this, "Fail", Toast.LENGTH_SHORT).show();
                            pd.cancel();
                        }
                    });
                }
                else
                {
                    Toast.makeText(MarkaEkle.this, "Logo Seçin", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    public void selectImage()
    {
        logo.setOnClickListener(new View.OnClickListener() {
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
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                logo.setImageBitmap(bitmap);
                logo.setVisibility(View.VISIBLE);
                resimDurum = true;
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
