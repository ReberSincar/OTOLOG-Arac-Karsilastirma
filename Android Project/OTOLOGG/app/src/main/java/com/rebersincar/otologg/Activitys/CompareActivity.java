package com.rebersincar.otologg.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rebersincar.otologg.Adapters.SliderAdapter;
import com.rebersincar.otologg.Models.ComparePojo;
import com.rebersincar.otologg.Models.SliderPojo;
import com.rebersincar.otologg.R;
import com.rebersincar.otologg.RestApi.ManagerAll;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompareActivity extends AppCompatActivity {

    TextView markaV1,
            modelV1,
            puanV1,
            fiyatV1,
            hizV1,
            yilV1,
            yakitV1,
            yakitHacimV1,
            vitesV1,
            vitessayiV1,
            motorV1,
            silindirV1,
            agirlikV1,
            koltukV1,
            kapiV1;

    TextView markaV2,
            modelV2,
            puanV2,
            fiyatV2,
            hizV2,
            yilV2,
            yakitV2,
            yakitHacimV2,
            vitesV2,
            vitessayiV2,
            motorV2,
            silindirV2,
            agirlikV2,
            koltukV2,
            kapiV2;

    ImageView ekle1,ekle2;

    int modelId,markaId,puan,fiyat,hiz,yil,yakithacim,viteskademe,silindir,agirlik,koltuk,kapi;
    String markaad,model,yakit,vites;
    float motor;

    int modelId2,markaId2,puan2,fiyat2,hiz2,yil2,yakithacim2,viteskademe2,silindir2,agirlik2,koltuk2,kapi2;
    String markaad2,model2,yakit2,vites2;
    float motor2;

    int markaid,modelid,markaid2,modelid2;

    private ViewPager arabaResim1,arabaResim2;

    private SliderAdapter sliderAdapter;

    private CircleIndicator circleIndicator1,circleIndicator2;

    private List<SliderPojo> sliderPojos1,sliderPojos2;

    ComparePojo car1,car2;

    String where;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        define();
        yukle();
        ekle();
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    public void define()
    {
        {
            ekle1 = findViewById(R.id.ekle1);
            markaV1 = findViewById(R.id.arabaMarkaOzellik1);
            modelV1 = findViewById(R.id.arabaModelOzellik1);
            puanV1 = findViewById(R.id.puanText1);
            fiyatV1 = findViewById(R.id.fiyatText1);
            hizV1 = findViewById(R.id.arabaHizOzellik1);
            yilV1 = findViewById(R.id.arabaModelYilOzellik1);
            yakitV1 = findViewById(R.id.arabaYakitOzellik1);
            yakitHacimV1 = findViewById(R.id.arabaYakitHacmiOzellik1);
            vitesV1 = findViewById(R.id.arabaVitesOzellik1);
            vitessayiV1 = findViewById(R.id.arabaVitesKademeOzellik1);
            motorV1 = findViewById(R.id.arabaMotorOzellik1);
            silindirV1 = findViewById(R.id.arabaSilindirOzellik1);
            agirlikV1 = findViewById(R.id.arabaAgirlikOzellik1);
            koltukV1 = findViewById(R.id.arabaKoltukOzellik1);
            kapiV1 = findViewById(R.id.arabaKapiOzellik1);

            arabaResim1 = findViewById(R.id.viewPagerCar1);
            circleIndicator1 = findViewById(R.id.circleCar1);
        }

        {
            ekle2 = findViewById(R.id.ekle2);
            markaV2 = findViewById(R.id.arabaMarkaOzellik2);
            modelV2 = findViewById(R.id.arabaModelOzellik2);
            puanV2 = findViewById(R.id.puanText2);
            fiyatV2 = findViewById(R.id.fiyatText2);
            hizV2 = findViewById(R.id.arabaHizOzellik2);
            yilV2 = findViewById(R.id.arabaModelYilOzellik2);
            yakitV2 = findViewById(R.id.arabaYakitOzellik2);
            yakitHacimV2 = findViewById(R.id.arabaYakitHacmiOzellik2);
            vitesV2 = findViewById(R.id.arabaVitesOzellik2);
            vitessayiV2 = findViewById(R.id.arabaVitesKademeOzellik2);
            motorV2 = findViewById(R.id.arabaMotorOzellik2);
            silindirV2 = findViewById(R.id.arabaSilindirOzellik2);
            agirlikV2 = findViewById(R.id.arabaAgirlikOzellik2);
            koltukV2 = findViewById(R.id.arabaKoltukOzellik2);
            kapiV2 = findViewById(R.id.arabaKapiOzellik2);

            arabaResim2 = findViewById(R.id.viewPagerCar2);
            circleIndicator2 = findViewById(R.id.circleCar2);
        }

    }

    public void yukle()
    {
        Bundle extras = getIntent().getExtras();
        where = extras.getString("where");
        final String araba = extras.getString("araba");


        if (where.equals("normal"))
        {
            markaid = Integer.valueOf(extras.getString("markaid"));
            modelid = Integer.valueOf(extras.getString("modelid"));
            Call<ComparePojo> request1 = ManagerAll.getInstance().compare(markaid,modelid);
            request1.enqueue(new Callback<ComparePojo>() {
                @Override
                public void onResponse(Call<ComparePojo> call, Response<ComparePojo> response) {
                    car1 = response.body();
                    ekle_Car1();
                    getImagesCar1();
                }

                @Override
                public void onFailure(Call<ComparePojo> call, Throwable t) {

                }
            });
        }
        else
        {

            markaid = extras.getInt("markaid");
            modelid = extras.getInt("modelid");
            markaid2 = extras.getInt("markaid2");
            modelid2 = extras.getInt("modelid2");

            if (araba.equals("2"))
            {
                Call<ComparePojo> request2 = ManagerAll.getInstance().compare(markaid,modelid);
                request2.enqueue(new Callback<ComparePojo>() {
                    @Override
                    public void onResponse(Call<ComparePojo> call, Response<ComparePojo> response) {

                        if (response.isSuccessful())
                        {
                            car1 = response.body();
                            ekle_Car1();
                            getImagesCar1();
                        }

                    }

                    @Override
                    public void onFailure(Call<ComparePojo> call, Throwable t) {

                    }
                });

                Call<ComparePojo> request3 = ManagerAll.getInstance().compare(markaid2,modelid2);
                request3.enqueue(new Callback<ComparePojo>() {
                    @Override
                    public void onResponse(Call<ComparePojo> call, Response<ComparePojo> response) {

                        if (response.isSuccessful())
                        {
                            car2 = response.body();
                            ekle_Car2();
                            getImagesCar2();
                        }

                    }

                    @Override
                    public void onFailure(Call<ComparePojo> call, Throwable t) {

                    }
                });

            }
            else
            {
                Call<ComparePojo> request2 = ManagerAll.getInstance().compare(markaid2,modelid2);
                request2.enqueue(new Callback<ComparePojo>() {
                    @Override
                    public void onResponse(Call<ComparePojo> call, Response<ComparePojo> response) {

                        if (response.isSuccessful())
                        {
                            car1 = response.body();
                            ekle_Car1();
                            getImagesCar1();
                        }

                    }

                    @Override
                    public void onFailure(Call<ComparePojo> call, Throwable t) {

                    }
                });

                Call<ComparePojo> request3 = ManagerAll.getInstance().compare(markaid,modelid);
                request3.enqueue(new Callback<ComparePojo>() {
                    @Override
                    public void onResponse(Call<ComparePojo> call, Response<ComparePojo> response) {

                        if (response.isSuccessful())
                        {
                            car2 = response.body();
                            ekle_Car2();
                            getImagesCar2();
                        }

                    }

                    @Override
                    public void onFailure(Call<ComparePojo> call, Throwable t) {

                    }
                });
            }

        }
    }

    public void ekle_Car1()
    {
        modelId = Integer.valueOf(car1.getMdlid());
        markaId = Integer.valueOf(car1.getMrkid());
        markaad = car1.getMrkad();
        model = car1.getMdlad();
        puan = Integer.valueOf(car1.getPoint());
        fiyat = Integer.valueOf(car1.getFyt());
        hiz = Integer.valueOf(car1.getHz());
        yil = Integer.valueOf(car1.getMdlyil());
        yakit = car1.getYktid();
        yakithacim = Integer.valueOf(car1.getYktdepohacmi());
        vites = car1.getVtsid();
        viteskademe = Integer.valueOf(car1.getVtskademe());
        motor = Float.valueOf(car1.getMtrgucu());
        silindir = Integer.valueOf(car1.getSlndrsayisi());
        agirlik = Integer.valueOf(car1.getAgrlk());
        koltuk = Integer.valueOf(car1.getKltksayisi());
        kapi = Integer.valueOf(car1.getKapisayisi());


        markaV1.setText(markaad);
        modelV1.setText(model);
        puanV1.setText(" %"+puan);
        fiyatV1.setText(""+fiyat);
        hizV1.setText(""+hiz);
        yilV1.setText(""+yil);
        yakitV1.setText(yakit);
        yakitHacimV1.setText(""+yakithacim);
        vitesV1.setText(vites);
        vitessayiV1.setText(""+viteskademe);
        motorV1.setText(""+motor);
        silindirV1.setText(""+silindir);
        agirlikV1.setText(""+agirlik);
        koltukV1.setText(""+koltuk);
        kapiV1.setText(""+kapi);

        compare();
    }

    public void ekle_Car2()
    {
        modelId2 = Integer.valueOf(car2.getMdlid());
        markaId2 = Integer.valueOf(car2.getMrkid());
        markaad2 = car2.getMrkad();
        model2 = car2.getMdlad();
        puan2 = Integer.valueOf(car2.getPoint());
        fiyat2 = Integer.valueOf(car2.getFyt());
        hiz2 = Integer.valueOf(car2.getHz());
        yil2 = Integer.valueOf(car2.getMdlyil());
        yakit2 = car2.getYktid();
        yakithacim2 = Integer.valueOf(car2.getYktdepohacmi());
        vites2 = car2.getVtsid();
        viteskademe2 = Integer.valueOf(car2.getVtskademe());
        motor2 = Float.valueOf(car2.getMtrgucu());
        silindir2 = Integer.valueOf(car2.getSlndrsayisi());
        agirlik2 = Integer.valueOf(car2.getAgrlk());
        koltuk2 = Integer.valueOf(car2.getKltksayisi());
        kapi2 = Integer.valueOf(car2.getKapisayisi());

        markaV2.setText(markaad2);
        modelV2.setText(model2);
        puanV2.setText(" %"+puan2);
        fiyatV2.setText(""+fiyat2);
        hizV2.setText(""+hiz2);
        yilV2.setText(""+yil2);
        yakitV2.setText(yakit2);
        yakitHacimV2.setText(""+yakithacim2);
        vitesV2.setText(vites2);
        vitessayiV2.setText(""+viteskademe2);
        motorV2.setText(""+motor2);
        silindirV2.setText(""+silindir2);
        agirlikV2.setText(""+agirlik2);
        koltukV2.setText(""+koltuk2);
        kapiV2.setText(""+kapi2);

        compare();
    }
    public void ekle()
    {
        ekle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (markaV2.getText().equals(""))
                {
                    Intent intent = new Intent(getApplicationContext(),MarkaActivity.class);
                    intent.putExtra("button","1");
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(),CompareMarka.class);
                    intent.putExtra("araba","1");
                    intent.putExtra("markaid",markaid2);
                    intent.putExtra("modelid",modelid2);
                    startActivity(intent);
                }

            }
        });

        ekle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),CompareMarka.class);
                intent.putExtra("araba","2");
                intent.putExtra("markaid",markaid);
                intent.putExtra("modelid",modelid);
                startActivity(intent);

            }
        });
    }

    public void getImagesCar1() {
        Call<List<SliderPojo>> requestImages = ManagerAll.getInstance().slider(markaId, modelId);
        requestImages.enqueue(new Callback<List<SliderPojo>>() {
            @Override
            public void onResponse(Call<List<SliderPojo>> call, Response<List<SliderPojo>> response) {

                sliderPojos1 = response.body();
                sliderAdapter = new SliderAdapter(sliderPojos1, getApplicationContext());
                arabaResim1.setAdapter(sliderAdapter);
                circleIndicator1.setViewPager(arabaResim1);
                circleIndicator1.bringToFront();
            }

            @Override
            public void onFailure(Call<List<SliderPojo>> call, Throwable t) {

            }
        });
    }

    public void getImagesCar2() {
        Call<List<SliderPojo>> requestImages = ManagerAll.getInstance().slider(markaId2, modelId2);
        requestImages.enqueue(new Callback<List<SliderPojo>>() {
            @Override
            public void onResponse(Call<List<SliderPojo>> call, Response<List<SliderPojo>> response) {

                sliderPojos2 = response.body();
                sliderAdapter = new SliderAdapter(sliderPojos2, getApplicationContext());
                arabaResim2.setAdapter(sliderAdapter);
                circleIndicator2.setViewPager(arabaResim2);
                circleIndicator2.bringToFront();
            }

            @Override
            public void onFailure(Call<List<SliderPojo>> call, Throwable t) {

            }
        });
    }

    public void compare()
    {

        //FİYAT
        if(fiyat>fiyat2)
        {
            fiyatV1.setTextColor(Color.RED);
            fiyatV2.setTextColor(Color.GREEN);
        }
        if(fiyat<fiyat2)
        {
            fiyatV1.setTextColor(Color.GREEN);
            fiyatV2.setTextColor(Color.RED);
        }
        if(fiyat==fiyat2)
        {
            fiyatV1.setTextColor(Color.WHITE);
            fiyatV2.setTextColor(Color.WHITE);
        }

        //MOTOR
        if(motor>motor2)
        {
            motorV1.setTextColor(Color.GREEN);
            motorV2.setTextColor(Color.RED);
        }
        if(motor<motor2)
        {
            motorV1.setTextColor(Color.RED);
            motorV2.setTextColor(Color.GREEN);
        }
        if(motor==motor2)
        {
            motorV1.setTextColor(Color.WHITE);
            motorV2.setTextColor(Color.WHITE);
        }

        karsilastir(puan,puan2,puanV1,puanV2);
        karsilastir(yil,yil2,yilV1,yilV2);
        karsilastir(hiz,hiz2,hizV1,hizV2);
        karsilastir(yakithacim,yakithacim2,yakitHacimV1,yakitHacimV2);
        karsilastir(viteskademe,viteskademe2,vitessayiV1,vitessayiV2);
        karsilastir(silindir,silindir2,silindirV1,silindirV2);
        karsilastir(agirlik,agirlik2,agirlikV1,agirlikV2);
        karsilastir(koltuk,koltuk2,koltukV1,koltukV2);
        karsilastir(kapi,kapi2,kapiV1,kapiV2);


    }

    public void karsilastir(int ozellik1, int ozellik2, TextView view1, TextView view2)
    {

        if(ozellik1>ozellik2)
        {
            view1.setTextColor(Color.GREEN);
            view2.setTextColor(Color.RED);
        }
        if(ozellik1<ozellik2)
        {
            view1.setTextColor(Color.RED);
            view2.setTextColor(Color.GREEN);
        }
        if(ozellik1==ozellik2)
        {
            view1.setTextColor(Color.WHITE);
            view2.setTextColor(Color.WHITE);
        }
    }
}
