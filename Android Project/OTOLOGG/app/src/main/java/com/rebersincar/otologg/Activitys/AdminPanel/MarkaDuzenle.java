package com.rebersincar.otologg.Activitys.AdminPanel;

import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rebersincar.otologg.Activitys.YoneticiActivity;
import com.rebersincar.otologg.Models.MarkaDuzenlePojo;
import com.rebersincar.otologg.Models.MarkaEklePojo;
import com.rebersincar.otologg.R;
import com.rebersincar.otologg.RestApi.ManagerAll;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarkaDuzenle extends AppCompatActivity {

    EditText markaAd;
    ImageView logo;
    Button onay;
    Bitmap bitmap;
    String marka;
    String oldLogo;
    String newLogo;
    int id;
    boolean resimDurum=false;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marka_duzenle);

        markaAd = findViewById(R.id.markaDuzenleAd);
        logo = findViewById(R.id.markaDuzenleLogo);
        onay = findViewById(R.id.markaDuzenleBtn);

        Bundle extras = getIntent().getExtras();
        marka = extras.getString("marka");
        oldLogo = extras.getString("logo");
        id = extras.getInt("id");
        markaAd.setText(marka);
        String url ="http://otolog.atwebpages.com/"+oldLogo;
        Picasso.with(getApplicationContext()).load(url).resize(200,200).into(logo);

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

                marka= markaAd.getText().toString();


                if (marka.equals(""))
                {
                    Toast.makeText(MarkaDuzenle.this, "Marka Adını Boş Bırakamazsınız", Toast.LENGTH_SHORT).show();
                }
                else if (resimDurum==true)
                {
                    pd = new ProgressDialog(MarkaDuzenle.this);
                    pd.setMessage("İşlem Gerçekleştiriliyor.Lütfen Bekleyiniz..");
                    pd.show();

                    newLogo = imageToString();
                    Call<MarkaDuzenlePojo> request = ManagerAll.getInstance().markaduzenle(id,marka,newLogo,oldLogo);
                    request.enqueue(new Callback<MarkaDuzenlePojo>() {
                        @Override
                        public void onResponse(Call<MarkaDuzenlePojo> call, Response<MarkaDuzenlePojo> response) {
                            if (response.isSuccessful())
                            {
                                Toast.makeText(MarkaDuzenle.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                                if (response.body().isTf())
                                {
                                    Intent intent = new Intent(getApplicationContext(),YoneticiActivity.class);
                                    pd.cancel();
                                    startActivity(intent);
                                }
                            }
                            else
                            {
                                Toast.makeText(MarkaDuzenle.this, "Bir Hata Oluştu", Toast.LENGTH_SHORT).show();
                                pd.cancel();
                            }
                        }

                        @Override
                        public void onFailure(Call<MarkaDuzenlePojo> call, Throwable t) {
                            Toast.makeText(MarkaDuzenle.this, "Fail", Toast.LENGTH_SHORT).show();
                            pd.cancel();
                        }
                    });
                }
                else
                {
                    Toast.makeText(MarkaDuzenle.this, "Logoyu Değiştirmediniz", Toast.LENGTH_SHORT).show();
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
                resimDurum=true;
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
