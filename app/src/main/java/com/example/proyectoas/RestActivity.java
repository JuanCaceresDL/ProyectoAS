package com.example.proyectoas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.proyectoas.API.ApiClient;
import com.example.proyectoas.API.ResApi;
import com.example.proyectoas.API.TurAPI;
import com.example.proyectoas.adapter.resAdapter;
import com.example.proyectoas.adapter.turAdapter;
import com.example.proyectoas.modelos.restau;
import com.example.proyectoas.modelos.turistico;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestActivity extends AppCompatActivity {

    private List<restau> rRestaurant;
    private ResApi rApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);
        rApi = ApiClient.getInstance().create(ResApi.class);

        RecyclerView rvRes = (RecyclerView) findViewById(R.id.res_list);
        // Create adapter passing in the sample user data
        resAdapter adapter = new resAdapter(new ArrayList<>());
        // Attach the adapter to the recyclerview to populate items
        rvRes.setAdapter(adapter);
        // Set layout manager to position the items
        rvRes.setLayoutManager(new LinearLayoutManager(this));
        Call<List<restau>> bookCall = rApi.getRes();
        bookCall.enqueue(new Callback<List<restau>>() {
            @Override
            public void onResponse(Call<List<restau>> call, Response<List<restau>> response) {
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                System.out.println(response);
                adapter.reloadData(response.body());
            }

            @Override
            public void onFailure(Call<List<restau>> call, Throwable t) {
                System.out.println(t.toString());
                Toast.makeText(RestActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public  void turismo(View view){
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        finish();
    }
}