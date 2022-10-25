package com.example.proyectoas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.InetAddresses;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.proyectoas.API.ApiClient;
import com.example.proyectoas.API.DescApi;
import com.example.proyectoas.API.DetallApi;
import com.example.proyectoas.adapter.desAdapter;
import com.example.proyectoas.modelos.Detallelug;
import com.example.proyectoas.modelos.detalles;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TuristicoActivity extends AppCompatActivity {

    private Detallelug detallelug;
    private DescApi descApi;
    private Integer mId;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turistico);
        mId = getIntent().getIntExtra("idtur", -1);
        descApi = ApiClient.getInstance().create(DescApi.class);
        url="http://192.168.1.36/android/getDetalleslugar.php";
        Call<Detallelug> detallCall = descApi.getDeta(mId);
        detallCall.enqueue(new Callback<Detallelug>() {
            @Override
            public void onResponse(Call<Detallelug> call, Response<Detallelug> response) {
                poblar(response.body());
            }

            @Override
            public void onFailure(Call<Detallelug> call, Throwable t) {

            }
        });

        RecyclerView rvTur = (RecyclerView) findViewById(R.id.comentur_list);
        // Create adapter passing in the sample user data
        desAdapter adapter = new desAdapter(new ArrayList<>());
        // Attach the adapter to the recyclerview to populate items
        rvTur.setAdapter(adapter);
        // Set layout manager to position the items
        rvTur.setLayoutManager(new LinearLayoutManager(this));
        Call<List<detalles>> detCall = descApi.getDet();
        detCall.enqueue(new Callback<List<detalles>>() {
            @Override
            public void onResponse(Call<List<detalles>> call, Response<List<detalles>> response) {
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                System.out.println(response);
                adapter.reloadData(response.body());
            }

            @Override
            public void onFailure(Call<List<detalles>> call, Throwable t) {
                System.out.println(t.toString());
                Toast.makeText(TuristicoActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void poblar(Detallelug detlug){
        TextView descript = findViewById(R.id.descript);
        descript.setText(detlug.lDepart);
        RatingBar ratingBar = findViewById(R.id.ratingBar3);
        ratingBar.setRating(detlug.lCali);
        TextView visita = findViewById(R.id.fecha);
        visita.setText(detlug.lVisita);
        ImageView imagelug = findViewById(R.id.imagen_detalle_tur);
        Button waze = findViewById(R.id.waze);
        Button google = findViewById(R.id.google);
        Glide.with(this).load(detlug.lImagen).into(imagelug);

        waze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://waze.com/ul/hd503bx4j8&navigate=yes")));
            }
        });

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/dir/?api=1&destination=15.8625321%2C-90.7802413&dir_action=navigate")));
            }
        });

    }
}