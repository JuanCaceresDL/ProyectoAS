package com.example.proyectoas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.proyectoas.API.ApiClient;
import com.example.proyectoas.API.TurAPI;
import com.example.proyectoas.adapter.turAdapter;
import com.example.proyectoas.modelos.turistico;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private List<turistico> mTuristico;
    private TurAPI mApi;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "user";
    private static final String KEY_EMAIL = "email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mApi = ApiClient.getInstance().create(TurAPI.class);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String slcorreo = sharedPreferences.getString(KEY_EMAIL,null);

        RecyclerView rvTur = (RecyclerView) findViewById(R.id.tur_list);
        // Create adapter passing in the sample user data
        turAdapter adapter = new turAdapter(new ArrayList<>());
        // Attach the adapter to the recyclerview to populate items
        rvTur.setAdapter(adapter);
        // Set layout manager to position the items
        rvTur.setLayoutManager(new LinearLayoutManager(this));
        Call<List<turistico>> bookCall = mApi.getTur();
        bookCall.enqueue(new Callback<List<turistico>>() {
            @Override
            public void onResponse(Call<List<turistico>> call, Response<List<turistico>> response) {
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                System.out.println(response);
                System.out.println(slcorreo);
                adapter.reloadData(response.body());
            }

            @Override
            public void onFailure(Call<List<turistico>> call, Throwable t) {
                System.out.println(t.toString());
                Toast.makeText(HomeActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public  void restaurantes(View view){
        startActivity(new Intent(getApplicationContext(),RestActivity.class));
        finish();
    }
}
