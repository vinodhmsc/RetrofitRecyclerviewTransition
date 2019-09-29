package com.vinodh.retrofitrecyclerviewtransition;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.vinodh.retrofitrecyclerviewtransition.Adapter.RecyclerviewAdapter;
import com.vinodh.retrofitrecyclerviewtransition.Interface.Api;
import com.vinodh.retrofitrecyclerviewtransition.Model.MarvelHeros;
import com.vinodh.retrofitrecyclerviewtransition.Utils.AppUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;
    Api service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_id);
        service = AppUtils.getMarvelHeros();

        if(!isNetworkConnected() == true){
            Toast.makeText(this, "Please connect the internet!", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<List<MarvelHeros>> call = service.getHeros();
        call.enqueue(new Callback<List<MarvelHeros>>() {
            @Override
            public void onResponse(Call<List<MarvelHeros>> call, Response<List<MarvelHeros>> response) {

                if(!response.isSuccessful()){
                    Log.d(TAG, "onResponse: Error :" +response.code());
                }

                List<MarvelHeros> heros = response.body();
                RecyclerviewAdapter adapter = new RecyclerviewAdapter(MainActivity.this,heros);
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


            }

            @Override
            public void onFailure(Call<List<MarvelHeros>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error" +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
