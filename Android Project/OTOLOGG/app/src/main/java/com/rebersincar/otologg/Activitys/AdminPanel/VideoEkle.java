package com.rebersincar.otologg.Activitys.AdminPanel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rebersincar.otologg.Activitys.YoneticiActivity;
import com.rebersincar.otologg.Models.VideoEklePojo;
import com.rebersincar.otologg.R;
import com.rebersincar.otologg.RestApi.ManagerAll;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoEkle extends AppCompatActivity {

    EditText reklamText,incelemeText,kazaText;
    Button ekleBtn;
    TextView markamodel;
    ImageView markaLogo;

    private int markaid,modelid;
    private String markaAd,modelAd,logo;

    Bundle extras;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_ekle);

        reklamText = findViewById(R.id.reklamLink);
        incelemeText = findViewById(R.id.incelemeLink);
        kazaText = findViewById(R.id.kazaTestLink);

        markaLogo = findViewById(R.id.videoEkleMarkaImg);

        markamodel = findViewById(R.id.markamodelVideoEkle);

        ekleBtn = findViewById(R.id.videoEkleBtn);

        yukle();
        ekle();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),VideoOperations.class);
        startActivity(intent);
    }

    public void ekle()
    {
        ekleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] reklamLink = reklamText.getText().toString().split("=");
                String[] incelemeLink = incelemeText.getText().toString().split("=");
                String[] kazaTestLink = kazaText.getText().toString().split("=");

                if (reklamText.getText().toString().trim().equals("") || incelemeText.getText().toString().trim().equals("") || kazaText.getText().toString().trim().equals(""))
                {
                    Toast.makeText(VideoEkle.this, "Tüm Alanları Doldurun", Toast.LENGTH_SHORT).show();
                }
                else if(reklamText.getText().toString().trim().contains("youtube.com/watch?v=") &&
                        incelemeText.getText().toString().trim().contains("youtube.com/watch?v=") &&
                        kazaText.getText().toString().trim().contains("youtube.com/watch?v="))
                {
                    pd = new ProgressDialog(VideoEkle.this);
                    pd.setMessage("İşlem Gerçekleştiriliyor.Lütfen Bekleyiniz..");
                    pd.show();

                    Call<VideoEklePojo> requestVideo = ManagerAll.getInstance().videoekle(
                            markaid,
                            modelid,
                            reklamLink[1],
                            incelemeLink[1],
                            kazaTestLink[1]);
                    requestVideo.enqueue(new Callback<VideoEklePojo>() {
                        @Override
                        public void onResponse(Call<VideoEklePojo> call, Response<VideoEklePojo> response) {
                            if (response.isSuccessful())
                            {
                                if (response.body().isTf())
                                {
                                    Toast.makeText(VideoEkle.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),YoneticiActivity.class);
                                    pd.cancel();
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(VideoEkle.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                                    pd.cancel();
                                }

                            }
                            else
                                Toast.makeText(VideoEkle.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                            pd.cancel();
                        }

                        @Override
                        public void onFailure(Call<VideoEklePojo> call, Throwable t) {
                            Toast.makeText(VideoEkle.this, "Fail", Toast.LENGTH_SHORT).show();
                            pd.cancel();
                        }
                    });
                }
                else
                {
                    Toast.makeText(VideoEkle.this, "Sadece Youtube Linki Girin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void yukle()
    {
        extras = getIntent().getExtras();
        markaid = Integer.valueOf(extras.getString("markaid"));
        modelid = Integer.valueOf(extras.getString("modelid"));
        markaAd = extras.getString("markaad");
        modelAd = extras.getString("modelad");
        logo = extras.getString("logo");

        markamodel.setText(markaAd+"  "+modelAd);
        String url ="http://otolog.atwebpages.com/"+logo;
        Picasso.with(getApplicationContext()).load(url).resize(50,50).into(markaLogo);
    }
}
