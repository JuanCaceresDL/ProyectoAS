package com.example.proyectoas.API;

import com.example.proyectoas.modelos.turistico;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface TurAPI {
    @GET("/android/getLugturisticos.php")
    Call<List<turistico>> getTur();
}
