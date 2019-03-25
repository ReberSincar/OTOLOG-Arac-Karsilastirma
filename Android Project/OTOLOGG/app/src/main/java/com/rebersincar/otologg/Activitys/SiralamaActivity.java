package com.rebersincar.otologg.Activitys;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.rebersincar.otologg.Adapters.MarkaAdapter;
import com.rebersincar.otologg.Adapters.SiralamaAdapter;
import com.rebersincar.otologg.Models.SiralamaPojo;
import com.rebersincar.otologg.R;
import com.rebersincar.otologg.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SiralamaActivity extends AppCompatActivity {

    ListView listView;
    SiralamaAdapter siralamaAdapter;
    List<SiralamaPojo> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siralama);

        listView = findViewById(R.id.siralamaList);

        yukle();
        postAc();
    }

    public void yukle()
    {
        Call<List<SiralamaPojo>> request = ManagerAll.getInstance().siralama();
        request.enqueue(new Callback<List<SiralamaPojo>>() {
            @Override
            public void onResponse(Call<List<SiralamaPojo>> call, Response<List<SiralamaPojo>> response) {
                list = response.body();

                siralamaAdapter = new SiralamaAdapter(list,getApplicationContext());
                listView.setAdapter(siralamaAdapter);
            }

            @Override
            public void onFailure(Call<List<SiralamaPojo>> call, Throwable t) {

            }
        });
    }

    public void postAc() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), CarActivity.class);
                intent.putExtra("markaid", list.get(position).getMarkaid());
                intent.putExtra("modelid", list.get(position).getModelid());

                startActivity(intent);
            }
        });
    }
}
