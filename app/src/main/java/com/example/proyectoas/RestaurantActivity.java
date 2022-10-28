package com.example.proyectoas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.proyectoas.API.ApiClient;
import com.example.proyectoas.API.DescApi;
import com.example.proyectoas.API.ResApi;
import com.example.proyectoas.modelos.Detallelug;
import com.example.proyectoas.modelos.Detallerest;
import com.example.proyectoas.modelos.restau;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantActivity extends AppCompatActivity {

    private Detallerest detalleres;
    private DescApi descApi;
    private Integer rId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        rId = getIntent().getIntExtra("idres", -1);
        descApi = ApiClient.getInstance().create(DescApi.class);
        Call<Detallerest> detallresCall = descApi.getDetares(rId);
        detallresCall.enqueue(new Callback<Detallerest>() {
            @Override
            public void onResponse(Call<Detallerest> call, Response<Detallerest> response) {
                poblares(response.body());
            }

            @Override
            public void onFailure(Call<Detallerest> call, Throwable t) {

            }
        });

    }
    public void poblares(Detallerest detres){
        TextView descript = findViewById(R.id.descript);
        descript.setText(detres.rDepart);
        RatingBar ratingBar = findViewById(R.id.ratingBar4);
        ratingBar.setRating(detres.rCali);
        TextView visita = findViewById(R.id.fecha);
        visita.setText(detres.rVisita);
        ImageView imageres = findViewById(R.id.imagen_detalle_res);
        Button waze = findViewById(R.id.waze);
        Button google = findViewById(R.id.google);
        Glide.with(this).load(detres.rImagen).into(imageres);
        waze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://waze.com/ul/h9fxeh5xnr&navigate=yes")));
            }
        });

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/dir/?api=1&destination=14.6102117%2C-90.5195859&dir_action=navigate")));
            }
        });

    }

}
