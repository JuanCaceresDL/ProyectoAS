package com.example.proyectoas.API;

import com.example.proyectoas.modelos.restau;
import com.example.proyectoas.modelos.turistico;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ResApi {
    @GET("/android/getRestaurantes.php")
    Call<List<restau>> getRes();
}
