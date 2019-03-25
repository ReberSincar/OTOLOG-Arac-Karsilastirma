package com.rebersincar.otologg.Activitys.AdminPanel;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rebersincar.otologg.Activitys.MarkaActivity;
import com.rebersincar.otologg.Adapters.MarkaAdapter;
import com.rebersincar.otologg.Adapters.ResimAdapter;
import com.rebersincar.otologg.Models.ResimDelPojo;
import com.rebersincar.otologg.Models.ResimPojo;
import com.rebersincar.otologg.R;
import com.rebersincar.otologg.RestApi.ManagerAll;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResimDel extends AppCompatActivity {

    ListView liste;
    ImageView logo;
    TextView marka,model;

    List<ResimPojo> pojo;

    ResimAdapter resimAdapter;

    Bundle extras;

    String markaAd,modelAd,logoLink;

    int markaId,modelId,resimId;

    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resim_del);

        define();
        ListYukle();
        Sil();


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),ResimOperations.class);
        startActivity(intent);
    }

    public void define()
    {
        liste = findViewById(R.id.resimDelList);
        logo = findViewById(R.id.resimDelMarkaLogo);
        marka = findViewById(R.id.resimDelMarka);
        model = findViewById(R.id.resimDelModel);

        extras = getIntent().getExtras();

        markaId = Integer.valueOf(extras.getString("markaid"));
        modelId = Integer.valueOf(extras.getString("modelid"));
        markaAd = extras.getString("markaad");
        modelAd = extras.getString("modelad");
        logoLink = extras.getString("logo");
        marka.setText(markaAd);
        model.setText(modelAd);

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

    public void Onay()
    {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Resim Sil")
                .setMessage("Seçtiğiniz resmi kaldırmak istediğinize emin misiniz?")
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Baglan();

                    }
                }).setNegativeButton("Hayır", null).show();
    }

    public void Sil()
    {
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                resimId = pojo.get(position).getResimId();
                Onay();
            }
        });
    }

    public void Baglan()
    {
        pd = new ProgressDialog(ResimDel.this);
        pd.setMessage("İşlem Gerçekleştiriliyor.Lütfen Bekleyiniz..");
        pd.show();

        Call<ResimDelPojo> requestDel = ManagerAll.getInstance().resimdel(resimId);
        requestDel.enqueue(new Callback<ResimDelPojo>() {
            @Override
            public void onResponse(Call<ResimDelPojo> call, Response<ResimDelPojo> response) {
                if (response.isSuccessful())
                {
                    if (response.body().isTf())
                    {
                        Toast.makeText(ResimDel.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),ResimDel.class);
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
                        Toast.makeText(ResimDel.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                        pd.cancel();
                    }
                }
                else
                {
                    Toast.makeText(ResimDel.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                    pd.cancel();
                }
            }

            @Override
            public void onFailure(Call<ResimDelPojo> call, Throwable t) {
                Toast.makeText(ResimDel.this, "Fail", Toast.LENGTH_SHORT).show();
                pd.cancel();
            }
        });
    }
}
