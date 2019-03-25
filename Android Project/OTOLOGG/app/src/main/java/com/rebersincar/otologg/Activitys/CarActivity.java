package com.rebersincar.otologg.Activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.rebersincar.otologg.Adapters.SliderAdapter;
import com.rebersincar.otologg.Models.ComparePojo;
import com.rebersincar.otologg.Models.PuanKontrolPojo;
import com.rebersincar.otologg.Models.PuanPojo;
import com.rebersincar.otologg.Models.SliderPojo;
import com.rebersincar.otologg.Models.VideoPojo;
import com.rebersincar.otologg.R;
import com.rebersincar.otologg.RestApi.ManagerAll;
import com.rebersincar.otologg.YoutubeConfig;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarActivity extends YouTubeBaseActivity {

    private TextView markaV,
            modelV,
            puanV,
            fiyatV,
            hizV,
            yilV,
            yakitV,
            yakitHacimV,
            vitesV,
            vitessayiV,
            motorV,
            silindirV,
            agirlikV,
            koltukV,
            kapiV;
    private YouTubePlayerView reklamVideo,incelemeVideo,kazatestVideo;
    private YouTubePlayer.OnInitializedListener reklamOnInitializedListener,incelemeOnInitializedListener,kazaOnInitializedListener;

    private ViewPager arabaResim;

    private ImageView plus,star1,star2,star3,star4,star5;

    private SliderAdapter sliderAdapter;

    private CircleIndicator circleIndicator;

    private List<SliderPojo> sliderPojos;

    private ComparePojo model;

    private int modelId,markaId,userId,fiyat,hiz,yil,yakithacim,viteskademe,silindir,agirlik,koltuk,kapi,puan,userPuan;
    private String markaad,modelad,yakit,vites,reklam,inceleme,kaza;
    private float motor;

    private static final String TAG = "CarActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        userId = LoginActivity.sharedPreferences.getInt("uye_id",0);

        define();
        yukle();
        getImages();
        videos();
        PuanKontrol();
        youtube();
        compare();
        PuanAyarla();
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
    public void define()
    {
        plus = findViewById(R.id.carplus);

        star1 = findViewById(R.id.yildiz1);
        star2 = findViewById(R.id.yildiz2);
        star3 = findViewById(R.id.yildiz3);
        star4 = findViewById(R.id.yildiz4);
        star5 = findViewById(R.id.yildiz5);

        reklamVideo = findViewById(R.id.reklamView);
        incelemeVideo = findViewById(R.id.incelemeView);
        kazatestVideo = findViewById(R.id.kazaView);

        markaV = findViewById(R.id.arabaMarkaOzellik);
        modelV = findViewById(R.id.arabaModelOzellik);
        puanV = findViewById(R.id.puanText);
        fiyatV = findViewById(R.id.ucretText);
        hizV = findViewById(R.id.arabaHizOzellik);
        yilV = findViewById(R.id.arabaModelYilOzellik);
        yakitV = findViewById(R.id.arabaYakitOzellik);
        yakitHacimV = findViewById(R.id.arabaYakitHacmiOzellik);
        vitesV = findViewById(R.id.arabaVitesOzellik);
        vitessayiV = findViewById(R.id.arabaVitesKademeOzellik);
        motorV = findViewById(R.id.arabaMotorOzellik);
        silindirV = findViewById(R.id.arabaSilindirOzellik);
        agirlikV = findViewById(R.id.arabaAgirlikOzellik);
        koltukV = findViewById(R.id.arabaKoltukOzellik);
        kapiV = findViewById(R.id.arabaKoltukOzellik);

        arabaResim = (ViewPager) findViewById(R.id.carImages);
        circleIndicator = findViewById(R.id.sliderCircle);

    }

    public void PuanKontrol()
    {
        Call<PuanKontrolPojo> requestKontrol = ManagerAll.getInstance().puankontrol(userId,markaId,modelId);
        requestKontrol.enqueue(new Callback<PuanKontrolPojo>() {
            @Override
            public void onResponse(Call<PuanKontrolPojo> call, Response<PuanKontrolPojo> response) {
                if (response.isSuccessful())
                {
                    if (response.body().isTf())
                    {
                        int gelenPuan = response.body().getPuan();
                        if (gelenPuan==20)
                        {
                            star1.setImageResource(R.drawable.star2);
                        }
                        else if (gelenPuan==40)
                        {
                            star1.setImageResource(R.drawable.star2);
                            star2.setImageResource(R.drawable.star2);
                        }
                        else if (gelenPuan==60)
                        {
                            star1.setImageResource(R.drawable.star2);
                            star2.setImageResource(R.drawable.star2);
                            star3.setImageResource(R.drawable.star2);
                        }
                        else if (gelenPuan==80)
                        {
                            star1.setImageResource(R.drawable.star2);
                            star2.setImageResource(R.drawable.star2);
                            star3.setImageResource(R.drawable.star2);
                            star4.setImageResource(R.drawable.star2);
                        }
                        else if (gelenPuan==100)
                        {
                            star1.setImageResource(R.drawable.star2);
                            star2.setImageResource(R.drawable.star2);
                            star3.setImageResource(R.drawable.star2);
                            star4.setImageResource(R.drawable.star2);
                            star5.setImageResource(R.drawable.star2);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<PuanKontrolPojo> call, Throwable t) {

            }
        });
    }

    public void YildizSondur()
    {
        star1.setImageResource(R.drawable.star1);
        star2.setImageResource(R.drawable.star1);
        star3.setImageResource(R.drawable.star1);
        star4.setImageResource(R.drawable.star1);
        star5.setImageResource(R.drawable.star1);
    }

    public void PuanAyarla()
    {
        star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userPuan=20;
                YildizSondur();
                PuanVer();
            }
        });
        star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userPuan=40;
                YildizSondur();
                PuanVer();
            }
        });
        star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userPuan=60;
                YildizSondur();
                PuanVer();
            }
        });
        star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userPuan=80;
                YildizSondur();
                PuanVer();
            }
        });
        star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userPuan=100;
                YildizSondur();
                PuanVer();
            }
        });
    }

    public void PuanVer()
    {
        Log.i("deneme",markaId+" "+modelId+" "+userId+" "+userPuan);
        Call<PuanPojo> requestPuan = ManagerAll.getInstance().puanver(userId,markaId,modelId,userPuan);
        requestPuan.enqueue(new Callback<PuanPojo>() {
            @Override
            public void onResponse(Call<PuanPojo> call, Response<PuanPojo> response) {
                if (response.isSuccessful())
                {
                    if (response.body().isTf())
                    {
                        if (userPuan==20)
                        {
                            star1.setImageResource(R.drawable.star2);
                        }
                        else if (userPuan==40)
                        {
                            star1.setImageResource(R.drawable.star2);
                            star2.setImageResource(R.drawable.star2);
                        }
                        else if (userPuan==60)
                        {
                            star1.setImageResource(R.drawable.star2);
                            star2.setImageResource(R.drawable.star2);
                            star3.setImageResource(R.drawable.star2);
                        }
                        else if (userPuan==80)
                        {
                            star1.setImageResource(R.drawable.star2);
                            star2.setImageResource(R.drawable.star2);
                            star3.setImageResource(R.drawable.star2);
                            star4.setImageResource(R.drawable.star2);
                        }
                        else if (userPuan==100)
                        {
                            star1.setImageResource(R.drawable.star2);
                            star2.setImageResource(R.drawable.star2);
                            star3.setImageResource(R.drawable.star2);
                            star4.setImageResource(R.drawable.star2);
                            star5.setImageResource(R.drawable.star2);
                        }

                        puanV.setText("%"+response.body().getPuan());
                        Toast.makeText(CarActivity.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(CarActivity.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(CarActivity.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PuanPojo> call, Throwable t) {
                Toast.makeText(CarActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void yukle()
    {
        Bundle extras = getIntent().getExtras();
        modelId = Integer.valueOf(extras.getString("modelid"));
        markaId = Integer.valueOf(extras.getString("markaid"));

        Call<ComparePojo> request = ManagerAll.getInstance().compare(markaId,modelId);
        request.enqueue(new Callback<ComparePojo>() {
            @Override
            public void onResponse(Call<ComparePojo> call, Response<ComparePojo> response) {
                if (response.isSuccessful())
                {
                    model = response.body();
                    yukle2();
                }
                else
                {
                    Toast.makeText(CarActivity.this, "Else girdi", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ComparePojo> call, Throwable t) {
                Toast.makeText(CarActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void yukle2()
    {
        markaad = model.getMrkad();
        modelad = model.getMdlad();
        puan = Integer.valueOf(model.getPoint());
        fiyat = Integer.valueOf(model.getFyt());
        hiz = Integer.valueOf(model.getHz());
        yil = Integer.valueOf(model.getMdlyil());
        yakit = model.getYktid();
        yakithacim = Integer.valueOf(model.getYktdepohacmi());
        vites = model.getVtsid();
        viteskademe = Integer.valueOf(model.getVtskademe());
        motor = Float.valueOf(model.getMtrgucu());
        silindir = Integer.valueOf(model.getSlndrsayisi());
        agirlik = Integer.valueOf(model.getAgrlk());
        koltuk = Integer.valueOf(model.getKltksayisi());
        kapi = Integer.valueOf(model.getKapisayisi());

        markaV.setText(markaad);
        modelV.setText(modelad);
        puanV.setText(" %"+puan);
        fiyatV.setText(""+fiyat);
        hizV.setText(""+hiz);
        yilV.setText(""+yil);
        yakitV.setText(yakit);
        yakitHacimV.setText(""+yakithacim);
        vitesV.setText(vites);
        vitessayiV.setText(""+viteskademe);
        motorV.setText(""+motor);
        silindirV.setText(""+silindir);
        agirlikV.setText(""+agirlik);
        koltukV.setText(""+koltuk);
        kapiV.setText(""+kapi);
    }
    public void youtube()
    {

        reklamVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reklamVideo.initialize(YoutubeConfig.getAPI_KEY(),reklamOnInitializedListener);
            }
        });

        incelemeVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incelemeVideo.initialize(YoutubeConfig.getAPI_KEY(),incelemeOnInitializedListener);
            }
        });

        kazatestVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kazatestVideo.initialize(YoutubeConfig.getAPI_KEY(),kazaOnInitializedListener);
            }
        });
        reklamOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if (reklam == null)
                {
                    Toast.makeText(CarActivity.this, "Bu Modelin Reklam Videosu Bulunamadı", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    youTubePlayer.loadVideo(reklam);
                }

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        }
        };

        incelemeOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
        @Override
        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

            if (inceleme==null)
            {
                Toast.makeText(CarActivity.this, "Bu Modelin İnceleme Videosu Bulunamadı", Toast.LENGTH_SHORT).show();
            }
            else
            {
                youTubePlayer.loadVideo(inceleme);
            }

        }

        @Override
        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        }
    };

        kazaOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                if (kaza==null)
                {
                    Toast.makeText(CarActivity.this, "Bu Modelin Kaza Testi Videosu Bulunamadı", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    youTubePlayer.loadVideo(kaza);
                }

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

    }

    public void videos()
    {
        Call<VideoPojo> request = ManagerAll.getInstance().video(markaId,modelId);
        request.enqueue(new Callback<VideoPojo>() {
            @Override
            public void onResponse(Call<VideoPojo> call, Response<VideoPojo> response) {
                if (response.body().isTf())
                {
                    reklam = response.body().getReklam();
                    inceleme = response.body().getInceleme();
                    kaza = response.body().getKaza();
                }

            }

            @Override
            public void onFailure(Call<VideoPojo> call, Throwable t) {

            }
        });
    }

    public void getImages()
    {
        Call<List<SliderPojo>> requestImages = ManagerAll.getInstance().slider(markaId,modelId);
        requestImages.enqueue(new Callback<List<SliderPojo>>() {
            @Override
            public void onResponse(Call<List<SliderPojo>> call, Response<List<SliderPojo>> response) {

                sliderPojos = response.body();
                sliderAdapter = new SliderAdapter(sliderPojos,getApplicationContext());
                arabaResim.setAdapter(sliderAdapter);
                circleIndicator.setViewPager(arabaResim);
                circleIndicator.bringToFront();
            }

            @Override
            public void onFailure(Call<List<SliderPojo>> call, Throwable t) {

            }
        });
    }

    public void compare()
    {
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),CompareMarka.class);
                intent.putExtra("araba","2");
                intent.putExtra("markaid",markaId);
                intent.putExtra("modelid",modelId);
                startActivity(intent);
            }
        });
    }
}
